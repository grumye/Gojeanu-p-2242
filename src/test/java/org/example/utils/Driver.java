package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

    public static WebDriver getAutoLocalDriver() {

        ChromeOptions options = new ChromeOptions();

        // Стабильность для GitHub Actions / Linux
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        // В CI НЕ headless (чтобы записывалось видео)
        boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"));
        if (!isCI) {
            // Если хочешь локально headless — раскомментируй:
            // options.addArguments("--headless=new");
        }

        return new ChromeDriver(options);
    }

    // Локальный метод (если нужен отдельно)
    public static WebDriver getLocalDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }
}