import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import db.MemberDTO;

import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Pan6 extends JPanel {

	/**
	 * Create the panel.
	 */
	public Pan6(Test win) {
		setBounds(0, 0, 400, 500);
		setLayout(null);
		
		//내정보
//		MemberDTO dto = Test.dao.getMemberDTO(Pan4.getLoginId());
		JLabel titleLabel = new JLabel("TEST");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("궁서", Font.PLAIN, 28));
		titleLabel.setBounds(12, 10, 78, 66);
		add(titleLabel);
		
		JLabel subTitleLabel = new JLabel("내정보");
		subTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subTitleLabel.setBounds(89, 53, 65, 23);
		add(subTitleLabel);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(24, 107, 112, 15);
		add(idLabel);
		
		JTextArea idTextArea = new JTextArea();
		idTextArea.setBounds(46, 103, 83, 24);
		add(idTextArea);
		
		JLabel nameLabel = new JLabel("\uC774\uB984");
		nameLabel.setBounds(153, 107, 57, 15);
		add(nameLabel);
		
		JTextArea nameTextArea = new JTextArea();
		nameTextArea.setBounds(196, 103, 83, 24);
		add(nameTextArea);
		
		
		JLabel myAllergyLabel = new JLabel("\uBCF4\uC720\uC911\uC778 \uC54C\uB808\uB974\uAE30");
		myAllergyLabel.setBounds(22, 177, 107, 15);
		add(myAllergyLabel);
		
		
		
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
		
		JTextPane myAllergyTextPane = new JTextPane();
		myAllergyTextPane.setBounds(22, 195, 335, 78);
		add(myAllergyTextPane);
		
		JLabel addAllergyLabel = new JLabel("\uC54C\uB808\uB974\uAE30 \uCD94\uAC00");
		addAllergyLabel.setBounds(22, 291, 107, 15);
		add(addAllergyLabel);
		
		JButton addAllergyButton = new JButton("\uCD94\uAC00");
		addAllergyButton.setBounds(240, 316, 78, 26);
		add(addAllergyButton);
		
		//뒤로가기 검색창으로 감
		JButton backButton = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("pan2");
			}
		});
		backButton.setBounds(210, 406, 97, 36);
		add(backButton);
		
		
		//회원 탈퇴 부분
		JButton delMemberButton = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
		delMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Test.dao.deleteMember(dto.getId());
			}
		});
		delMemberButton.setBounds(89, 406, 97, 36);
		add(delMemberButton);
	}
	
	
	
}
