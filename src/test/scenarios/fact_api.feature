Feature: Product API Endpoint Testing
  As an API consumer
  I want to verify the product endpoint
  So that I can ensure it's working correctly

  Scenario: Retrieve product details with valid data
    Given I have a valid Fact
    When I send a GET request to the Fact endpoint
    Then the response status code should be 200
#    And the response should contain valid Fact details

  Scenario: Verify product details structure
    Given I have a valid Fact
    When I request Fact details
    Then the response should contain fields: fact, length