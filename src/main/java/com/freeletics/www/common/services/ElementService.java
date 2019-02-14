package com.freeletics.www.common.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.freeletics.www.common.utils.WebDriverWaitFactory;

public class ElementService {

    protected WebDriverWaitFactory explicitWait;

    public ElementService(WebDriverWaitFactory explicitWait) {
        this.explicitWait = explicitWait;
    }

    public void click(By locator) {
        explicitWait.waitUntilElementClickable(locator).click();
    }

    public void click(WebElement element) {
        explicitWait.waitUntilElementClickable(element).click();
    }

    public String getText(By locator) {
        return explicitWait.waitUntilElementVisible(locator).getText();
    }

    public String getAttribute(By locator, String attribute) {
        return explicitWait.waitUntilElementVisible(locator).getAttribute(attribute);
    }

    public String getCssValue(By locator, String cssStyle) {
        return explicitWait.waitUntilElementVisible(locator).getCssValue(cssStyle);
    }
}
