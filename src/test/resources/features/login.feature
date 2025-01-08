Feature: Saucedemo functionality

  Background:
    Given browser is launched and "https://saucedemo.com" page open

  Scenario: Successful login
    When user enters valid username "standard_user" and password "secret_sauce"
    And click on login button
    Then user redirected to product page "https://www.saucedemo.com/inventory.html"

Scenario Outline: Successful login and add product to cart
  When user enters valid username "<username>" and password "<password>"
  And click on login button
  Then user redirected to product page "https://www.saucedemo.com/inventory.html"
  And user adds to cart "<product>"
  And navigate to cart page
  And cart should contain "<product>"

  Examples:
    |username|password|product|
    |standard_user|secret_sauce|Sauce Labs Backpack|
    |visual_user |secret_sauce|Sauce Labs Bolt T-Shirt|






