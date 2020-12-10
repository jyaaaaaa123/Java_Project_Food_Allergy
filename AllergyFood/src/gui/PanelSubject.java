package gui;
public interface PanelSubject {
	public void registerObject(Pan p);
	public void removeObject(Pan p);
	public void notifyObject();
}
