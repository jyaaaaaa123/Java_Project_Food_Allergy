package db;

public class MemberAllergyDTO {
	private String member_id;
	private String allergy_name;
	
	public MemberAllergyDTO() {
		
	}
	
	public MemberAllergyDTO(String member_id, String allergy_name) {
		this.member_id = member_id;
		this.allergy_name = allergy_name;
	}


	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getAllergy_name() {
		return allergy_name;
	}

	public void setAllergy_name(String allergy_name) {
		this.allergy_name = allergy_name;
	}


	@Override
	public String toString() {
		return "MemberAllergyDTO [member_id=" + member_id + ", allergy_name=" + allergy_name + "]";
	}
	
	
}
