package week5.day1;

import org.testng.annotations.Test;
import week3.day2.pojos.UpdateIncident;

public class ServinowIncidentTableE2eTest {	
	
	private static String sys_id;
	private UpdateIncident updateIncident = new UpdateIncident();		
	private IncidentServiceV1 incidentService = new IncidentServiceV1();
	
	@Test(priority = 1)
	public void create_new_record() {		
		incidentService.createIncident();
		incidentService.validateResponse(201, "Created", "json");		
		sys_id = incidentService.extractSysIdValue();	
	}
	
	@Test(priority = 2)
	public void get_a_record() {
		incidentService.getARecords(sys_id);
		incidentService.validateResponse(200, "OK", "json");
		incidentService.validateSysIdValue("result.sys_id", sys_id);
	}

	@Test(priority = 3)
	public void update_existing_record() {
		updateIncident.setShortDescription("RESTAPIFEB2026");
		updateIncident.setDescription("Update existing record using put method");
		updateIncident.setCategory("hardware");
		incidentService.updateIncident(sys_id, updateIncident);
		incidentService.validateResponse(200, "OK", "json");
		incidentService.validateResponseBodyValue("result.short_description", updateIncident.getShortDescription());
		incidentService.validateResponseBodyValue("result.description", updateIncident.getDescription());
		incidentService.validateResponseBodyValue("result.category", updateIncident.getCategory());
	}
	
	@Test(priority = 4)
	public void delete_existing_record() {
		incidentService.deleteExistingRecord(sys_id);
		incidentService.validateResponse(204, "No Content");
	}
	
	@Test(priority = 5)
	public void is_record_deleted_successfully() {
		incidentService.getARecords(sys_id);
		incidentService.validateResponse(404, "Not Found", "json");
	}
}