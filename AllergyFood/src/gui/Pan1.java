package gui;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.MemberDTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Pan1 extends JPanel  implements Pan {
	private Main win;
	private JTextField idTextField;
	private JPasswordField pwTextField;
//	public static String loginid;
	private loginData user;
	
	public int tmp = 0;
	
	static boolean[] b = {false, false};
	static boolean[] whichPanelClickBackBtn = {false, false};
	
	
	/**
	 * Create the panel.
	 */
	public Pan1(Main win, loginData user) {
		this.win = win;
		this.user = user;
		user.registerObject(this);
		setBounds(0, 0, 890, 600);
		setLayout(null);
		
		JButton start_btn = new JButton("\uB85C\uADF8\uC778 \uC5C6\uC774 \uC2DC\uC791\uD558\uAE30");
		start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		start_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				win.change("pan2");
			}
		});
		start_btn.setIcon(null);
		start_btn.setBounds(566, 433, 245, 34);
		add(start_btn);
		
		JLabel titleLabel = new JLabel("\uC74C\uC2DD\uD504\uB85C\uC81D\uD2B8");
		titleLabel.setBackground(SystemColor.control);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("궁서", Font.PLAIN, 50));
		titleLabel.setBounds(272, 22, 300, 124);
		add(titleLabel);
		
		JLabel subTitleLabel = new JLabel("\uD14C\uC2A4\uD2B8");
		subTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subTitleLabel.setFont(new Font("HY견명조", Font.PLAIN, 20));
		subTitleLabel.setBounds(331, 134, 166, 34);
		add(subTitleLabel);
		
		
		idTextField = new JTextField();
		idTextField.setBounds(487, 267, 192, 32);
		add(idTextField);
		idTextField.setColumns(10);
		
		pwTextField = new JPasswordField();
		pwTextField.setBounds(487, 322, 192, 32);
		add(pwTextField);
		pwTextField.setColumns(10);
		
		//로그인버튼
		JButton loginButton = new JButton("\uB85C\uADF8\uC778");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b = Main.dao.loginMember(idTextField.getText(), pwTextField.getText());
				if(b[0]) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
					user.set(idTextField.getText(), pwTextField.getText());
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
		loginButton.setBounds(714, 267, 97, 84);
		add(loginButton);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(414, 275, 57, 15);
		add(idLabel);
		
		JLabel pwLabel = new JLabel("PW");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel.setBounds(414, 322, 57, 15);
		add(pwLabel);
		
		//회원가입
		JButton joinButton = new JButton("\uAC00\uC785\uD558\uAE30");
		joinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whichPanelClickBackBtn[0] = true;
				win.change("pan5");
			}
		});
		joinButton.setBounds(698, 381, 113, 32);
		add(joinButton);
		
		//아이디찾기
		JButton searchIdButton = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		searchIdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search sc = new Search();
				sc.run();
			}
		});
		searchIdButton.setBounds(566, 381, 113, 32);
		add(searchIdButton);
		
		JLabel picLabel = new JLabel("");
		picLabel.setHorizontalAlignment(SwingConstants.CENTER);
		picLabel.setIcon(new ImageIcon(Pan1.class.getResource("/icon/pic1.png")));
		picLabel.setBounds(57, 197, 357, 337);
		add(picLabel);
		
	}
	

	@Override
	public void update(String id, String pw) {
		
	}
}

