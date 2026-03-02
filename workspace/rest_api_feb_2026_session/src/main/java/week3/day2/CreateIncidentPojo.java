package week3.day2;

import com.google.gson.annotations.SerializedName;

public class CreateIncidentPojo {

	// Access Modifier Datatype VariableName;
	@SerializedName("short_description")
	private String shortDescription;
	private String description;
	private String category;
	
	public String getShortDescription() {
		return shortDescription;
	}

	public void setShort_description(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}