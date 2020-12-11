package gui;
public interface Subject {
	public void registerObject(Pan p);
	public void removeObject(Pan p);
	public void notifyObject();
}
