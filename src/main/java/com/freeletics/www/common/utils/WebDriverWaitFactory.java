package com.freeletics.www.common.utils;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfAllElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("test")
public class WebDriverWaitFactory {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private Logger log = LoggerFactory.getLogger(WebDriverWaitFactory.class);
    private static final int TIME_OUT = 50;
    private static final long SLEEP_TIME_OUT = 100L;

    @Autowired
    public WebDriverWaitFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, TIME_OUT, SLEEP_TIME_OUT);

    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public boolean waitUntilElementIsInvisible(By locator) {
        return getWebDriverWait().until(invisibilityOfElementLocated(locator));
    }

    public boolean waitUntilElementsAreInvisible(List<WebElement> elements) {
        return getWebDriverWait().until(invisibilityOfAllElements(elements));
    }

    public boolean waitUntilUrlNotChanged(String url) {
        return getWebDriverWait().until(urlToBe(url));
    }

    public boolean waitUntilUrlContains(String url) {
        return getWebDriverWait().until(urlContains(url));
    }

    public WebElement waitUntilElementVisible(By locator) {
        return getWebDriverWait().until(visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilElementPresent(By locator) {
        return getWebDriverWait().until(presenceOfElementLocated(locator));
    }

    public WebElement waitUntilElementClickable(By locator) {
        return getWebDriverWait().until(elementToBeClickable(locator));
    }

    public WebElement waitUntilElementClickable(WebElement webElement) {
        return getWebDriverWait().until(elementToBeClickable(webElement));
    }

    public List<WebElement> waitUntilAllElementsVisible(By locator) {
        return getWebDriverWait().until(visibilityOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitUntilAllElementsPresent(By locator) {
        return getWebDriverWait().until(presenceOfAllElementsLocatedBy(locator));
    }

    public void waitUntilUrlChanged(String urlBefore) {
        ExpectedCondition<Boolean> windowCondition = driver -> !driver.getCurrentUrl().equals(urlBefore);
        getWebDriverWait().until(windowCondition);
    }

}
