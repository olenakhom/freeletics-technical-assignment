package com.freeletics.www.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.freeletics.www.common.models.JobPostingDto;
import com.freeletics.www.common.services.ElementService;
import com.freeletics.www.common.utils.WebDriverWaitFactory;

import io.qameta.allure.Step;

public class JobsPage {

    private Logger logger = LoggerFactory.getLogger(JobsPage.class);

    private static final By listJobs = By.xpath("//li[@itemscope='itemscope']");
    private static final By title = By.xpath(".//h3[@itemprop='title']");
    private static final By jobLocation = By.xpath(".//span[@itemprop='jobLocation']");
    private static final By employmentType = By.xpath(".//span[@itemprop='employmentType']");
    private static final By url = By.xpath(".//a[@itemprop='url']");

    private ElementService elementService;
    private WebDriverWaitFactory webDriverWait;

    public JobsPage(WebDriverWaitFactory webDriverWait) {
        this.webDriverWait = webDriverWait;
        this.elementService = new ElementService(webDriverWait);
    }

    @Step("Get all jobs posts")
    public List<JobPostingDto> getAllJobs() {
        List<JobPostingDto> jobs = new ArrayList<>();
        getAllJobsElements().stream().forEach(jobElement -> {
            JobPostingDto job = new JobPostingDto();
            job.setTitle(jobElement.findElement(title).getText());
            job.setJobLocation(jobElement.findElement(jobLocation).getText());
            job.setEmploymentType(jobElement.findElement(employmentType).getText());
            job.setUrl(jobElement.findElement(url));
            jobs.add(job);
        });
        return jobs;
    }

    @Step("Click on job link with title {0} and job location {1}")
    public void clickJob(String title, String jobLocation) {
        WebElement jobUrl = getJobUrl(title, jobLocation);
        if (null == jobUrl) {
            logger.info("Job by title {} and location {} wasn't found", title, jobLocation);
        }
        elementService.click(jobUrl);
    }

    private List<WebElement> getAllJobsElements() {
        return webDriverWait.waitUntilAllElementsPresent(listJobs);
    }

    private WebElement getJobUrl(String title, String jobLocation) {
        return getAllJobs().stream()
                .filter(item -> item.getTitle().equals(title) && item.getJobLocation().equals(jobLocation))
                .map(item -> item.getUrl())
                .findAny()
                .orElse(null);
    }

}
