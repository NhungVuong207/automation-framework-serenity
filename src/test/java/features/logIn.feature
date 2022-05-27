@login
Feature: Log in to My Store
  I want to use this template for my feature file

  @login
  Scenario Outline: Register to system and login
    Given Get login page Url
    When Open to Register Page
    And Input to "email" textbox with value "<username>"
    And Input to "passwd" textbox with value "<password>"
    And Click to Login button
    Then Success message displayed with "Welcome to your account"


    Examples:
      | username               | password  |
      | nhungvincom1@gmail.com | Mylove123 |