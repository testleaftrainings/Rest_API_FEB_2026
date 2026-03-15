package com.testleaf.matschie.servicenow.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/com/testleaf/matschie/servicenow/features/incident-service.feature"},
		          glue = {"com.testleaf.matschie.servicenow.step.definitions"},
		          dryRun = false,
		          plugin = {
		        		  "pretty",
		        		  "html:cucumber-report/result.html",
		        		  "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
		          }
		        )
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
