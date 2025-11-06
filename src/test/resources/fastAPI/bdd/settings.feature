Feature: User Settings

  Background:
    Given I am logged in and on the user settings page

  Scenario: View My Profile tab
    Then I should see full name and email displayed

  Scenario: Change password with valid data
    When I navigate to the Password tab
    And I enter current password, new password, and confirm new password
    And I click Save in password form
    Then I should see a success message

  Scenario: Change password with incorrect current password
    When I navigate to the Password tab
    And I enter wrong current password and valid new passwords
    And I click Save in password form
    Then I should see an error about incorrect current password

  Scenario: Change password with mismatched new passwords
    When I navigate to the Password tab
    And I enter valid current password but mismatched new passwords
    And I click Save in password form
    Then I should see an error about passwords not matching

  Scenario: Toggle Appearance to Dark Mode
    When I navigate to the Appearance tab
    And I select Dark Mode
    Then the page should switch to dark theme

  Scenario: Delete account and verify redirection
    When I log out and create a temporary user
    And I log in as that temporary user
    And I navigate to the Danger zone tab
    And I click the Delete button
    And I confirm account deletion
    Then I should be redirected to the login page after deletion

