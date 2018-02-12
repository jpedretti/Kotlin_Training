Feature: Open app
  Scenario: Go to DI screen and execute action
    Given I'm on the Di screen
    Then The app name is displayed
    When I click the do service stuff button
    Then The service stuff result is displayed

  Scenario: Go to DI screen and press back
    Given I'm on the Di screen
    When I press the back button
    Then I should back to Main screen