package db;

public class MemberDTO {
	private String member_id;
	private String member_name;
	private String member_password;
	private int member_birth;
	
	public MemberDTO() {
		
	}
	
	public MemberDTO(String member_id, String member_name, String member_password, int member_birth) {
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_password = member_password;
		this.member_birth = member_birth;
	}
	
	public String getId() {
		return member_id;
	}
	
	public void setId(String member_id) {
		this.member_id = member_id;
	}
	
	public String getName() {
		return member_name;
	}
	
	public void setName(String member_name) {
		this.member_name = member_name;
	}
	
	public String getPw() {
		return member_password;
	}
	
	public void setPw(String member_password) {
		this.member_password = member_password;
	}

	public int getMember_birth() {
		return member_birth;
	}

	public void setMember_birth(int member_birth) {
		this.member_birth = member_birth;
	}

	@Override
	public String toString() {
		return "MemberDTO [member_id=" + member_id + ", member_name=" + member_name + ", member_password="
				+ member_password + ", member_birth=" + member_birth + "]";
	}
	
}
