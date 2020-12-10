package gui;
import java.util.ArrayList;

public class loginData implements PanelSubject {
	private ArrayList<Pan> observers;
	private String id;
	private String pw;
	
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
	
	public void set(String id, String pw) {
		this.id = id;
		this.pw = pw;
		userChanged();
	}
	
	public void setID(String id) {
		this.id = id;
		userChanged();
	}
	
	
	public String getid() {
		return id;
	}
	public String getpw() {
		return pw;
	}

}
