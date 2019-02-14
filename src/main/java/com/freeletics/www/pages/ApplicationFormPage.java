package com.freeletics.www.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.freeletics.www.common.services.ElementService;
import com.freeletics.www.common.utils.WebDriverWaitFactory;

public class ApplicationFormPage {

    private static final By applicationFormSections = By.xpath("//div[@class='section page-centered " +
            "application-form']");

    private ElementService elementService;
    private WebDriverWaitFactory webDriverWait;

    public ApplicationFormPage(WebDriverWaitFactory webDriverWait) {
        this.webDriverWait = webDriverWait;
        elementService = new ElementService(webDriverWait);
    }

    public boolean isShowedApplicationFormSections() {
        return !getSections().isEmpty();
    }

    private List<WebElement> getSections() {
        return webDriverWait.waitUntilAllElementsVisible(applicationFormSections);
    }

}
