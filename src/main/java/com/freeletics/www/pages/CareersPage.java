package com.freeletics.www.pages;

import org.openqa.selenium.By;

import com.freeletics.www.common.services.ElementService;
import com.freeletics.www.common.utils.WebDriverWaitFactory;

import io.qameta.allure.Step;

public class CareersPage {
    private static final By linkOpenPositions = By.xpath("//*[contains(text(),'open position')]");

    private ElementService elementService;
    private WebDriverWaitFactory webDriverWait;

    public CareersPage(WebDriverWaitFactory webDriverWait) {
        this.webDriverWait = webDriverWait;
        elementService = new ElementService(webDriverWait);
    }

    @Step("Click on link Open Positions")
    public void clickLinkOpenPositions() {
        elementService.click(linkOpenPositions);
    }

}
