Feature: User Signup

  Background:
    Given I am on the signup page

  Scenario: Successful signup with valid data
    When I enter a valid name, email, and password
    And I click the Sign Up button
    Then I should be redirected to the login page

  Scenario: Signup with empty fields
    When I leave all signup fields empty
    And I click the Sign Up button
    Then I should remain on the signup page
    And I should see error messages

  Scenario: Signup with already registered email
    When I enter an existing email and password
    And I click the Sign Up button
    Then I should remain on the signup page
    And I should see an error about the existing account

  Scenario: Signup with mismatched passwords
    When I enter mismatched passwords
    And I click the Sign Up button
    Then I should remain on the signup page
    And I should see an error about password mismatch

  Scenario: Signup with invalid email
    When I enter an invalid email format in signup
    And I click the Sign Up button
    Then I should remain on the signup page
    And I should see an error about invalid email in signup