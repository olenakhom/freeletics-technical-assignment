package com.freeletics.www.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.freeletics.www.common.services.ElementService;
import com.freeletics.www.common.utils.WebDriverWaitFactory;

import io.qameta.allure.Step;

public class JobDetailsPage {

    private static final By title = By.xpath("//h1[@itemprop='title']");
    private static final By jobLocation = By.xpath("//span[@itemprop='jobLocation']");
    private static final By jobDescriptionList = By.xpath("//div[@itemprop='description']//span");
    private static final By responsibilitiesList = By.xpath("//section[1]//li");
    private static final By profileList = By.xpath("//section[2]//li");
    private static final By buttonApply = By.xpath("//a[contains(@href, '/apply')]");

    private ElementService elementService;
    private WebDriverWaitFactory webDriverWait;

    public JobDetailsPage(WebDriverWaitFactory webDriverWait) {
        this.webDriverWait = webDriverWait;
        this.elementService = new ElementService(webDriverWait);
    }

    @Step("Get job title")
    public String getTitle() {
        return elementService.getText(title);
    }

    @Step("Get job location")
    public String getJobLocation() {
        return elementService.getText(jobLocation);
    }

    @Step("Get job description")
    public List<String> getJobDescriptionText() {
        List<String> descriptions = new ArrayList<>();
        getJobDescriptionElements().stream()
                .forEach(element -> {
                    descriptions.add(element.getText());
                });
        return descriptions;
    }

    @Step("Is description shown")
    public boolean isShowedDescription() {
        boolean isShowed = false;
        List<String> description = getJobDescriptionText();
        if (!description.isEmpty()) {
            isShowed = description.stream()
                    .anyMatch(item -> !item.isEmpty());
        }
        return isShowed;
    }

    @Step("Get job responsibilities")
    public List<String> getResponsibilities() {
        return webDriverWait.waitUntilAllElementsPresent(responsibilitiesList).stream()
                .map(item -> item.getText())
                .collect(Collectors.toList());
    }

    @Step("Get job profile")
    public List<String> getProfiles() {
        return webDriverWait.waitUntilAllElementsPresent(profileList).stream()
                .map(item -> item.getText())
                .collect(Collectors.toList());
    }

    @Step("Click on Apply button")
    public void clickApplyButton() {
        elementService.click(buttonApply);
    }

    private List<WebElement> getJobDescriptionElements() {
        return webDriverWait.waitUntilAllElementsPresent(jobDescriptionList);
    }

    @Step("Verify job title is {0}")
    public void verifyTitle(String expectedTitle){
        Assert.assertEquals(getTitle(), expectedTitle, "Job title");
    }

    @Step("Verify job location is {0}")
    public void verifyJobLocation(String expectedJobLocation){
        Assert.assertEquals(getJobLocation(), expectedJobLocation, "Job Location");
    }

    @Step("Verify job description is shown")
    public void verifyJobDescriptionIsShown(){
        Assert.assertTrue(isShowedDescription(), "Job Description is not showed");
    }

    @Step("Verify number of responsibilities is {0}")
    public void verifyNumberOfResponsibilities(int expectedNumberOfResponsibilities){
        Assert.assertEquals(getResponsibilities().size(), expectedNumberOfResponsibilities, "Number of responsibilities");
    }

    @Step("Verify number of profile requirements is {0}")
    public void verifyNumberOfProfileRequirements(int expectedNumberOfProfileRequirements){
        Assert.assertEquals(getProfiles().size(), expectedNumberOfProfileRequirements, "Number of profile requirements");
    }

}
