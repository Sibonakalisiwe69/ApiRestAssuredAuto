# Allure Report Generation - Issues Fixed

## Problem
The Allure server report was not being generated and returning errors due to missing configuration.

## Root Causes Identified

1. **Missing Allure Maven Plugin**: The pom.xml file did not include the `allure-maven` plugin which is required to generate the Allure report.

2. **Missing TestNG Configuration**: No `testng.xml` file was present to configure how tests should be executed, which is required for proper test result collection.

3. **Incomplete Surefire Plugin Configuration**: The Maven Surefire plugin was not configured to point to the TestNG XML file.

4. **Missing Allure Properties**: No `allure.properties` file was configured to specify where Allure results should be stored.

## Solutions Applied

### 1. Updated pom.xml
- Added the `allure-maven` plugin (version 2.13.0)
- Configured the `maven-surefire-plugin` to use the TestNG XML file at `src/test/resources/testng.xml`

**Changes:**
```xml
<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.13.0</version>
</plugin>
```

And updated Surefire configuration:
```xml
<configuration>
    <suiteXmlFiles>
        <suiteXmlFile>src/test/resources/testng.xml</suiteXmlFile>
    </suiteXmlFiles>
</configuration>
```

### 2. Created TestNG Configuration File
Created `src/test/resources/testng.xml` to define test suite configuration:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<suite name="API Rest Assured Test Suite" parallel="false" thread-count="1">
    <test name="User API Tests">
        <classes>
            <class name="tests.UserTest"/>
        </classes>
    </test>
</suite>
```

### 3. Created Allure Properties File
Created `src/test/resources/allure.properties` to configure Allure result storage:
```ini
allure.results.directory=allure-results
```

## How to Generate and View the Report

### Generate Test Results
```bash
mvn clean test
```

### Generate Allure Report
```bash
mvn allure:report
```

### View the Report
```bash
mvn allure:serve
```

This will start a local server and open the Allure report in your browser.

## Expected Outcome
- Tests will be executed with proper Allure result collection
- Results will be stored in the `allure-results` directory
- The Allure Maven plugin will generate an HTML report in `target/site/allure-report`
- The Allure serve command will display the report in a browser with real-time updates

## Verification Steps
1. Run `mvn clean test` - Tests should execute and generate results in `allure-results` folder
2. Check `allure-results` folder for JSON result files
3. Run `mvn allure:report` to generate the HTML report
4. Open `target/site/allure-report/index.html` or use `mvn allure:serve` to view the report

