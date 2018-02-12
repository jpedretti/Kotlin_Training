Feature: Open app
  Scenario: Successful open the app
    Given The app is opened
    Then The greeting message should be displayed
    And The Controller response message should be displayed
    And The goto di button should be displayed and enabled
