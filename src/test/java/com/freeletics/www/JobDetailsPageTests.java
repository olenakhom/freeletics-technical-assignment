package com.freeletics.www;

import org.testng.Assert;
import org.testng.annotations.Test;

public class JobDetailsPageTests extends BaseTest{

    @Test
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

        Assert.assertEquals(jobDetailsPage.getTitle(), jobTitle, "Job title");
        Assert.assertEquals(jobDetailsPage.getJobLocation(), jobLocation, "Job Location");
        Assert.assertTrue(jobDetailsPage.isShowedDescription(), "Job Description is not showed");
        Assert.assertEquals(jobDetailsPage.getResponsibilities().size(), numberOfResponsibilities, "Number of responsibilities");
        Assert.assertEquals(jobDetailsPage.getProfiles().size(), numberOfProfileRequirements, "Number of profile requirements");

        jobDetailsPage.clickApplyButton();
        Assert.assertTrue(applicationFormPage.isShowedApplicationFormSections(), "Sections on Application page are not showed");
    }

}
