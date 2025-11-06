Feature: Admin deletes an existing user

  Background:
    Given I am logged in as an admin
    And I am on the Admin Page

  Scenario: Admin cancels user deletion
    When I click on the "Actions" menu for a user
    And I select "Delete User"
    And I click on the "Cancel" button
    Then the confirmation dialog should close
    And the user should remain visible in the user list

  Scenario: Admin confirms user deletion
    When I click on the "Actions" menu for a user
    And I select "Delete User"
    And I click on the "Delete" button
    Then I should see a success message saying "User was deleted successfully"
    And the user should no longer be visible in the user list