Feature: DI
  Scenario: Go to DI screen and execute action
    Given I'm on the Di screen
    Then The app name is displayed
    When I click the do service stuff button
    Then The service stuff result is displayed