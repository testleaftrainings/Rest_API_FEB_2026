package cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;

public class Hooks {

	@BeforeAll
	public static void beforeAllScenariosRunOnce() {
		System.out.println("Runnig @BeforeAll tag");
	}

	@AfterAll
	public static void afterRunningAllScenariosRunOnce() {
		System.out.println("Runnig @AfterAll tag");
	}
	
	@Before
	public void beforeRunningEachScenarios() {
		System.out.println("Runnig @Before tag");
	}
	
	@After
	public void afterRunningEachScenarios() {
		System.out.println("Runnig @After tag");
	}
	
	@BeforeStep
	public void beforeRunningEachStepsinScenarios() {
		System.out.println("Runnig @BeforeStep tag");
	}
	
	@AfterStep
	public void afterRunningEachStepsinScenarios() {
		System.out.println("Runnig @AfterStep tag");
	}

}