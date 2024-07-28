import com.tjvc.tv0724.service.RentalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
        assertThrows(IllegalArgumentException.class, () -> rentalService.createRentalAgreement(
                "JAKR", 5, 101, LocalDate.of(2015, 9, 3)));
    }

    @Test
    public void test2() {

    }

    @Test
    public void test3() {

    }

    @Test
    public void test4() {

    }
}