Feature:

  Background:
    Given The user navigates to the ParaBank website
    When The user clicks on the register button and then fills the information areas with the customer data and clicks on the submit button
    Then Success message should be displayed
    When The user clicks on the logout button

    When The user fills in the login information and clicks the login button
    Then The user should be redirected to the account overview page

  Scenario:

  Scenario: Opening a New Account
    When The user clicks on the Open new account area

    Then The user selects account type
    And the user clicks the open new account button
    And the user should see the message that the account was created and the account number on the screen
