import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import db.AllergyDTO;
import db.MemberDTO;

import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Pan6 extends JPanel {
	
	public static JTextArea idTextArea;
	public static JTextArea nameTextArea;
	public static JTextArea myAllergyTextArea;
	private String my_new_allergy = "";
	/**
	 * Create the panel.
	 */
	public Pan6(Test win, Pan4 pan4) {
		
		setBounds(0, 0, 400, 500);
		setLayout(null);
		
		
		JLabel titleLabel = new JLabel("\uC74C\uC2DD");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("�ü�", Font.PLAIN, 28));
		titleLabel.setBounds(12, 10, 78, 66);
		add(titleLabel);
		
		JLabel subTitleLabel = new JLabel("������");
		subTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subTitleLabel.setBounds(89, 53, 65, 23);
		add(subTitleLabel);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(24, 107, 112, 15);
		add(idLabel);
		
		
		idTextArea = new JTextArea();
		idTextArea.setBounds(41, 101, 95, 21);
		add(idTextArea);
		
		
		JLabel nameLabel = new JLabel("\uC774\uB984");
		nameLabel.setBounds(153, 107, 57, 15);
		add(nameLabel);
		
		nameTextArea = new JTextArea();
		nameTextArea.setBounds(190, 103, 107, 24);
		add(nameTextArea);
		
		
		
		JLabel myAllergyLabel = new JLabel("\uBCF4\uC720\uC911\uC778 \uC54C\uB808\uB974\uAE30");
		myAllergyLabel.setBounds(22, 177, 107, 15);
		add(myAllergyLabel);
		
		
		myAllergyTextArea = new JTextArea();
		myAllergyTextArea.setEditable(false);
		myAllergyTextArea.setBounds(32, 202, 325, 75);
		add(myAllergyTextArea);
		
		
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
		comboBox.setBounds(41, 316, 169, 26);
		add(comboBox);
		
		
		JLabel addAllergyLabel = new JLabel("\uC54C\uB808\uB974\uAE30 \uCD94\uAC00");
		addAllergyLabel.setBounds(22, 291, 107, 15);
		add(addAllergyLabel);
		
		//�˷����� �߰�, ����
		JButton addAllergyButton = new JButton("\uCD94\uAC00");
		addAllergyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Test.dao.callMyAllergy(idTextArea.getText()).contains((String) comboBox.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "�̹� ��ϵ� �˷������Դϴ�", "���", JOptionPane.WARNING_MESSAGE);
				} else {
					//�˷����� ���� ����
					if(!Test.dao.searchAllergy((String) comboBox.getSelectedItem())) {
						Test.dao.insertAllergy((String) comboBox.getSelectedItem());
					}
					JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�");
					if(myAllergyTextArea.getText().equals("")) {
						my_new_allergy += (String) comboBox.getSelectedItem();
						myAllergyTextArea.setText(my_new_allergy);
					} else {
						my_new_allergy = myAllergyTextArea.getText() + ", " + (String) comboBox.getSelectedItem();
						myAllergyTextArea.setText(my_new_allergy);
					}
				}
			}
		});
		addAllergyButton.setBounds(222, 316, 75, 26);
		add(addAllergyButton);
		
		JButton delAllergyButton = new JButton("\uC0AD\uC81C");
		delAllergyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(idTextArea.getText());
				if(!myAllergyTextArea.getText().contains((String) comboBox.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "��ϵ��� ���� �˷������Դϴ�", "���", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�");
					if(myAllergyTextArea.getText().contains(", " + (String) comboBox.getSelectedItem())) {
						myAllergyTextArea.setText(myAllergyTextArea.getText().replaceAll((", " + (String) comboBox.getSelectedItem()), ""));
						my_new_allergy = myAllergyTextArea.getText().replaceAll((", " + (String) comboBox.getSelectedItem()), "");
					} else if(myAllergyTextArea.getText().contains((String) comboBox.getSelectedItem())) {
						myAllergyTextArea.setText(myAllergyTextArea.getText().replaceAll(((String) comboBox.getSelectedItem()), ""));
						my_new_allergy = myAllergyTextArea.getText().replaceAll(((String) comboBox.getSelectedItem()), "");
						
					}
					//������ �ִ��� �ƴ� ���
					if(!Test.dao.checkAllergy((String) comboBox.getSelectedItem())) {
						Test.dao.deleteAllergy((String) comboBox.getSelectedItem());
					}
					System.out.println(myAllergyTextArea.getText());
					Test.dao.deleteMyAllergy((String) comboBox.getSelectedItem(), idTextArea.getText());
				}
			}
		});
		delAllergyButton.setBounds(310, 316, 65, 26);
		add(delAllergyButton);
		
		//�ڷΰ��� �˻�â���� ��
		JButton backButton = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("pan2");
			}
		});
		backButton.setBounds(41, 406, 97, 36);
		add(backButton);
		
		
		//ȸ�� Ż�� �κ�
		JButton delMemberButton = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
		delMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "���� Ż���Ͻðڽ��ϱ�?", "�˸�", JOptionPane.YES_NO_CANCEL_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					Test.dao.deleteMember(Pan4.getLoginId());
					JOptionPane.showMessageDialog(null, "Ż�� �Ϸ�");
					Pan2.setLoginBtnTrue();
					Pan2.setLogoutBtnFalse();
					Pan2.setMyInfoBtnFalse();
					win.change("pan2");
				}
			}
		});
		delMemberButton.setBounds(260, 406, 97, 36);
		add(delMemberButton);
		
		//��������
		JButton updateButton = new JButton("\uC815\uBCF4\uC218\uC815");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ĭ�϶�
				if(idTextArea.getText().length() == 0 || nameTextArea.getText().length() == 0 || myAllergyTextArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���� �Է����ּ���", "���", JOptionPane.WARNING_MESSAGE);
				} else {
					//���̵� �ٲ������
					if(!idTextArea.getText().equals(Pan4.getLoginId())) {
						if(Test.dao.searchMemberId(idTextArea.getText())) {
							JOptionPane.showMessageDialog(null, "�ߺ��Ǵ� ���̵� �̹� �����մϴ�", "���", JOptionPane.WARNING_MESSAGE);
						} else {
							Test.dao.updateMember(idTextArea.getText(), nameTextArea.getText(), Pan4.getLoginId());
							String[] stmp = myAllergyTextArea.getText().split(", ");
							for (int i = 0; i < stmp.length; i++) {
								//�� ������ ��ϵ��� ���� �˷����⸸ ��ϵȴ�
								System.out.println(idTextArea.getText());
								if(!Test.dao.callMyAllergy(idTextArea.getText()).contains(stmp[i])) {
									Test.dao.insertMemberAllergy2(idTextArea.getText(), stmp[i]);
								}
							}
							Pan4.setLoginId(idTextArea.getText());
							JOptionPane.showMessageDialog(null, "�������� �Ϸ�");
						}
					} else {
						Test.dao.updateMember(idTextArea.getText(), nameTextArea.getText(), Pan4.getLoginId());
						Test.dao.updateIdCheck(idTextArea.getText(), Pan4.getLoginId());
						String[] stmp = myAllergyTextArea.getText().split(", ");
						for (int i = 0; i < stmp.length; i++) {
							//�� ������ ��ϵ��� ���� �˷����⸸ ��ϵȴ�
							if(!Test.dao.callMyAllergy(idTextArea.getText()).contains(stmp[i])) {
								Test.dao.insertMemberAllergy2(idTextArea.getText(), stmp[i]);
							}
						}
						Pan4.setLoginId(idTextArea.getText());
						JOptionPane.showMessageDialog(null, "�������� �Ϸ�");
					}
					
				}
			}
		});
		updateButton.setBounds(151, 406, 97, 36);
		add(updateButton);
		
		
		
		
		
	}
}
