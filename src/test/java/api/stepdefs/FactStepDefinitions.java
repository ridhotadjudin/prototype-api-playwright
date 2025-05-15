package api.stepdefs;

import api.clients.FactApiClient;
import api.models.FactResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.microsoft.playwright.APIResponse;
import static org.junit.jupiter.api.Assertions.*;

public class FactStepDefinitions {
    private FactApiClient FactApiClient;
    private APIResponse response;
    private FactResponse FactResponse;

    @Given("I have a valid Fact")
    public void i_have_a_valid_fact_id() {
        FactApiClient = new FactApiClient();
    }

    @When("I send a GET request to the Fact endpoint")
    public void i_send_a_get_request_to_the_fact_endpoint() {
        response = FactApiClient.getFact();
    }

    @When("I request Fact details")
    public void i_request_fact_details() {
        FactResponse = FactApiClient.getFactAsObject();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.status());
    }

    @Then("the response should contain valid Fact details")
    public void the_response_should_contain_valid_fact_details() {
        assertNotNull(FactResponse);
        assertTrue(FactResponse.getFact() != null && !FactResponse.getFact().isEmpty());
        assertTrue(FactResponse.getLength() > 0);
    }

    @Then("the response should contain fields: fact, length")
    public void the_response_should_contain_required_fields() {
        assertNotNull(FactResponse);
        assertTrue(FactResponse.getLength() > 0);
        assertNotNull(FactResponse.getFact());
    }
}
