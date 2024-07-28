import com.tjvc.tv0724.model.ToolType;
import com.tjvc.tv0724.service.RentalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = IntegrationTests.TestConfig.class)
public class IntegrationTests {

    @Configuration
    @ComponentScan(basePackages = {"com.tjvc.tv0724.service", "com.tjvc.tv0724.repo"})
    public static class TestConfig {

    }

    @Autowired
    private RentalService rentalService;


    @Test
    public void test1() {
        String toolCode = "JAKR";
        LocalDate checkOutDate = LocalDate.of(2015, 9, 3);
        int rentalDays = 5;
        int discountPercent = 101;

        assertThrows(IllegalArgumentException.class, () -> rentalService.createRentalAgreement(
                toolCode, rentalDays, discountPercent, checkOutDate));
    }

    @Test
    public void test2() {
        String toolCode = "LADW";
        LocalDate checkOutDate = LocalDate.of(2020, 7, 2);
        int rentalDays = 3;
        int discountPercent = 10;

        var rentalAgreement = rentalService.createRentalAgreement(
                toolCode, rentalDays, discountPercent, checkOutDate);
        rentalAgreement.printRentalAgreementReport();

        assertEquals(toolCode, rentalAgreement.toolCode());
        assertEquals(ToolType.Ladder, rentalAgreement.toolType());
        assertEquals("Werner", rentalAgreement.toolBrand());
        assertEquals(rentalDays, rentalAgreement.rentalDays());
        assertEquals(checkOutDate, rentalAgreement.checkOutDate());
        assertEquals(LocalDate.of(2020, 7, 5), rentalAgreement.dueDate());
        assertEquals(new BigDecimal("1.99"), rentalAgreement.toolDailyCharge().getAmount());
        assertEquals(2, rentalAgreement.chargeableDays());
        assertEquals(new BigDecimal("3.98"),
                rentalAgreement.totalWithDiscount().getAmountBeforeDiscount().getAmount());
        assertEquals(discountPercent, rentalAgreement.discountPercent());
        assertEquals(new BigDecimal("0.40"), rentalAgreement.totalWithDiscount().getDiscountAmount().getAmount());
        assertEquals(new BigDecimal("3.58"), rentalAgreement.totalWithDiscount().getAmount());
    }

    @Test
    public void test3() {
        String toolCode = "CHNS";
        LocalDate checkOutDate = LocalDate.of(2015, 7, 2);
        int rentalDays = 5;
        int discountPercent = 25;

        var rentalAgreement = rentalService.createRentalAgreement(
                toolCode, rentalDays, discountPercent, checkOutDate);
        rentalAgreement.printRentalAgreementReport();

        assertEquals(toolCode, rentalAgreement.toolCode());
        assertEquals(ToolType.Chainsaw, rentalAgreement.toolType());
        assertEquals("Stihl", rentalAgreement.toolBrand());
        assertEquals(rentalDays, rentalAgreement.rentalDays());
        assertEquals(checkOutDate, rentalAgreement.checkOutDate());
        assertEquals(LocalDate.of(2015, 7, 7), rentalAgreement.dueDate());
        assertEquals(new BigDecimal("1.49"), rentalAgreement.toolDailyCharge().getAmount());
        assertEquals(3, rentalAgreement.chargeableDays());
        assertEquals(new BigDecimal("4.47"),
                rentalAgreement.totalWithDiscount().getAmountBeforeDiscount().getAmount());
        assertEquals(discountPercent, rentalAgreement.discountPercent());
        assertEquals(new BigDecimal("1.12"), rentalAgreement.totalWithDiscount().getDiscountAmount().getAmount());
        assertEquals(new BigDecimal("3.35"), rentalAgreement.totalWithDiscount().getAmount());
    }

    @Test
    public void test4() {
        String toolCode = "JAKD";
        LocalDate checkOutDate = LocalDate.of(2015, 9, 3);
        int rentalDays = 6;
        int discountPercent = 0;

        var rentalAgreement = rentalService.createRentalAgreement(
                toolCode, rentalDays, discountPercent, checkOutDate);
        rentalAgreement.printRentalAgreementReport();

        assertEquals(toolCode, rentalAgreement.toolCode());
        assertEquals(ToolType.Jackhammer, rentalAgreement.toolType());
        assertEquals("DeWalt", rentalAgreement.toolBrand());
        assertEquals(rentalDays, rentalAgreement.rentalDays());
        assertEquals(checkOutDate, rentalAgreement.checkOutDate());
        assertEquals(LocalDate.of(2015, 9, 9), rentalAgreement.dueDate());
        assertEquals(new BigDecimal("2.99"), rentalAgreement.toolDailyCharge().getAmount());
        assertEquals(3, rentalAgreement.chargeableDays());
        assertEquals(new BigDecimal("8.97"),
                rentalAgreement.totalWithDiscount().getAmountBeforeDiscount().getAmount());
        assertEquals(discountPercent, rentalAgreement.discountPercent());
        assertEquals(new BigDecimal("0.00"), rentalAgreement.totalWithDiscount().getDiscountAmount().getAmount());
        assertEquals(new BigDecimal("8.97"), rentalAgreement.totalWithDiscount().getAmount());
    }

    @Test
    public void test5() {
        String toolCode = "JAKR";
        LocalDate checkOutDate = LocalDate.of(2015, 7, 2);
        int rentalDays = 9;
        int discountPercent = 0;

        var rentalAgreement = rentalService.createRentalAgreement(
                toolCode, rentalDays, discountPercent, checkOutDate);
        rentalAgreement.printRentalAgreementReport();

        assertEquals(toolCode, rentalAgreement.toolCode());
        assertEquals(ToolType.Jackhammer, rentalAgreement.toolType());
        assertEquals("Ridgid", rentalAgreement.toolBrand());
        assertEquals(rentalDays, rentalAgreement.rentalDays());
        assertEquals(checkOutDate, rentalAgreement.checkOutDate());
        assertEquals(LocalDate.of(2015, 7, 11), rentalAgreement.dueDate());
        assertEquals(new BigDecimal("2.99"), rentalAgreement.toolDailyCharge().getAmount());
        assertEquals(5, rentalAgreement.chargeableDays());
        assertEquals(new BigDecimal("14.95"),
                rentalAgreement.totalWithDiscount().getAmountBeforeDiscount().getAmount());
        assertEquals(discountPercent, rentalAgreement.discountPercent());
        assertEquals(new BigDecimal("0.00"), rentalAgreement.totalWithDiscount().getDiscountAmount().getAmount());
        assertEquals(new BigDecimal("14.95"), rentalAgreement.totalWithDiscount().getAmount());
    }

    @Test
    public void test6() {
        String toolCode = "JAKR";
        LocalDate checkOutDate = LocalDate.of(2020, 7, 2);
        int rentalDays = 4;
        int discountPercent = 50;

        var rentalAgreement = rentalService.createRentalAgreement(
                toolCode, rentalDays, discountPercent, checkOutDate);
        rentalAgreement.printRentalAgreementReport();

        assertEquals(toolCode, rentalAgreement.toolCode());
        assertEquals(ToolType.Jackhammer, rentalAgreement.toolType());
        assertEquals("Ridgid", rentalAgreement.toolBrand());
        assertEquals(rentalDays, rentalAgreement.rentalDays());
        assertEquals(checkOutDate, rentalAgreement.checkOutDate());
        assertEquals(LocalDate.of(2020, 7, 6), rentalAgreement.dueDate());
        assertEquals(new BigDecimal("2.99"), rentalAgreement.toolDailyCharge().getAmount());
        assertEquals(1, rentalAgreement.chargeableDays());
        assertEquals(new BigDecimal("2.99"),
                rentalAgreement.totalWithDiscount().getAmountBeforeDiscount().getAmount());
        assertEquals(discountPercent, rentalAgreement.discountPercent());
        assertEquals(new BigDecimal("1.50"), rentalAgreement.totalWithDiscount().getDiscountAmount().getAmount());
        assertEquals(new BigDecimal("1.49"), rentalAgreement.totalWithDiscount().getAmount());
    }
}