Feature:Contact Info Update Functionality

  Background:
    Given The user navigates to the ParaBank website
    When The user clicks on the register button and then fills the information areas with the customer data and clicks on the submit button
    Then Success message should be displayed

    When The user clicks on the logout button

    When The user fills in the login information and clicks the login button
    Then The user should be redirected to the account overview page

  Scenario: Update user information
    Given User click on the Update Contact Info button
    When User clear the last name
    Then User click on the update profile button
    And User will visible an error message
    Then User will update the relevant field
    Then User click on the update profile button
    And User will see the update message
    Given User click on the Update Contact Info button

    When The user clicks on the logout button