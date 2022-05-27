Feature: Search Product Check


  @1
  Scenario: Validate that same product is displayed on searched page with same details which were displayed on T-Shirt's page
    Given Go to login page Url
    When Click to menu T-shirts
    And Get Name of the first product displayed on the page.
    And Enter the same product name in the search bar present on top of page
    And Click search button
    Then Validate that same product is displayed on searched page with same details which were displayed on T-Shirt's page

  @2
  Scenario Outline: "Buy Product" feature of the e-commerce website
    Given Get login page Url
    And Open to Register Page
    And Input to "email" textbox with value "<username>"
    And Input to "passwd" textbox with value "<password>"
    And Click to Login button
    When Click to menu T-shirts
    And The first product displayed on the page.
    And MORE button will be displayed, click on MORE button
    And Input to "quantity_wanted" textbox with value "<quantity>"
    And Input to "group_1" with value "<sizeValue>"
    And Select color
    And Click Add to Cart
    Then Click Proceed to checkout


    Examples:
      | username               | password  | quantity | sizeValue |
      | nhungvincom1@gmail.com | Mylove123 | 2        | 2         |