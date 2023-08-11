Feature: Add items to shopping list

    Scenario: Add several items to the basket

        Given today is "2022-05-22"
        And I add 2 units of "The Hobbit" to my shopping basket
        And I add 5 units of "Breaking Bad" to my shopping basket
        When I check the content of my shopping basket
        Then it should have a date "2022-05-22"
        Then the total is 45 pounds
        And contains the following items
            | item         | quantity | price per unit | total |
            | The Hobbit   | 2        | 5              | 10    |
            | Breaking Bad | 5        | 7              | 35    |

