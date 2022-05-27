Feature: Register to My Store
  I want to use this template for my feature file

  @1
  Scenario Outline: Register to system and login
    Given Get login page Url
    When Open to Register Page
    When Input to Username
    And Click to Submit button on page
    Then Click to MRS radio button
    And Input to "customer_firstname" textbox with value "<FirstName>"
    And Input to "customer_lastname" textbox with value "<LastName>"
    And Input to "email" textbox with value "<Email>"
    And Input to "passwd" textbox with value "<Password>"
    And Input to "days" with value "<dateBirthday>"
    And Input to "months" with value "<monthBirthday>"
    And Input to "years" with value "<yearBirthday>"
    And Input to "firstname" textbox with value "<FirstName>"
    And Input to "lastname" textbox with value "<FirstName>"
    And Input to "address1" textbox with value "<Address>"
    And Input to "city" textbox with value "<City>"
    And Input to "id_state" with value "<State>"
    And Input to "postcode" textbox with value "<ZipCode>"
    And Input to "phone_mobile" textbox with value "<Mobile>"
    And Input to "alias" textbox with value "<Address>"
    And Click to Register button
    Then Success message displayed with "Welcome to your account"


    Examples:
      | FirstName | LastName | Email                  | Password  | dateBirthday | monthBirthday | yearBirthday | Address         | City   | State | ZipCode | Mobile     |
      | Nhung     | Vuong    | nhungvincom1@gmail.com | Mylove123 | 20           | 7             | 1992         | 22 Mai Anh Tuan | Ha Noi | 5     | 00000   | 0987875101 |

  @2
  Scenario: Register to system with invalid email
    Given Get login page Url
    When Open to Register Page
    And Input to Username
    Then Click to Submit button on page
    And Error message "Invalid email address." displayed with "Invalid email address."

  @3
  Scenario: Register to system and login
    Given Get login page Url
    When Open to Register Page
    And Input to Username
    And Click to Submit button on page
    Then Click to Register button without filling information
    Then Error message "You must register at least one phone number." displayed with "You must register at least one phone number."
    And Error message "The Zip/Postal code you've entered is invalid. It must follow this format: 00000" displayed with "It must follow this format: 00000"
    And Error message "This country requires you to choose a State." displayed with "This country requires you to choose a State."
    And Error message "lastname" is displayed
    And Error message "firstname" is displayed
    And Error message "passwd" is displayed
    And Error message "address1" is displayed
    And Error message "city" is displayed

  @4
  Scenario Outline: Register to system and login
    Given Get login page Url
    When Open to Register Page
    When Input to Username
    And Click to Submit button on page
    Then Click to MRS radio button
    And Input to "customer_firstname" textbox with value "<FirstName>"
    And Input to "customer_lastname" textbox with value "<LastName>"
    And Input to "email" textbox with value "<Email>"
    And Input to "passwd" textbox with value "<Password>"
    And Input to "firstname" textbox with value "<FirstName>"
    And Input to "lastname" textbox with value "<FirstName>"
    And Input to "address1" textbox with value "<Address>"
    And Input to "city" textbox with value "<City>"
    And Input to "id_state" with value "<State>"
    And Input to "postcode" textbox with value "<ZipCode>"
    And Input to "phone_mobile" textbox with value "<Mobile>"
    And Click to Register button
    Then Error message "lastname" is displayed
    And Error message "firstname" is displayed
    And Error message "passwd" is displayed
    And Error message "email" is displayed
    And Error message "phone_mobile" is displayed
    And Error message "The Zip/Postal code you've entered is invalid. It must follow this format: 00000" displayed with "It must follow this format: 00000"

    Examples:
      | FirstName | LastName | Email   | Password | Address | City | State | ZipCode | Mobile  |
      | 123       | 456      | sfasfdv | qqs      | adcs    | facf | 5     | amdj    | adrfvda |


