package com.tjvc.tv0724;

import com.tjvc.tv0724.service.RentalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final RentalService rentalService;

    public Application(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("""
                Welcome to Tool Rental Checkout
                
                Please enter a tool code from the following list:
                """);

        System.out.println(rentalService.getToolListing());

        var scanner = new Scanner(System.in);
        var toolCode = promptAndGatherStringInput(scanner, "tool code");
        while (toolCode.isEmpty()) {
            System.out.println("Tool code is required, please enter a valid tool code.");
            toolCode = promptAndGatherStringInput(scanner, "tool code");
        }

        var rentalDayCount = promptAndGatherIntInput(scanner, "number of days to rent", 1);

        var discountPercent = promptAndGatherIntInput(scanner, "discount %", 0);

        LocalDate checkOutDate = null;
        while (checkOutDate == null) {
            try {
                checkOutDate = promptAndGatherDateInput(scanner, "check out date", LocalDate.now());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in MM/dd/yy format.");
            }
        }

        try {
            var rentalAgreement = rentalService.createRentalAgreement(
                    toolCode.toUpperCase(), rentalDayCount, discountPercent, checkOutDate);
            System.out.println();
            rentalAgreement.printRentalAgreementReport();
        } catch (IllegalArgumentException exc) {
            System.out.println(exc.getMessage());
            System.out.println("Press enter to continue.");
            scanner.nextLine();
            run(args);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static String promptAndGatherStringInput(Scanner scanner, String prompt) {
        return promptAndGatherStringInput(scanner, prompt, null);
    }

    private static String promptAndGatherStringInput(Scanner scanner, String prompt, String defaultValue) {
        promptForInput(prompt, defaultValue);
        return scanner.nextLine();
    }

    private static int promptAndGatherIntInput(Scanner scanner, String prompt, int defaultValue) {
        var stringValue = promptAndGatherStringInput(scanner, prompt, Integer.toString(defaultValue));
        int intValue;
        if (stringValue == null || stringValue.isEmpty()) {
            intValue = defaultValue;
        } else {
            intValue = Integer.parseInt(stringValue);
        }
        return intValue;
    }

    @SuppressWarnings("SameParameterValue")
    private static LocalDate promptAndGatherDateInput(Scanner scanner, String prompt, LocalDate defaultValue) {
        var dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        var stringValue = promptAndGatherStringInput(scanner, prompt, defaultValue.format(dateFormatter));
        LocalDate dateValue;
        if (stringValue == null || stringValue.isEmpty()) {
            dateValue = defaultValue;
        } else {
            dateValue = LocalDate.parse(stringValue, dateFormatter);
        }
        return dateValue;
    }

    private static void promptForInput(String prompt, String defaultValue) {
        System.out.printf("Enter the %s%s: ", prompt, defaultValue == null ? "" : " [" + defaultValue + "]");
    }

}
