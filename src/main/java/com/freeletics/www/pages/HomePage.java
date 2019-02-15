package com.freeletics.www.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.freeletics.www.common.services.ElementService;
import com.freeletics.www.common.utils.WebDriverWaitFactory;

import io.qameta.allure.Step;

public class HomePage {

    private static final By linkCareers = By.linkText("Careers");
    private static final By buttonIAgreeCookies = By.xpath("//div[@class='_3_1pE_A23IPm']/button/span");

    private ElementService elementService;
    private WebDriverWaitFactory webDriverWait;

    public HomePage(WebDriverWaitFactory webDriverWait) {
        this.webDriverWait = webDriverWait;
        this.elementService = new ElementService(webDriverWait);
    }

    @Step("Click on link Careers")
    public void clickLinkCareers() {
        elementService.click(linkCareers);
    }

    @Step("Click on link I Agree by cookies policy")
    public void clickLinkIAgree() {
        elementService.click(buttonIAgreeCookies);
    }

    @Step("Open page {0}")
    public void open(String url) {
        webDriverWait.getWebDriver().get(url);
    }

    @Step("Scroll page to the bottom")
    public void scrollToBottom() {
        ((JavascriptExecutor) webDriverWait.getWebDriver())
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}
