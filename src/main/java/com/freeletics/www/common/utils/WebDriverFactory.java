package com.freeletics.www.common.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class WebDriverFactory {

    @Value("${mobile.os.version}")
    private String mobileOsVersion;

    public WebDriver getDriver(String runEnv, String rDriverUrl) throws MalformedURLException {
        if ("local".equals(runEnv)) {
            setupChromeDriverManager();
            return new ChromeDriver();
        }
        else if ("remote".equals(runEnv)) {
            return new RemoteWebDriver(new URL(rDriverUrl), getChromeCapabilities());
        }
        else if ("mobile".equals(runEnv)) {
            return new AndroidDriver(new URL(rDriverUrl), getAndroidChromeCapabilities());
        }
        else {
            throw new IllegalArgumentException(String.format("No such run enviroment - '%s'.", runEnv));
        }
    }

    private void setupChromeDriverManager() {
        ChromeDriverManager chromeDriverManager = ChromeDriverManager.getInstance();
        chromeDriverManager.setup();
    }

    private DesiredCapabilities getChromeCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        return capabilities;
    }

    private DesiredCapabilities getAndroidChromeCapabilities(){
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"My Android");
        capabilities.setCapability(MobileCapabilityType.VERSION, mobileOsVersion);
        return capabilities;
    }

}
