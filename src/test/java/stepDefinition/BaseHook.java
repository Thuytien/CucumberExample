package stepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import util.UtilRuntest;

public class BaseHook extends BaseDef {

	public static Boolean testWasInitiated = false;
	
	@Before
	public void beforeEachScenario(Scenario scenario) {

		if (UtilRuntest.testWasInitiated == false) {
			UtilRuntest.initiate();
		}
		
	}

	@After
	public void afterEachScenario(Scenario scenario) {
		UtilRuntest.finalize(browser);
	}
}
