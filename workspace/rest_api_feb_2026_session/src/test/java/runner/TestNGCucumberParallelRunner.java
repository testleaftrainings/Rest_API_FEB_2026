package runner;

import org.testng.annotations.DataProvider;

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
public class TestNGCucumberParallelRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {		
		return super.scenarios();
	}

}