package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import db.*;



public class Main extends JFrame {
	/**
	 * 
	 */


	public static MemberDAO dao;
	
	
	
	public Pan1 pan1 = null;
	public Pan2 pan2 = null;
	public Pan3 pan3 = null;
	public Pan5 pan5 = null;
	public Pan6 pan6 = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					dao = new MemberDAO();
					frame.setTitle("음식알레르기");
					frame.pan1 = new Pan1(frame);
					frame.pan2 = new Pan2(frame);
					frame.pan3 = new Pan3(frame);
					frame.pan5 = new Pan5(frame);
					frame.pan6 = new Pan6(frame);
					frame.getContentPane().add(frame.pan1);
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 890, 600);	
	}
	
	public void change(String panelName) {
		if(panelName.equals("pan1")) {
			getContentPane().removeAll();
			getContentPane().add(pan1);
			revalidate();
			repaint();
		} else if(panelName.equals("pan2")) {
			getContentPane().removeAll();
			getContentPane().add(pan2);
			revalidate();
			repaint();
		} else if(panelName.equals("pan3")) {
			getContentPane().removeAll();
			getContentPane().add(pan3);
			revalidate();
			repaint();
		}  else if(panelName.equals("pan5")) {
			getContentPane().removeAll();
			getContentPane().add(pan5);
			revalidate();
			repaint();
		} else {
			getContentPane().removeAll();
			getContentPane().add(pan6);
			revalidate();
			repaint();
		}
	}
}
