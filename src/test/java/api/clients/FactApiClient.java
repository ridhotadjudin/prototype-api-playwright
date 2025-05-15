package api.clients;

import api.models.FactResponse;
import api.utils.ApiRequestBuilder;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FactApiClient {
    private final ApiRequestBuilder requestBuilder;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FactApiClient() {
        this.requestBuilder = new ApiRequestBuilder();
    }

    public APIResponse getFact() {
        return requestBuilder.getRequestContext()
                .get("/fact");
    }

    public FactResponse getFactAsObject() {
        APIResponse response = getFact();

        try {
            // Check if response is successful
            if (response.ok() && response.status() == 200) {
                String responseText = response.text();
                if (responseText != null && !responseText.isEmpty()) {
                    // Use Jackson's ObjectMapper to deserialize JSON to FactResponse
                    return objectMapper.readValue(responseText, FactResponse.class);
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing response: " + e.getMessage());
            // Depending on your error handling strategy:
            // throw new RuntimeException("Failed to parse API response", e);
        }

        return null;
    }

    public void close() {
        requestBuilder.close();
    }
}
