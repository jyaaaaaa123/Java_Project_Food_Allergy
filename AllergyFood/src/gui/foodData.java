package gui;

import java.util.ArrayList;

public class foodData implements Subject {
	private ArrayList<Pan> observers;
	private String food_image;
	
	
	public foodData() {
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
			p.updateA();
		}
	}
	

	public void userChanged() {
		notifyObject();
	}

	public void set(String food_image) {
		this.food_image = food_image;
		userChanged();
	}
	
	public String getimage() {
		return food_image;
	}
}
