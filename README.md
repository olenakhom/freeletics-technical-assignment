# Freeletics technical assignment
QA Engineer - Technical Test Exercise

# Part 1

### Instruments were used
1. [Java](https://www.oracle.com/java/)
2. [Maven](https://maven.apache.org/)
3. [Selenium WebDriver](https://www.seleniumhq.org/projects/webdriver/)
4. [TestNg](https://testng.org)
5. [Allure](http://allure.qatools.ru/)
6. [Appium](http://appium.io/)

### Required installation
1. [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download)
3. [Allure commandline for generation test report](https://docs.qameta.io/allure/#_installing_a_commandline)

### IntelliJ IDEA settings
1. Open File --> New --> Project from Version control --> Git
2. Clone from repository URL https://github.com/olenakhom/freeletics-technical-assignment.git
3. Open File --> Project Structure --> Project
4. Select java version 1.8 from Project SDK dropdown
5. Select 8-Lambdas, annotations from Project language level dropdown
6. Open File --> Project Structure --> Modules
7. Select 8-Lambdas, annotations Language level on Source tab


### Test Execution
Run tests from command line (maven installation is required)
```
mvn clean -U -DtestngFile=test-suite.xml -DpropFile=prod.properties test
```
or
run tests from embedded maven command line from IntelliJ IDEA (Execute Maven Goal button)
```
clean -U -DtestngFile=test-suite.xml -DpropFile=prod.properties test
```
If your appium server and android device are set up, you can run test on android app by:
1. Change android version in prod-mobile.properties file
2. Run test by command
```
clean -U -DtestngFile=test-suite.xml -DpropFile=prod-mobile.properties test
```

### Allure test report generation
This is already enough to see the Allure report in one command:
```
allure serve /home/path/to/project/allure-results
```
If report generated successfully, report page should be opened automatically in browser:
![Allure Report](src/test/resources/allure_report.png?raw=true "Allure Report Example")

# Part 2

Which parts of the code you provided would need to be adapted to run the same
tests in an Android phone, using the same browser, in the tool you chose?
```
Using Appium for mobile automation minimizes number of changes in code. 
Main changes are:
1. Adding appium dependency into Maven repository:
<dependency>
            <groupId>io.appium</groupId>
            <artifactId>java-client</artifactId>
            <version>${appium.version}</version>
</dependency>
2. Initialization of WebDriver. The example of changes is provided below:
DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"My Android");
        capabilities.setCapability(MobileCapabilityType.VERSION, "6.0.1");
WebDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities); 

where http://localhost:4723/wd/hub - Appium server URL
```
How could we run these tests in a physical android phone connected to your laptop?
Does the laptop need any extra software/hardware to run them properly?
```
To run tests on real Android device following installations are needed 

For laptop:
1. Android SDK
2. Node.js
3. Appium
4. Appium client 

For Android device:
1. Chrome browser of version >= 70.0

Settings:
1. Android device's Developer options is turned on, USB debuging option is turned on
2. Android device is connected to laptop via USB
3. Appium server is started.
```
What would be the main changes if the website was now part of a hybrid android
app?
```
The same tests for website cannot be used for testing hybrid android app because:
1. WebDriver will be changed to AppiumDriver
2. DesiredCapabilities will be the same as for Native app, For example:
DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"My Android");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "activity");
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "package");
3. The method driver.context("CONTEXT_NAME") will be used for switching between Native and Web view. 
Method driver.getContextHandles returns set of all available views in the app, 
and method driver.getContext() returns current view.
4. Locators should be taken via Browser for Web view.
5. Locators should be taken via UI Automator Viewer for Native view.
6. App installation is required on mobile device 
```
And if it was a native app?
```
1. WebDriver will be changed to AppiumDriver
2. Example of DesiredCapabilities for native app:
DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"My Android");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "activity");
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "package");
3. Locators should be taken via UI Automator Viewer.
4. App installation is required on mobile device
```
