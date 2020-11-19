import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.MemberDTO;
import db.AllergyDTO;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JScrollPane;

public class Pan5 extends JPanel {
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField pwTextField;
	private String my_al = "";
	
	int check_check = 0;
	
	/**
	 * Create the panel.
	 */
	public Pan5(Test win) {
		setBounds(0, 0, 400, 500);
		setLayout(null);
		
		
		JLabel titleLabel = new JLabel("TEST");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("궁서", Font.PLAIN, 28));
		titleLabel.setBounds(12, 10, 78, 66);
		add(titleLabel);
		
		JLabel subTitleLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		subTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subTitleLabel.setBounds(89, 53, 65, 23);
		add(subTitleLabel);
		
		
		//id
		JLabel idLabel = new JLabel("ID");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(48, 141, 57, 15);
		add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(117, 138, 163, 21);
		add(idTextField);
		idTextField.setColumns(10);
		
		JButton checkIdButton = new JButton("\uC911\uBCF5\uAC80\uC0AC");
		checkIdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check_check++;
				String check_id = idTextField.getText();
				if(Test.dao.searchMemberId(check_id)) {
					JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다", "경고", JOptionPane.WARNING_MESSAGE);
					check_check = 0; //중복체크 다시 하도록
				} else {
					JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다");
				}
			}
		});
		checkIdButton.setBounds(301, 137, 87, 23);
		add(checkIdButton);
		
		
		//이름
		JLabel nameLabel = new JLabel("\uC774\uB984");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(48, 178, 57, 15);
		add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(117, 175, 163, 21);
		add(nameTextField);
		nameTextField.setColumns(10);

		
		//비밀번호
		JLabel pwLabel = new JLabel("PW");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel.setBounds(48, 219, 57, 15);
		add(pwLabel);
		
		pwTextField = new JTextField();
		pwTextField.setBounds(117, 216, 163, 21);
		add(pwTextField);
		pwTextField.setColumns(10);
		
		
		
		
		JLabel allergyListLabel = new JLabel("\uBCF4\uC720\uC911\uC778 \uC54C\uB808\uB974\uAE30");
		allergyListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		allergyListLabel.setBounds(48, 258, 129, 15);
		add(allergyListLabel);
		
		
		//알레르기 선택박스
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() == 0) {
					comboBox.setEditable(true);
				} else {
					comboBox.setEditable(false);
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"[\uC9C1\uC811 \uC785\uB825]", "\uC0C8\uC6B0", "\uAD74", "\uAC8C", "\uD64D\uD569", "\uC624\uC9D5\uC5B4", "\uC804\uBCF5", "\uACE0\uB4F1\uC5B4", "\uC870\uAC1C\uB958", "\uBA54\uBC00", "\uBC00", "\uB300\uB450", "\uD638\uB450", "\uB545\uCF69", "\uC7A3", "\uC54C\uB958(\uAC00\uAE08\uB958)", "\uC6B0\uC720", "\uC1E0\uACE0\uAE30", "\uB3FC\uC9C0\uACE0\uAE30", "\uB2ED\uACE0\uAE30", "\uBCF5\uC22D\uC544", "\uD1A0\uB9C8\uD1A0", "\uC544\uD669\uC0B0\uB958(\uC640\uC778 \uB4F1)"}));
		comboBox.setBounds(68, 345, 169, 26);
		add(comboBox);
		
		//보유 알레르기 표시
		JTextArea allergyTextArea = new JTextArea();
		allergyTextArea.setEditable(false);
		allergyTextArea.setLineWrap(true);
		allergyTextArea.setBackground(SystemColor.control);
		
		JScrollPane scrollPane = new JScrollPane(allergyTextArea);
		scrollPane.setBounds(68, 283, 277, 42);
		scrollPane.setBackground(SystemColor.control);
		add(scrollPane);
		
		
		//추가, 삭제 버튼
		JButton allergyAddButton = new JButton("\uCD94\uAC00");
		allergyAddButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//알레르기 존재 여부
				if(!Test.dao.searchAllergy((String) comboBox.getSelectedItem())) {
					Test.dao.insertAllergy((String) comboBox.getSelectedItem());
				} 
				JOptionPane.showMessageDialog(null, "등록되었습니다");
				if(my_al.equals("")) {
					my_al += (String) comboBox.getSelectedItem();
					allergyTextArea.setText(my_al);
				} else {
					my_al += ", " + (String) comboBox.getSelectedItem();
					allergyTextArea.setText(my_al);
				}
				
			}
		});
		allergyAddButton.setBounds(249, 345, 57, 26);
		add(allergyAddButton);
		
		JButton allergydelButton = new JButton("\uC0AD\uC81C");
		allergydelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Test.dao.searchAllergy((String) comboBox.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "등록되지 않은 알레르기입니다", "경고", JOptionPane.WARNING_MESSAGE);
				} else {
					if(allergyTextArea.getText().contains(", " + (String) comboBox.getSelectedItem())) {
						allergyTextArea.setText(allergyTextArea.getText().replaceAll((", " + (String) comboBox.getSelectedItem()), ""));
						my_al = allergyTextArea.getText().replaceAll((", " + (String) comboBox.getSelectedItem()), "");
					} else if(allergyTextArea.getText().contains((String) comboBox.getSelectedItem())) {
						allergyTextArea.setText(allergyTextArea.getText().replaceAll(((String) comboBox.getSelectedItem()), ""));
						my_al = allergyTextArea.getText().replaceAll(((String) comboBox.getSelectedItem()), "");
					}
					//기존에 있던게 아닌 경우
					if(!Test.dao.checkAllergy((String) comboBox.getSelectedItem())) {
						Test.dao.deleteAllergy((String) comboBox.getSelectedItem());
					}
				}
			}
		});
		allergydelButton.setBounds(318, 345, 57, 25);
		add(allergydelButton);
		
		
		
		
		
		
		//뒤로가기 버튼
		JButton backButton = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("pan4");
				idTextField.setText("");
				nameTextField.setText("");
				pwTextField.setText("");
				my_al = "";
				allergyTextArea.setText("");
			}
		});
		backButton.setBounds(226, 424, 97, 23);
		add(backButton);
		
		
		//회원가입 버튼
		JButton memberAddButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		memberAddButton.setBounds(117, 424, 97, 23);
		add(memberAddButton);
				
		memberAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//빈칸 x
				if(idTextField.getText().length() == 0 || nameTextField.getText().length() == 0 || pwTextField.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "값을 입력해주세요", "경고", JOptionPane.WARNING_MESSAGE);
				} else {
					//중복검사
					if(check_check == 0) {
						JOptionPane.showMessageDialog(null, "아이디 중복 검사를 해주세요", "경고", JOptionPane.WARNING_MESSAGE);
					} else {
						check_check = 0;
						MemberDTO mdto = new MemberDTO(idTextField.getText(), nameTextField.getText(), pwTextField.getText());
						System.out.println(allergyTextArea.getText());
						String[] stmp = allergyTextArea.getText().split(", ");
						Test.dao.insertMember(mdto);
						for (int i = 0; i < stmp.length; i++) {
							AllergyDTO adto = new AllergyDTO(stmp[i]);
							Test.dao.insertMemberAllergy(adto, mdto);
						}		
						JOptionPane.showMessageDialog(null, "가입 성공");
						win.change("pan4");
					}
				}
			}
		});	
		
	}
	
}
