import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Search extends JFrame {

	private JPanel contentPane;
	private JTextField nmTextField;
	private JTextField btTextField;
	private Search frame;

	/**
	 * Launch the application.
	 */
	
	public void run() {
		try {
			frame = new Search();
			frame.setTitle("아이디 찾기");
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Search() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 327, 229);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		nmTextField = new JTextField();
		nmTextField.setBounds(24, 32, 87, 23);
		panel.add(nmTextField);
		nmTextField.setColumns(10);
		
		btTextField = new JTextField();
		btTextField.setBounds(123, 32, 87, 22);
		panel.add(btTextField);
		btTextField.setColumns(10);
		
		JTextPane textArea = new JTextPane();
		textArea.setBounds(24, 88, 275, 42);
		panel.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(24, 88, 275, 86);
		panel.add(scrollPane);
		
		JButton scButton = new JButton("\uCC3E\uAE30");
		scButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				ArrayList<String> idList = new ArrayList<String>();
				String birth = btTextField.getText();
				//문자가 들어가는지 확인
				char tmp;
				boolean boolnum = true;
				for (int i = 0; i < birth.length(); i++) {
					tmp = birth.charAt(i);
					if(!Character.isDigit(tmp)) {
						boolnum = false;
					}
				}
				if(birth.length() != 6) {
					boolnum = false;
				}
				if(boolnum) {
					idList = Test.dao.searchMyId(nmTextField.getText(), Integer.valueOf(btTextField.getText()));
					String String_list = "";
					if(idList.size() == 0) {
						textArea.setText("아이디가 없습니다");
					}
					for (int i = 0; i < idList.size(); i++) {
						String_list += idList.get(i)+"\n";
					}
					textArea.setText(String_list);
				} else {
					textArea.setText("생년월일은 6자리 숫자를 입력해주세요");
				}
			}
		});
		scButton.setBounds(221, 31, 94, 23);
		panel.add(scButton);
		
		
		
		JButton okButton = new JButton("\uD655\uC778");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.setBounds(113, 184, 97, 23);
		panel.add(okButton);
		
		JLabel nmLabel = new JLabel("\uC774\uB984");
		nmLabel.setBounds(24, 7, 57, 15);
		panel.add(nmLabel);
		
		JLabel btLabel = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		btLabel.setBounds(123, 7, 57, 15);
		panel.add(btLabel);
		
		JLabel idLabel = new JLabel("\uC544\uC774\uB514 \uAC80\uC0C9 \uACB0\uACFC");
		idLabel.setBounds(24, 65, 138, 15);
		panel.add(idLabel);
		
		
		
	}
}
