@Flights
Feature: test flights

  @Positive
  Scenario Outline: Get all flights on a specific date using valid dates
    Given Search for a flight using <parameter> as a query parameter.
    And Prepare the SearchFlight API URI.
    When Send the API request using GET http method.
    Then Verify that the response code is <status>.
    And I should have the below values in the received response.
    """
    {
      "request": {
        "searchType": "<searchType>"
        }
    }
    """
    Examples:
      | parameter                                    | status | searchType |
      | AMM-JED/2022-08-29/2022-08-30/Economy/1Adult | 200    | Roundtrip  |
      | AMM-JED/2022-08-29/Economy/1Adult            | 200    | Oneway     |

  @Negative
  Scenario Outline: Get all flights using invalid dates
    Given Search for a flight using <parameter> as a query parameter.
    And Prepare the SearchFlight API URI.
    When Send the API request using GET http method.
    Then Verify that the response code is <status>.
    And I should have the below values in the received response.
    """
    {
      "status": <status>
    }
    """
    Examples:
      | parameter                                    | status |
      | AMM-JED/2021-08-29/2021-08-30/Economy/1Adult | 400    |
      | AMM-JED/2021-08-21/Economy/1Adult            | 400    |

  @Positive
  Scenario: Get flights using dynamic dates
    Given Search for round trip flights with origin AMM,  destination JED, depart date with 0 added days, return date with 7 added days, class Economy and 1 adult.
    And Prepare the SearchFlight API URI.
    When Send the API request using GET http method.
    Then Verify that the response code is 200.

  @Positive
  Scenario: Async the search result
    Given Search for round trip flights with origin AMM,  destination JED, depart date with 1 added days, return date with 3 added days, class Economy and 1 adult.
    And Prepare the SearchFlight API URI.
    When Send the API request using GET http method.
    Then Verify that the response code is 200.
    When Prepare the AsyncSearchResult API URI.
    And Prepare the request body.
    And Send the API request using POST http method.
    Then Verify that the response code is 200.
