package com.tjvc.tv0724.service;

import com.tjvc.tv0724.model.RentalAgreement;
import com.tjvc.tv0724.model.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static com.tjvc.tv0724.model.Tool.DELIMITER;

@Service
public class RentalService {

    private final ToolService toolService;
    private final MoneyService moneyService;

    @Autowired
    public RentalService(ToolService toolService, MoneyService moneyService) {
        this.toolService = toolService;
        this.moneyService = moneyService;
    }

    public String getToolListing() {
        var tools = toolService.getAllTools();
        var toolListing = "Tool Code" + DELIMITER + "Tool Type" + DELIMITER + "Brand";
        return toolListing + "\n" + tools.stream().map(Tool::toString).collect(Collectors.joining("\n"));
    }

    public RentalAgreement createRentalAgreement(String toolCode,
                                                 int rentalDayCount,
                                                 int discountPercent,
                                                 LocalDate checkOutDate) {
        assert toolCode != null;
        assert checkOutDate != null;
        if (rentalDayCount <= 0) {
            throw new IllegalArgumentException("Rental Day Count must be greater than 0.");
        }
        if (discountPercent <= -1 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }
        var tool = toolService.getToolByCode(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Tool code not found: " + toolCode);
        }

        var rentalCalculationResult = toolService.computeToolRental(tool.getType(), checkOutDate, rentalDayCount);
        var totalWithDiscount = moneyService.applyDiscount(
                rentalCalculationResult.rentalCharge().getAmount(), discountPercent);

        return new RentalAgreement(
                toolCode,
                tool.getType(),
                tool.getBrand(),
                rentalDayCount,
                checkOutDate,
                checkOutDate.plusDays(rentalDayCount),
                moneyService.createMoneyAmount(tool.getType().getDailyCharge()),
                rentalCalculationResult.chargeableDays(),
                discountPercent,
                totalWithDiscount // This provides the pre-discount amount, discount amount, and amount after discount
                );
    }
}
