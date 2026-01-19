Feature: Item management on the Items page

  As a user
  I want to create, edit, and delete items
  So that I can manage my item list effectively

  Scenario: Create a new item
    Given the user is on the Items page
    When the user clicks the "Add Item" button
    And the user enters "Magic Wand" as title
    And the user enters "A powerful item for spellcasting" as description
    And the user clicks the "Save" button
    Then a new item should appear in the list with title "Magic Wand"
    And the item should have a generated ID

#  Scenario: Edit an existing item
#    Given the user is on the Items page
#    And an item with title "Magic Wand" exists
#    When the user clicks the "Edit" button for "Magic Wand"
#    And the user changes the title to "Enchanted Wand"
#    And the user clicks the "Save" button
#    Then the item list should contain "Enchanted Wand"
#    And should not contain "Magic Wand"
#
#  Scenario: Delete an existing item
#    Given the user is on the Items page
#    And an item with title "Enchanted Wand" exists
#    When the user clicks the "Delete" button for "Enchanted Wand"
#    Then the item list should not contain "Enchanted Wand"