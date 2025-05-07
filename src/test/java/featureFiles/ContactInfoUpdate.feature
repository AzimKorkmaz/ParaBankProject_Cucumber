Feature:Contact Info Update Functionality

  Background:
    Given The user navigates to the ParaBank website
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



