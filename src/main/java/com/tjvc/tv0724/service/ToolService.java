package com.tjvc.tv0724.service;

import com.tjvc.tv0724.model.MoneyAmount;
import com.tjvc.tv0724.model.Tool;
import com.tjvc.tv0724.model.ToolType;
import com.tjvc.tv0724.repo.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class ToolService {

    private final ToolRepository toolRepository;
    private final DateService dateService;
    private final MoneyService moneyService;

    @Autowired
    public ToolService(ToolRepository toolRepository, DateService dateService, MoneyService moneyService) {
        this.toolRepository = toolRepository;
        this.dateService = dateService;
        this.moneyService = moneyService;
    }

    public Collection<Tool> getAllTools() {
        return toolRepository.getAllTools();
    }

    public Tool getToolByCode(String code) {
        return toolRepository.getToolByCode(code);
    }

    // This doesn't really need to be in the service as of now, but leaving it in case it changes over time
    // TODO return partially populated RentalAgreement w/ chargeable days instead
    public MoneyAmount computeToolRental(ToolType toolType,
                                                 LocalDate checkOutDate,
                                                 int rentalDayCount) {
        assert toolType != null;
        assert rentalDayCount > 0;
        assert checkOutDate != null;
        var chargeableDays = 0;
        for (var d = 1; d <= rentalDayCount; ++d) {
            var dateToEval = checkOutDate.plusDays(d);
            var isHoliday = dateService.isHoliday(dateToEval);
            if (!toolType.isChargeOnHoliday() && isHoliday) { // It's a holiday and there is no charge for holidays
                continue;
            }
            var isWeekendDay = dateService.isWeekend(dateToEval);
            if (!toolType.isChargeOnWeekend() && isWeekendDay) { // It's the weekend and there is no charge for weekends
                continue;
            }
            var isWeekday = !isWeekendDay;
            if (!toolType.isChargeOnWeekday() && isWeekday) { // It's a weekday and there is no charge for weekdays
                continue;
            }
            ++chargeableDays; // Otherwise do charge for this date
        }
        return moneyService.computeLineItemTotal(toolType.getDailyCharge(), chargeableDays, 0);
    }
}
