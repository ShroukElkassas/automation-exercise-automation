package com.automationexercise.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * DriverFactory — centralized, thread-safe WebDriver creation.
 * Default browser: Chrome. Override with: -Dbrowser=chrome
 */
public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(createDriver());
        }
        return driverThreadLocal.get();
    }

    private static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase().trim();
        return switch (browser) {
            case "chrome" -> createChromeDriver();
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: '" + browser + "'. Supported values: chrome");
        };
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = buildChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        applyCommonSettings(driver);
        return driver;
    }

    private static ChromeOptions buildChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Window
        options.addArguments("--start-maximized");

        // Notifications & popups
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        // Ads / overlays / automation detection
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Notification prefs via Chrome profile
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Stability (especially for CI / Linux)
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        return options;
    }

    private static void applyCommonSettings(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }

    /**
     * Safely quits the driver and clears ThreadLocal.
     * Safe to call even if driver was never initialized.
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("[DriverFactory] driver.quit() warning: " + e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}
