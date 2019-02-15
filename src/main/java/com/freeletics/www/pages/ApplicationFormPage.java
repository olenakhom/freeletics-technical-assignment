package com.freeletics.www.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.freeletics.www.common.services.ElementService;
import com.freeletics.www.common.utils.WebDriverWaitFactory;

import io.qameta.allure.Step;

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

    @Step("Verify application form sections are shown")
    public void verifyApplicationFormSectionsIsShown(){
        Assert.assertTrue(isShowedApplicationFormSections(), "Sections on Application page are not shown");
    }

}
