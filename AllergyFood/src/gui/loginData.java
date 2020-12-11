package gui;
import java.util.ArrayList;

public class loginData implements Subject {
	private ArrayList<Pan> observers;
	private String id;
	private String pw;
	private String name;
	private String myAllergy;
	private String[][] myCheckFood;
	private boolean loginCheck;
	
	public loginData() {
		observers = new ArrayList<Pan>();
	}
	
	@Override
	public void registerObject(Pan p) {
		observers.add(p);
		
	}

	@Override
	public void removeObject(Pan p) {
		int i = observers.indexOf(p);
		if(i >= 0) {
			observers.remove(i);
		}
	}
	
	@Override
	public void notifyObject() {
		for (int i=0; i < observers.size(); i++) {
			Pan p = observers.get(i);
			p.update(id, pw);
		}
	}

	public void userChanged() {
		notifyObject();
	}
	
	public void set(String id, String pw, String name, String myAllergy, String[][] myCheckFood, boolean loginCheck) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.myAllergy = myAllergy;
		this.myCheckFood = myCheckFood;
		this.loginCheck = loginCheck;
		userChanged();
	}
	
	public void setIdName(String id, String name) {
		this.id = id;
		this.name = name;
		userChanged();
	}
	
	public void setAlist(String myAllergy) {
		this.myAllergy = myAllergy;
		userChanged();
	}
	
	public void setMyfood(String[][] myCheckFood) {
		this.myCheckFood = myCheckFood;
		userChanged();
	}
	
	public String getid() {
		return id;
	}
	public String getpw() {
		return pw;
	}
	
	public boolean getcheck() {
		return loginCheck;
	}
	
	public String getname() {
		return name;
	}
	public String getMyAllergy() {
		return myAllergy;
	}
	public String[][] getMyCheckFood() {
		return myCheckFood;
	}
}
