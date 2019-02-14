package com.freeletics.www.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.freeletics.www.common.services.ElementService;
import com.freeletics.www.common.utils.WebDriverWaitFactory;

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

    public String getTitle() {
        return elementService.getText(title);
    }

    public String getJobLocation() {
        return elementService.getText(jobLocation);
    }

    public List<String> getJobDescriptionText() {
        List<String> descriptions = new ArrayList<>();
        getJobDescriptionElements().stream()
                .forEach(element -> {
                    descriptions.add(element.getText());
                });
        return descriptions;
    }

    public boolean isShowedDescription() {
        boolean isShowed = false;
        List<String> description = getJobDescriptionText();
        if (!description.isEmpty()) {
            isShowed = description.stream()
                    .anyMatch(item -> !item.isEmpty());
        }
        return isShowed;
    }

    public List<String> getResponsibilities() {
        return webDriverWait.waitUntilAllElementsPresent(responsibilitiesList).stream()
                .map(item -> item.getText())
                .collect(Collectors.toList());
    }

    public List<String> getProfiles() {
        return webDriverWait.waitUntilAllElementsPresent(profileList).stream()
                .map(item -> item.getText())
                .collect(Collectors.toList());
    }

    public void clickApplyButton() {
        elementService.click(buttonApply);
    }

    private List<WebElement> getJobDescriptionElements() {
        return webDriverWait.waitUntilAllElementsPresent(jobDescriptionList);
    }

}
