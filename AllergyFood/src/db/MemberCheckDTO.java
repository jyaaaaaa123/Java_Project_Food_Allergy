package db;

public class MemberCheckDTO {
	private String member_id;
	private String food_name;
	private String food_factory;
	private String food_image;
	
	public MemberCheckDTO() {
		
	}
	
	public MemberCheckDTO(String member_id, String food_name, String food_factory) {
		this.member_id = member_id;
		this.food_name = food_name;
		this.food_factory  = food_factory;
	}
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getFood_factory() {
		return food_factory;
	}
	public void setFood_factory(String food_factory) {
		this.food_factory = food_factory;
	}

	public String getFood_image() {
		return food_image;
	}

	public void setFood_image(String food_image) {
		this.food_image = food_image;
	}

	@Override
	public String toString() {
		return "MemberCheckDTO [member_id=" + member_id + ", food_name=" + food_name + ", food_factory=" + food_factory
				+ ", food_image=" + food_image + "]";
	}

	
}
