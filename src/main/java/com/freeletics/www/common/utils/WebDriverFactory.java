package com.freeletics.www.common.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class WebDriverFactory {

    public WebDriver getDriver(String runEnv, String rDriverUrl) throws MalformedURLException {
        if ("local".equals(runEnv)) {
            setupChromeDriverManager();
            return new ChromeDriver();
        } else if ("remote".equals(runEnv)) {
            return new RemoteWebDriver(new URL(rDriverUrl), getChromeDesiredCapabilities());
        } else {
            throw new IllegalArgumentException(String.format("No such run enviroment - '%s'.", runEnv));
        }
    }

    private void setupChromeDriverManager() {
        ChromeDriverManager chromeDriverManager = ChromeDriverManager.getInstance();
        chromeDriverManager.setup();
    }

    private DesiredCapabilities getChromeDesiredCapabilities() {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        capability.setCapability("enableVNC", true);
        capability.setCapability("enableVideo", false);
        return capability;
    }

}
