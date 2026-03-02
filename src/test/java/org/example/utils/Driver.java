package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

    public static WebDriver getAutoLocalDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Для стабильности в GitHub Actions / Linux
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        // В CI НЕ headless, чтобы Xvfb мог записать видео
        boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"));
        if (!isCI) {
            // Локально можешь включить headless по желанию:
            // options.addArguments("--headless=new");
        }

        return new ChromeDriver(options);
    }

    // Если надо оставить твой windows-метод — можно, но он не нужен для CI
    public static WebDriver getLocalDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }
}
