# webTestAutomation

## Below are the tools that have used for automation framework.

1)Java Programming language

2)Selenium webdriver for web UI automation

3)Gradle as build tool

4)TestNg for test execution flow

5)Page Object Model design pattern

6)log4j for logging purpose

7)Extent reports for reporting

## Test Scenarios:

1)Search and add product to cart

2)Increment the product quantity and validate prices

3)select the product from feature category

4)select the same product with different sizes

## Run Automation :

1)command to execute the entire project :  ./gradlew clean build test

2)run testng.xml in src/test/resources

3)All tests will run in parallel and execute in different browsers like chrome and firefox.

4)Report path : testReport/Test-Automaton-Report.html

## page factory

Implemented page factory approach for locators and the path is src/main/pages but never used @FindBy annotation as i want to write seperate class for selenium actions for better logging.

## sorted using streams
sorted product names in catalog based on their product names and its name length by using Java streams and compartor methods
