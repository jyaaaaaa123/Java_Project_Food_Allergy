package gui;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.MemberDTO;
import db.MemberException;
import db.AllergyDTO;
import db.AuthenException;

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
	private JPasswordField pwTextField;
	private JTextField brithdayTextField;
	private String my_al = "";
	static String check_id;
	
	int check_check = 0;
	
	/**
	 * Create the panel.
	 */
	public Pan5(Main win) {
		setBounds(0, 0, 890, 600);
		setLayout(null);
		
		
		JLabel titleLabel = new JLabel("\uC74C\uC2DD");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("�ü�", Font.PLAIN, 28));
		titleLabel.setBounds(12, 10, 78, 66);
		add(titleLabel);
		
		JLabel subTitleLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		subTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subTitleLabel.setBounds(89, 53, 65, 23);
		add(subTitleLabel);
		
		
		//id
		JLabel idLabel = new JLabel("ID");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(48, 175, 57, 15);
		add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(117, 175, 163, 33);
		add(idTextField);
		idTextField.setColumns(10);
		
		//���̵� �ߺ� üũ
		JButton checkIdButton = new JButton("\uC911\uBCF5\uAC80\uC0AC");
		checkIdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check_check++;
				check_id = idTextField.getText();
				if(Main.dao.searchMemberId(check_id)) {
					JOptionPane.showMessageDialog(null, "�̹� ������� ���̵��Դϴ�", "���", JOptionPane.WARNING_MESSAGE);
					check_check = 0; //�ߺ�üũ �ٽ� �ϵ���
				} else {
					try {
						MemberException.idFormat(check_id);
						JOptionPane.showMessageDialog(null, "��밡���� ���̵��Դϴ�");
					} catch(AuthenException e1) {
						JOptionPane.showMessageDialog(null, "3~10�� �̳��� Ư�����ڸ� ������ ���̵� �����մϴ�", "���", JOptionPane.WARNING_MESSAGE);
						check_check = 0;
					}
					
				}
			}
		});
		checkIdButton.setBounds(303, 174, 87, 34);
		add(checkIdButton);
		
		
		//�̸�
		JLabel nameLabel = new JLabel("\uC774\uB984");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(48, 297, 57, 15);
		add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(117, 288, 163, 33);
		add(nameTextField);
		nameTextField.setColumns(10);

		
		//��й�ȣ
		JLabel pwLabel = new JLabel("PW");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel.setBounds(48, 244, 57, 15);
		add(pwLabel);
		
		pwTextField = new JPasswordField();
		pwTextField.setBounds(117, 235, 163, 33);
		add(pwTextField);
		pwTextField.setColumns(10);
		
		//�������
		JLabel birthdayLabel = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		birthdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		birthdayLabel.setBounds(48, 355, 57, 15);
		add(birthdayLabel);
		
		brithdayTextField = new JTextField();
		brithdayTextField.setColumns(10);
		brithdayTextField.setBounds(117, 346, 163, 33);
		add(brithdayTextField);
		
		
		JLabel allergyListLabel = new JLabel("\uBCF4\uC720\uC911\uC778 \uC54C\uB808\uB974\uAE30");
		allergyListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		allergyListLabel.setBounds(478, 207, 129, 15);
		add(allergyListLabel);
		
		
		//�˷����� ���ùڽ�
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
		comboBox.setBounds(491, 327, 169, 26);
		add(comboBox);
		
		//���� �˷����� ǥ��
		JTextArea allergyTextArea = new JTextArea();
		allergyTextArea.setEditable(false);
		allergyTextArea.setLineWrap(true);
		allergyTextArea.setBackground(SystemColor.control);
		
		JScrollPane scrollPane = new JScrollPane(allergyTextArea);
		scrollPane.setBounds(489, 251, 277, 42);
		scrollPane.setBackground(SystemColor.control);
		add(scrollPane);
		
		
		//�߰�, ���� ��ư
		JButton allergyAddButton = new JButton("\uCD94\uAC00");
		allergyAddButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//�˷����� ���� ����
				if(!Main.dao.searchAllergy((String) comboBox.getSelectedItem())) {
					Main.dao.insertAllergy((String) comboBox.getSelectedItem());
				} 
				JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�");
				if(my_al.equals("")) {
					my_al += (String) comboBox.getSelectedItem();
					allergyTextArea.setText(my_al);
				} else {
					my_al += ", " + (String) comboBox.getSelectedItem();
					allergyTextArea.setText(my_al);
				}
				
			}
		});
		allergyAddButton.setBounds(667, 327, 57, 26);
		add(allergyAddButton);
		
		JButton allergydelButton = new JButton("\uC0AD\uC81C");
		allergydelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Main.dao.searchAllergy((String) comboBox.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "��ϵ��� ���� �˷������Դϴ�", "���", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�");
					if(allergyTextArea.getText().contains(", " + (String) comboBox.getSelectedItem())) {
						allergyTextArea.setText(allergyTextArea.getText().replaceAll((", " + (String) comboBox.getSelectedItem()), ""));
						my_al = allergyTextArea.getText().replaceAll((", " + (String) comboBox.getSelectedItem()), "");
					} else if(allergyTextArea.getText().contains((String) comboBox.getSelectedItem())) {
						allergyTextArea.setText(allergyTextArea.getText().replaceAll(((String) comboBox.getSelectedItem()), ""));
						my_al = allergyTextArea.getText().replaceAll(((String) comboBox.getSelectedItem()), "");
					}
					//������ �ִ��� �ƴ� ���
					if(!Main.dao.checkAllergy((String) comboBox.getSelectedItem())) {
						Main.dao.deleteAllergy((String) comboBox.getSelectedItem());
					}
				}
			}
		});
		allergydelButton.setBounds(729, 328, 57, 25);
		add(allergydelButton);
		
		
		//�ڷΰ��� ��ư
		JButton backButton = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idTextField.setText("");
				nameTextField.setText("");
				pwTextField.setText("");
				my_al = "";
				allergyTextArea.setText("");
				if(Pan1.whichPanelClickBackBtn[0] == true) {
					win.change("pan1");
					Pan1.whichPanelClickBackBtn[0] = false;
				} 
				if(Pan1.whichPanelClickBackBtn[1] == true) {
					win.change("pan2");
					Pan1.whichPanelClickBackBtn[1] = false;
				}
			}
		});
		backButton.setBounds(759, 25, 97, 49);
		add(backButton);
		
		
		//ȸ������ ��ư
		JButton memberAddButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		memberAddButton.setBounds(704, 447, 119, 66);
		add(memberAddButton);
		
		
				
		memberAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ĭ x
				if(idTextField.getText().length() == 0 || nameTextField.getText().length() == 0 || pwTextField.getText().length() == 0 || allergyTextArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���� �Է����ּ���", "���", JOptionPane.WARNING_MESSAGE);
				} else {
					//�ߺ��˻�
					if(check_check == 0 || !idTextField.getText().equals(check_id)) {
						JOptionPane.showMessageDialog(null, "���̵� �ߺ� �˻縦 ���ּ���", "���", JOptionPane.WARNING_MESSAGE);
					} else {
						try {
							MemberException.pwCheck(pwTextField.getText());
							MemberException.birthCheck(brithdayTextField.getText());
							MemberDTO mdto = new MemberDTO(idTextField.getText(), nameTextField.getText(), pwTextField.getText(), Integer.valueOf(brithdayTextField.getText()));
							String[] stmp = allergyTextArea.getText().split(", ");
							Main.dao.insertMember(mdto);
							for (int i = 0; i < stmp.length; i++) {
								AllergyDTO adto = new AllergyDTO(stmp[i]);
								Main.dao.insertMemberAllergy(adto, mdto);
							}		
							JOptionPane.showMessageDialog(null, "���� ����");
							idTextField.setText("");
							nameTextField.setText("");
							pwTextField.setText("");
							allergyTextArea.setText("");
							my_al = "";
							check_check = 0;
							win.change("pan1");
						} catch (AuthenException e2) {
							JOptionPane.showMessageDialog(null, e2.toString(), "���", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		});	
		
	}
}
