Feature: User Login

  Background:
    Given I am on the login page

  Scenario: Successful login with valid credentials
    When I enter a valid email and password
    And I click the Log In button
    Then I should be redirected to the welcome page

  Scenario: Login with incorrect password
    When I enter a valid email and incorrect password
    And I click the Log In button
    Then I should remain on the login page
    And I should see an error message about invalid credentials

  Scenario: Login with nonexistent user
    When I enter an unregistered email
    And I click the Log In button
    Then I should remain on the login page
    And I should see an error message about invalid credentials

  Scenario: Login with empty fields
    When I leave both login fields empty
    And I click the Log In button
    Then I should remain on the login page
    And I should see an error about required fields

  Scenario: Login with invalid email format
    When I enter an invalid email format in login
    And I click the Log In button
    Then I should remain on the login page
    And I should see an error about invalid email in login