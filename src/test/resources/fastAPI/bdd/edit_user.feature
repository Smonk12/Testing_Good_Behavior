Feature: Admin edits an existing user

  Background:
    Given I am logged in as an admin
    And I am on the Admin Page

  Scenario: Admin edits user successfully
    When I click on the "Actions" menu for a user
    And I select "Edit User"
    And I modify the user information with valid data
    And I click on the "Save" button
    Then I should see a message saying "User updated successfully"
    And the user information in the list should be updated

  Scenario: Admin cannot update user with existing email
    When I click on the "Actions" menu for a user
    And I select "Edit User"
    And I change the email to one that already exists
    And I click on the "Save" button
    Then I should see an error message saying "User with this email already exists"
    And the user information should remain unchanged

  Scenario: Admin cancels editing a user
    When I click on the "Actions" menu for a user
    And I select "Edit User"
    And I change the user information
    And I click on the "Cancel" button
    Then the form should close
    And no changes should be applied to the user list

  Scenario: Admin cannot save user with mismatching passwords
    When I click on the "Actions" menu for a user
    And I select "Edit User"
    And I fill in mismatching passwords
    Then I should see a warning message saying "Passwords do not match"
    And the "Save" button should be disabled

  Scenario: Admin changes user status
    When I click on the "Actions" menu for a user
    And I select "Edit User"
    And I toggle the "Active" checkbox
    And I click on the "Save" button
    Then I should see a message saying "User updated successfully"
    And the user status in the list should reflect the change
