import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;



public class Pan4 extends JPanel {
	private JTextField idTextField;
	private JPasswordField pwTextField;
	public static String loginid;
	static boolean[] b = {false, false};
	
	public int tmp = 0;
	/**
	 * Create the panel.
	 */
	public Pan4(Test win) {
		setBounds(0, 0, 1000, 618);
		setLayout(null);
		
		JLabel titleLabel = new JLabel("\uC74C\uC2DD");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("궁서", Font.PLAIN, 28));
		titleLabel.setBounds(12, 10, 78, 66);
		add(titleLabel);
		
		JLabel subTitleLabel = new JLabel("\uB85C\uADF8\uC778");
		subTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subTitleLabel.setBounds(89, 53, 65, 23);
		add(subTitleLabel);
		
		
		idTextField = new JTextField();
		idTextField.setBounds(620, 257, 192, 32);
		add(idTextField);
		idTextField.setColumns(10);
		
		pwTextField = new JPasswordField();
		pwTextField.setBounds(620, 299, 192, 32);
		add(pwTextField);
		pwTextField.setColumns(10);
		
		JButton loginButton = new JButton("\uB85C\uADF8\uC778");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b = Test.dao.loginMember(idTextField.getText(), pwTextField.getText());
				if(b[0]) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
					setLoginId(idTextField.getText());
					Pan2.setLoginBtnFalse();
					Pan2.setMyInfoBtnTrue();
					Pan2.setLogoutBtnTrue();
					win.change("pan2");
					idTextField.setText("");
					pwTextField.setText("");
				} else {
					if(b[1]) {
						JOptionPane.showMessageDialog(null, "잘못된 비밀번호입니다", "경고", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "잘못된 아이디입니다", "경고", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		loginButton.setBounds(852, 237, 97, 111);
		add(loginButton);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(551, 265, 57, 15);
		add(idLabel);
		
		JLabel pwLabel = new JLabel("PW");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel.setBounds(551, 307, 57, 15);
		add(pwLabel);
		
		JButton joinButton = new JButton("\uAC00\uC785\uD558\uAE30");
		joinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("pan5");
			}
		});
		joinButton.setBounds(786, 386, 124, 32);
		add(joinButton);
		
		JButton searchIdButton = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		searchIdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search sc = new Search();
				sc.run();
			}
		});
		searchIdButton.setBounds(620, 386, 130, 32);
		add(searchIdButton);
		
		JLabel picLabel = new JLabel("");
		picLabel.setIcon(new ImageIcon(Pan4.class.getResource("/icon/aa.png")));
		picLabel.setBounds(158, 133, 290, 358);
		add(picLabel);
		
		JButton backButton = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idTextField.setText("");
				pwTextField.setText("");
				win.change("pan2");
			}
		});
		backButton.setBounds(852, 21, 115, 32);
		add(backButton);
		
	}
	
	public static void setLoginId(String id) {
		loginid = id;
	}
	
	public static String getLoginId() {
		return loginid;
	}
}
