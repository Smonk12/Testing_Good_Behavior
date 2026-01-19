Feature: Admin Page User Management

  Background:
    Given I am logged in as an admin
    And I am on the Admin Page

  Scenario: Admin adds a new user successfully
    When I click on the "Add User" button
    And I fill out the add user form with valid information
    Then a new user should be added successfully
    And the new user should appear in the user list

  Scenario: Admin can add new user without full name
    When I click on the "Add User" button
    And I fill in the "Email" field with "newuser@example.com"
    And I leave the "Full Name" field empty
    And I fill in the "Password" fields correctly
    Then the user should be created successfully

  Scenario: Admin cannot add a user with an already existing email
    When I click the "Add user" button
    And I fill out the form with an email that already exists
    And I submit the form
    Then I should see an error message saying the email already exists
    And the new user should not be added to the list

  Scenario: Admin cannot add new user without email
    When I click on the "Add User" button
    And I leave the "Email" field empty
    And I fill in the "Full Name" field with "Test User"
    And I fill in the "Password" fields correctly
    Then I should see a warning message saying "Email is required"
    And the "Save" button should be disabled

  Scenario: Admin cannot add new user with missing password
    When I click on the "Add user" button
    And I fill the form without password
    Then the "Save" button should be disabled

  Scenario: Admin cannot add new user with mismatching passwords
    When I click on the "Add User" button
    And I fill in the "Email" field with "testuser@example.com"
    And I fill in the "Full Name" field with "Test User"
    And I fill in the "Password" field with "Password123"
    And I fill in the "Confirm Password" field with "DifferentPassword"
    Then I should see a warning message saying "Passwords do not match"
    And the "Save" button should be disabled

  Scenario: Admin cannot add new user with password shorter than 8 characters
    When I click on the "Add User" button and fill out the user form with a password shorter than 8 characters
    Then I should see a visible warning saying "Password must be at least 8 characters"
    And the "Save" button should be disabled

  Scenario: Admin cancels adding a new user
    When I click on the "Add User" button and fill out the user form with valid information
    And I click on the "Cancel" button
    Then the form should close
    And the new user should not appear in the user list

