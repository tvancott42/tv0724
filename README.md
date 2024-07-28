# Tool Rental Programming Demonstration

### Overview
This is a Spring Boot-based Java console app that demonstrates basic dependency injection as well as some newer Java
features.

### How to test or run
- Integration tests for the overall rental calculation logic can be found in IntegrationTests.
- The app can be run as a console app where you can input values manually for tool rental checkout and see user-friendly 
error messaging.

### Expandability
- Specific-date holidays can be added in the Spring app config (param: app.holiday.dates).
- The locale can be adjusted from en-US for other currencies.
- There are some comments in the Services and Repos pointing out spots where a persistent JPA-based solution could be
  swapped in.
