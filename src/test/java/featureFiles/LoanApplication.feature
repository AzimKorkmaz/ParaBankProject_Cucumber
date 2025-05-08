Feature: Loan Application Functionality

  Background:
    Given The user navigates to the ParaBank website
    When The user clicks on the register button and then fills the information areas with the customer data and clicks on the submit button
    Then Success message should be displayed
    When The user clicks on the logout button

    When The user fills in the login information and clicks the login button
    Then The user should be redirected to the account overview page

  Scenario: User should successfully apply for a loan with valid amount
    Given User clicks on the Request Loan button
    When User applies for a loan
    Then User should see the loan is approved
    And User should see the new account number
    When User clicks on the new account number
    Then User verifies there are no transactions in the new account

  Scenario: User should get rejection for invalid loan application
    Given User clicks on the Request Loan button
    When User applies for a loan with invalid values
    Then User should see the loan is denied
    And User should see the denial reason