Feature: User Logout and Login Functionality

  Background:
    Given The user navigates to the ParaBank website
    When The user clicks on the register button and then fills the information areas with the customer data and clicks on the submit button
    Then Success message should be displayed

  Scenario: User Login and Logout
    When The user clicks on the logout button
    When The user attempts to log in with incorrect login credentials
    Then An error message should be displayed

    When The user fills in the login information and clicks the login button
    Then The user should be redirected to the account overview page