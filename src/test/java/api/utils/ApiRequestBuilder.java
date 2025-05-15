package api.utils;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

public class ApiRequestBuilder {
    private APIRequestContext requestContext;

    public ApiRequestBuilder() {
        Playwright playwright = PlaywrightManager.getInstance();
        requestContext = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(ConfigManager.getBaseUrl())
                .setTimeout(ConfigManager.getApiTimeout()));
    }

    public APIRequestContext getRequestContext() {
        return requestContext;
    }

    public void close() {
        if (requestContext != null) {
            requestContext.dispose();
        }
    }
}
