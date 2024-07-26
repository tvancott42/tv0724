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
        assert rentalDayCount > 0;
        assert discountPercent > -1 && discountPercent <= 100;
        assert checkOutDate != null;
        var tool = toolService.getToolByCode(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("toolCode not found: " + toolCode);
        }
        var totalBeforeDiscount = toolService.computeToolRental(tool.getType(), checkOutDate, rentalDayCount);
        var totalWithDiscount = moneyService.computeLineItemTotal(totalBeforeDiscount.getAmount(), 1, discountPercent);
        // TODO populate rental agreement
        return new RentalAgreement();
    }
}
