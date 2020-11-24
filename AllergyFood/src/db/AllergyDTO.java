package db;

public class AllergyDTO {
	private String allergy_name;
	
	public AllergyDTO() {
		
	}
	
	public AllergyDTO(String allergy_name) {
		this.allergy_name = allergy_name;
	}

	public String getAllergy_name() {
		return allergy_name;
	}

	public void setAllergy_name(String allergy_name) {
		this.allergy_name = allergy_name;
	}

	@Override
	public String toString() {
		return "allergyDTO [allergy_name=" + allergy_name + "]";
	}
	
	
}
