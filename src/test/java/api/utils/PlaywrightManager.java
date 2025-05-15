package api.utils;

import com.microsoft.playwright.Playwright;

public class PlaywrightManager {
    private static Playwright playwright;

    private PlaywrightManager() {
        // Private constructor to prevent instantiation
    }

    public static Playwright getInstance() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        return playwright;
    }

    public static void close() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
