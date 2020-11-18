package db;

public class MemberAllergyDTO {
	private String member_id;
	private String member_allergy;
	private String member_mark;
	
	public MemberAllergyDTO() {
		
	}
	
	public MemberAllergyDTO(String member_id, String member_allergy, String member_mark) {
		this.member_id = member_id;
		this.member_allergy = member_allergy;
		this.member_mark = member_mark;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_allergy() {
		return member_allergy;
	}

	public void setMember_allergy(String member_allergy) {
		this.member_allergy = member_allergy;
	}

	public String getMember_mark() {
		return member_mark;
	}

	public void setMember_mark(String member_mark) {
		this.member_mark = member_mark;
	}

	@Override
	public String toString() {
		return "MemberAllergyDTO [member_id=" + member_id + ", member_allergy=" + member_allergy + ", member_mark="
				+ member_mark + "]";
	}
	
	
}
