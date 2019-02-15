# freeletics-technical-assignment
QA Engineer - Technical Test Exercise

1. Allure installation

Installing a commandline
Several options for Allure installation are currently supported:

1.1.1. Linux
For debian-based repositories a PPA is provided:

sudo apt-add-repository ppa:qameta/allure
sudo apt-get update
sudo apt-get install allure

1.1.2. Mac OS X
For Mas OS, automated installation is available via Homebrew

brew install allure

1.1.3. Windows
For Windows, Allure is available from the Scoop commandline-installer.
To install Allure, download and install Scoop and then execute in the Powershell:

scoop install allure

Check the installation
Execute allure --version in console to make sure that allure is now available:

$ allure --version

2. Test Execution

Run tests from command line (maven installation is required)
mvn clean -U -DtestngFile=test-suite.xml -DpropFile=prod.properties test

Run tests from embedded maven command line from IntelliJ IDEA
clean -U -DtestngFile=test-suite.xml -DpropFile=prod.properties test

3. Allure report generation
This is already enough to see the Allure report in one command:

allure serve /home/path/to/project/allure-results