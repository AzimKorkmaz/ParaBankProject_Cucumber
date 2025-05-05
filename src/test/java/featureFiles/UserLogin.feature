Feature: User Logout and Login Functionality

  Background:
    Given The user navigates to the ParaBank website

  Scenario: User Login and Logout

    When The user fills in the login information and clicks the login button
    Then The user should be redirected to the account overview page

    When The user clicks on the logout button

    When The user attempts to log in with incorrect login credentials
    Then An error message should be displayed