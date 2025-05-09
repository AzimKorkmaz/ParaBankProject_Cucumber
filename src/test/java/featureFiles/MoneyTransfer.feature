Feature: Funds Functionality

  Background:
    Given The user navigates to the ParaBank website
    When The user clicks on the register button and then fills the information areas with the customer data and clicks on the submit button
    Then Success message should be displayed
    When The user clicks on the logout button

    When The user fills in the login information and clicks the login button
    Then The user should be redirected to the account overview page
    When The user clicks on the Open new account area

    Then The user selects account type
    And the user clicks the open new account button
    And the user should see the message that the account was created and the account number on the screen

    When The user clicks on Transfer Funds button from home page

  Scenario:  The user should be able to transfer funds successfully
    And The user fills all fields
    And The user clicks Transfer button
    Then The user should see a transfer message and confirm successful transfer completion

    When The user clicks on Transfer Funds button from home page
    When The user does not enter anything to amount field
    And The user clicks Transfer button
    Then The user should see error message on transfer funds page

    When The user clicks on Find Transactions button from homepage
    And The user selects an account and enters the Transaction ID
    And The user click Find Transactions button
    Then The user should be redirected Account Details page