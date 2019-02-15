package com.freeletics.www;

import org.testng.annotations.Test;

import io.qameta.allure.Description;

public class JobDetailsPageTests extends BaseTest{

    @Test(description = "Test Job Post Description")
    @Description("Verify career site is displaying the QA Engineer job description properly")
    public void testJobPostDescription(){
        String jobTitle = "QA Engineer (m/f/d)";
        String jobLocation = "Munich";
        int numberOfResponsibilities = 9;
        int numberOfProfileRequirements = 7;

        homePage.open(siteUrl);
        homePage.clickLinkIAgree();
        homePage.scrollToBottom();
        homePage.clickLinkCareers();
        careersPage.clickLinkOpenPositions();
        jobsPage.clickJob(jobTitle, jobLocation);

        jobDetailsPage.verifyTitle(jobTitle);
        jobDetailsPage.verifyJobLocation(jobLocation);
        jobDetailsPage.verifyJobDescriptionIsShown();
        jobDetailsPage.verifyNumberOfResponsibilities(numberOfResponsibilities);
        jobDetailsPage.verifyNumberOfProfileRequirements(numberOfProfileRequirements);

        jobDetailsPage.clickApplyButton();
        applicationFormPage.verifyApplicationFormSectionsIsShown();
    }

}
