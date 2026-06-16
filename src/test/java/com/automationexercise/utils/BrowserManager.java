package com.automationexercise.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BrowserManager {

    private BrowserManager() {}

    public static WebDriver doBrowserSetup() throws IOException {
        String browserName = PropertiesLoader.loadProperty("browser.name");
        String browser = (browserName != null ? browserName : "chrome").toLowerCase().trim();

        return switch (browser) {
            case "chrome" -> createChromeDriver();
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: '" + browser + "'. Supported: chrome");
        };
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = buildChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        applyCommonSettings(driver);
        return driver;
    }

    private static ChromeOptions buildChromeOptions() {
        ChromeOptions options = new ChromeOptions();


        if (Boolean.parseBoolean(System.getenv("CI"))) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }

        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        return options;
    }

    private static void applyCommonSettings(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }
}