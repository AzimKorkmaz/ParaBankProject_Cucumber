Feature: User Login Functionality

  Background:
    Given The user navigates to the ParaBank website

  Scenario: User Login
    When The user fills in the login information and clicks the login button
    Then The user should be redirected to the account overview page