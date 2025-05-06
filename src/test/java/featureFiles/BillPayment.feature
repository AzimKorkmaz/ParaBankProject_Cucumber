Feature: Bill Payment Functionality

  Background:
    Given The user navigates to the ParaBank website
    When The user fills in the login information and clicks the login button
    Then The user should be redirected to the account overview page

  Scenario Outline: The user will pay using bill payment features
    Then save the current balance for checking
    Given User clicks on the BillPay button
    Then The user enters the information of the "<institution>" invoice to be paid
    And clicks the SendPayment button
    Then The user clicks on the Accounts Overview button
    And checks the payment
    And clicks on his account number to check "<institution>"


    Examples:
      | institution |
      | TEDAS       |
      | IGDAS       |
      | ASAT        |

