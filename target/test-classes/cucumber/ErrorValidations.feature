@tag
  Feature: Error Validation
    I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in with username <email> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | email  							|     password			|
      | qaEngineer@test.com 	|     Engineer@17 	|
      | devEngineer@test.com 	|     Engineer@17 	|
