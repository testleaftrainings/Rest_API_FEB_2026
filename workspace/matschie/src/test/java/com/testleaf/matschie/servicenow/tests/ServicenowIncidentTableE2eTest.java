package com.testleaf.matschie.servicenow.tests;

import org.testng.annotations.Test;

import com.testleaf.matschie.servicenow.services.IncidentService;

public class ServicenowIncidentTableE2eTest {	
	
	@Test
	public void get_all_records() {
		new IncidentService()
		    .getRecords()
		    .validateResponse(200, "OK", "JSON");
	}

}