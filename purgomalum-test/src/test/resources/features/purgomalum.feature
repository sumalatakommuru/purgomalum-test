Feature: PurgoMalum

  Scenario Outline: Retrieving details using mandatory fields
    Given I request purgomalum service to get <text> using <contentType>
    Then I should get success response code
    And response should contains <text> with <contentType>

    Examples:
      | text                          | contentType |
      | this is some json test input  | json        |
      | this is some xml test input   | xml         |
      | this is some plain test input | plain       |

  Scenario Outline: Retrieving details with containsprofanity as resource url
    Given I request purgomalum service value to get containsprofanity value <text> using <contentType>
    Then I should get success response code
    And response should return <containsprofanityValue>

    Examples:
      | text                          | contentType | containsprofanityValue |
      | this is some json test input  | json        | false                  |
      | this is some xml test input   | xml         | false                  |
      | this is some plain test input | plain       | false                  |

  Scenario Outline: find and replace with optional parameters Add and Fill Text
    Given I request purgomalum service text <text> to find text <find> and replace with <replace> using <contentType>
    Then I should get success response code
    And response should contains <replaceText> with <contentType>

    Examples:
      | text                          | contentType | find  | replace         | replaceText                             |
      | this is some json test input  | json        | json  | [replace json]  | this is some [replace json] test input  |
      | this is some xml test input   | xml         | xml   | [replace xml]   | this is some [replace xml] test input   |
      | this is some plain test input | plain       | plain | [replace plain] | this is some [replace plain] test input |

  Scenario Outline: find and fill with another character
    Given I request purgomalum service text <text> to find text <find> and fill with <fillCharacter> using <contentType>
    Then I should get success response code
    And response should contains <replaceText> with <contentType>

    Examples:
      | text                         | contentType | find  | fillCharacter | replaceText                  |
      | this is some xml test input  | xml         | test  |               | this is some xml **** input  |
      | this is some json test input | json        | input | _             | this is some json test _____ |
      | this is some xml test input  | xml         | test  | =             | this is some xml ==== input  |


  Scenario Outline: Replace with another text more than maximum allowed characters
    Given I request purgomalum service text <text> to replace with <replaceText> using <contentType>
    Then I should get success response code
    And response should contains <errorMessage> with <contentType>

    Examples:
      | text                          | contentType | replaceText                             | errorMessage                                          |
      | this is some xml test input   | xml         | this is curiously long replacement text | User Replacement Text Exceeds Limit of 20 Characters. |
