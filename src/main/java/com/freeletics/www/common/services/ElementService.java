package com.freeletics.www.common.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.freeletics.www.common.utils.WebDriverWaitFactory;

public class ElementService {

    protected WebDriverWaitFactory webDriverWait;

    public ElementService(WebDriverWaitFactory webDriverWait) {
        this.webDriverWait = webDriverWait;
    }

    public void click(By locator) {
        webDriverWait.waitUntilElementClickable(locator).click();
    }

    public void click(WebElement element) {
        webDriverWait.waitUntilElementClickable(element).click();
    }

    public String getText(By locator) {
        return webDriverWait.waitUntilElementVisible(locator).getText();
    }

}
