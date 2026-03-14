package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/refactor-servicenow-incident.feature"},
		          glue = {"steps.def"},
		          dryRun = false,
		          plugin = {
		        		    "pretty",
		        		    "html:cucumber-reports/result.html",
		        		    "rerun:failed-scenario.txt"
		          },
		         // tags = "@regression and @smoke",
		          publish = true
		        )
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {

}