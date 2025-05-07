Feature:
#  Feature kısımını kendi storynize göre doldurunuz

  Background:
    Given The user navigates to the ParaBank website
    When The user clicks on the register button and then fills the information areas with the customer data and clicks on the submit button
    Then Success message should be displayed
    When The user clicks on the logout button

    When The user fills in the login information and clicks the login button
    Then The user should be redirected to the account overview page

  Scenario:
#  Scenario kısımını kendi storynize göre doldurunuz
