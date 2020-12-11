package gui;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JTextPane;


public class Pan6 extends JPanel implements Pan {
	
	private JTextArea idTextArea;
	private JTextArea nameTextArea;
	private JTextArea myAllergyTextArea;
	private JList<String> foodlist;
	private String my_new_allergy = "";
	private loginData user;
	/**
	 * Create the panel.
	 */
	public Pan6(Main win, loginData user) {
		this.user = user;
		user.registerObject(this);
		
		setBounds(0, 0, 890, 600);
		setLayout(null);
		
		
		JLabel titleLabel = new JLabel("\u98DF\u898B");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("�ü�", Font.PLAIN, 28));
		titleLabel.setBounds(12, 10, 78, 66);
		add(titleLabel);
		
		JLabel subTitleLabel = new JLabel("������");
		subTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subTitleLabel.setBounds(89, 53, 65, 23);
		add(subTitleLabel);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setFont(new Font("���� ���", Font.PLAIN, 14));
		idLabel.setBounds(24, 105, 24, 15);
		add(idLabel);
		
		
		idTextArea = new JTextArea();
		idTextArea.setBounds(34, 130, 95, 21);
		add(idTextArea);
		
		
		JLabel nameLabel = new JLabel("\uC774\uB984");
		nameLabel.setFont(new Font("���� ���", Font.PLAIN, 14));
		nameLabel.setBounds(163, 105, 38, 15);
		add(nameLabel);
		
		nameTextArea = new JTextArea();
		nameTextArea.setBounds(191, 125, 107, 21);
		add(nameTextArea);
		
		
		
		JLabel myAllergyLabel = new JLabel("\uBCF4\uC720\uC911\uC778 \uC54C\uB808\uB974\uAE30");
		myAllergyLabel.setFont(new Font("���� ���", Font.PLAIN, 14));
		myAllergyLabel.setBounds(22, 177, 132, 15);
		add(myAllergyLabel);
		
		
		myAllergyTextArea = new JTextArea();
		myAllergyTextArea.setEditable(false);
		myAllergyTextArea.setBounds(34, 202, 351, 75);
		add(myAllergyTextArea);
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("���� ���", Font.PLAIN, 14));
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
		comboBox.setBounds(34, 316, 176, 26);
		add(comboBox);
		
		
		JLabel addAllergyLabel = new JLabel("\uC54C\uB808\uB974\uAE30 \uCD94\uAC00");
		addAllergyLabel.setFont(new Font("���� ���", Font.PLAIN, 14));
		addAllergyLabel.setBounds(22, 291, 107, 15);
		add(addAllergyLabel);
		
		//�˷����� �߰�, ����
		JButton addAllergyButton = new JButton("\uCD94\uAC00");
		addAllergyButton.setFont(new Font("���� ���", Font.PLAIN, 14));
		addAllergyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.dao.callMyAllergy(idTextArea.getText()).contains((String) comboBox.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "�̹� ��ϵ� �˷������Դϴ�", "���", JOptionPane.WARNING_MESSAGE);
				} else {
					if(((String) comboBox.getSelectedItem()).equals("[���� �Է�]")) {
						JOptionPane.showMessageDialog(null, "�˷����� �̸��� �Է����ּ���", "���", JOptionPane.WARNING_MESSAGE);
					} else {
						//�˷����� ���� ����
						try {
							if(!Main.dao.searchAllergy((String) comboBox.getSelectedItem())) {
								Main.dao.insertAllergy((String) comboBox.getSelectedItem());
							}
							if(myAllergyTextArea.getText().equals("")) {
								my_new_allergy += (String) comboBox.getSelectedItem();
								myAllergyTextArea.setText(my_new_allergy);
							} else {
								my_new_allergy = myAllergyTextArea.getText() + ", " + (String) comboBox.getSelectedItem();
								myAllergyTextArea.setText(my_new_allergy);
							}
							user.setAlist(my_new_allergy);
//							JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�");
						} catch (Exception e3) {
							e3.printStackTrace();
						}
					}
				}
			}
		});
		addAllergyButton.setBounds(222, 316, 75, 26);
		add(addAllergyButton);
		
		JButton delAllergyButton = new JButton("\uC0AD\uC81C");
		delAllergyButton.setFont(new Font("���� ���", Font.PLAIN, 14));
		delAllergyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!myAllergyTextArea.getText().contains((String) comboBox.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "��ϵ��� ���� �˷������Դϴ�", "���", JOptionPane.WARNING_MESSAGE);
				} else {
					if(myAllergyTextArea.getText().contains(", " + (String) comboBox.getSelectedItem())) {
						myAllergyTextArea.setText(myAllergyTextArea.getText().replaceAll((", " + (String) comboBox.getSelectedItem()), ""));
						my_new_allergy = myAllergyTextArea.getText().replaceAll((", " + (String) comboBox.getSelectedItem()), "");
					} else if(myAllergyTextArea.getText().contains((String) comboBox.getSelectedItem() + ", ")) {
						myAllergyTextArea.setText(myAllergyTextArea.getText().replaceAll(((String) comboBox.getSelectedItem()+ ", "), ""));
						my_new_allergy = myAllergyTextArea.getText().replaceAll(((String) comboBox.getSelectedItem()), "");
					} else {
						my_new_allergy = myAllergyTextArea.getText().replaceAll(((String) comboBox.getSelectedItem()), "");
					}
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�");
					//������ �ִ��� �ƴ� ���
					if(!Main.dao.checkAllergy((String) comboBox.getSelectedItem())) {
						Main.dao.deleteAllergy((String) comboBox.getSelectedItem());
					}
					Main.dao.deleteMyAllergy((String) comboBox.getSelectedItem(), idTextArea.getText());
					user.setAlist(my_new_allergy);
				}
			}
		});
		delAllergyButton.setBounds(310, 316, 75, 26);
		add(delAllergyButton);
		
		
		//�ڷΰ��� �˻�â���� ��
		JButton backButton = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backButton.setFont(new Font("HY�߰��", Font.PLAIN, 14));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my_new_allergy = "";
				if(!user.getid().equals(idTextArea.getText()) || !user.getname().equals(nameTextArea.getText()) || !user.getMyAllergy().equals(myAllergyTextArea.getText())) {
					int result = JOptionPane.showConfirmDialog(null, "���� ������ �Ͻðڽ��ϱ�?", "�˸�", JOptionPane.YES_NO_CANCEL_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						changeMyInfo();
						win.change("pan2");
					} else if(result == JOptionPane.CANCEL_OPTION) {
						
					} else {
						win.change("pan2");
					}
				} else {
					win.change("pan2");
				}
			}
		});
		backButton.setBounds(729, 25, 116, 46);
		add(backButton);
		
		
		//ȸ�� Ż�� �κ�
		JButton delMemberButton = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
		delMemberButton.setFont(new Font("HY�߰��", Font.PLAIN, 14));
		delMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "���� Ż���Ͻðڽ��ϱ�?", "�˸�", JOptionPane.YES_NO_CANCEL_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					Main.dao.deleteMember(user.getid());
					JOptionPane.showMessageDialog(null, "Ż�� �Ϸ�");
					String[][] tmp = user.getMyCheckFood();
					for (int i = 0; i < tmp.length; i++) {
						for (int j = 0; j < tmp[i].length; j++) {
							tmp[i][j] = "";
						}
					}
					user.set("", "", "", "", tmp, false);
					win.change("pan2");
				}
			}
		});
		delMemberButton.setBounds(224, 485, 112, 48);
		add(delMemberButton);
		
		
		
		//��������
		JButton updateButton = new JButton("\uC815\uBCF4\uC218\uC815");
		updateButton.setFont(new Font("HY�߰��", Font.PLAIN, 14));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeMyInfo();
			}
		});
		updateButton.setBounds(89, 485, 112, 48);
		add(updateButton);
		
		
		//�̹��� ��
		JLabel imageLabel = new JLabel("");
		imageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		imageLabel.setVerticalAlignment(SwingConstants.TOP);
		imageLabel.setBounds(413, 257, 427, 264);
		add(imageLabel);
		
		//�� ���� ����Ʈ
		foodlist = new JList<String>();
		foodlist.setFont(new Font("���� ���", Font.PLAIN, 15));
		foodlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					try {
						String[][] check = user.getMyCheckFood();
						ImageIcon icon = new ImageIcon(new URL(check[1][foodlist.getSelectedIndex()]));
						Image img = icon.getImage();
						Image changeImg = img.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
						ImageIcon changeIcon = new ImageIcon(changeImg);
						imageLabel.setIcon(changeIcon);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		foodlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		foodlist.setBounds(413, 106, 427, 116);
		add(foodlist);
		
		JLabel foodNameLabel = new JLabel("\uCCB4\uD06C\uD55C \uC2DD\uD488");
		foodNameLabel.setFont(new Font("���� ���", Font.PLAIN, 14));
		foodNameLabel.setVerticalAlignment(SwingConstants.TOP);
		foodNameLabel.setBounds(413, 77, 112, 23);
		add(foodNameLabel);
		
		JLabel imageLabel_0 = new JLabel("\uC2DD\uD488 \uC774\uBBF8\uC9C0");
		imageLabel_0.setFont(new Font("���� ���", Font.PLAIN, 14));
		imageLabel_0.setBounds(413, 232, 114, 15);
		add(imageLabel_0);

	}
	
	public void changeMyInfo() {
		//��ĭ�϶�
		if(idTextArea.getText().length() == 0 || nameTextArea.getText().length() == 0 || myAllergyTextArea.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "���� �Է����ּ���", "���", JOptionPane.WARNING_MESSAGE);
		} else {
			//���̵� �ٲ������
			if(!idTextArea.getText().equals(user.getid())) {
				if(Main.dao.searchMemberId(idTextArea.getText())) {
					JOptionPane.showMessageDialog(null, "�ߺ��Ǵ� ���̵� �̹� �����մϴ�", "���", JOptionPane.WARNING_MESSAGE);
				} else {
					Main.dao.updateMember(idTextArea.getText(), nameTextArea.getText(), user.getid());
					String[] stmp = myAllergyTextArea.getText().split(", ");
					for (int i = 0; i < stmp.length; i++) {
						//�� ������ ��ϵ��� ���� �˷����⸸ ��ϵȴ�
						if(!Main.dao.callMyAllergy(idTextArea.getText()).contains(stmp[i])) {
							Main.dao.insertMemberAllergy2(idTextArea.getText(), stmp[i]);
						}
					}
					user.setIdName(idTextArea.getText(), nameTextArea.getText());
					JOptionPane.showMessageDialog(null, "�������� �Ϸ�");
				}
			} else {
				Main.dao.updateMember(idTextArea.getText(), nameTextArea.getText(), user.getid());
				Main.dao.updateIdCheck(idTextArea.getText(), user.getid());
				String[] stmp = myAllergyTextArea.getText().split(", ");
				for (int i = 0; i < stmp.length; i++) {
					//�� ������ ��ϵ��� ���� �˷����⸸ ��ϵȴ�
					if(!Main.dao.callMyAllergy(idTextArea.getText()).contains(stmp[i])) {
						Main.dao.insertMemberAllergy2(idTextArea.getText(), stmp[i]);
					}
				}
				user.setIdName(idTextArea.getText(), nameTextArea.getText());
				JOptionPane.showMessageDialog(null, "�������� �Ϸ�");
			}
			
		}
	}

	@Override
	public void update(String id, String pw) {
		idTextArea.setText(user.getid());
		nameTextArea.setText(user.getname());
		myAllergyTextArea.setText(user.getMyAllergy());
		foodlist.setListData(user.getMyCheckFood()[0]);
	}

	@Override
	public void updateA() {
		// TODO Auto-generated method stub
		
	}
}
