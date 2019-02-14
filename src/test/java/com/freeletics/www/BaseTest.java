package com.freeletics.www;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.freeletics.www.common.utils.WebDriverFactory;
import com.freeletics.www.common.utils.WebDriverWaitFactory;
import com.freeletics.www.config.MainConfig;
import com.freeletics.www.pages.ApplicationFormPage;
import com.freeletics.www.pages.CareersPage;
import com.freeletics.www.pages.HomePage;
import com.freeletics.www.pages.JobDetailsPage;
import com.freeletics.www.pages.JobsPage;

@ContextConfiguration(classes = MainConfig.class, loader = AnnotationConfigContextLoader.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

    private static final long IMPLICITLY_WAIT_TIMEOUT = 5;
    private static final long PAGE_LOAD_TIMEOUT = 45;

    @Value("${site.base.url}")
    protected String siteUrl;

    @Value("${run.env}")
    private String runEnv;

    @Value("${run.server.url}")
    private String serverUrl;

    private WebDriverWaitFactory webDriverWait;

    protected HomePage homePage;
    protected CareersPage careersPage;
    protected JobsPage jobsPage;
    protected JobDetailsPage jobDetailsPage;
    protected ApplicationFormPage applicationFormPage;
    protected WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        initialize();
    }

    @AfterMethod
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    private void initialize() {
        webDriver = initWebDriver();
        webDriverWait = new WebDriverWaitFactory(webDriver);
        homePage = new HomePage(webDriverWait);
        careersPage = new CareersPage(webDriverWait);
        jobsPage = new JobsPage(webDriverWait);
        jobDetailsPage = new JobDetailsPage(webDriverWait);
        applicationFormPage = new ApplicationFormPage(webDriverWait);
    }

    private WebDriver initWebDriver() {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        try {
            webDriver = webDriverFactory.getDriver(runEnv, serverUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        webDriver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        return webDriver;
    }

}
