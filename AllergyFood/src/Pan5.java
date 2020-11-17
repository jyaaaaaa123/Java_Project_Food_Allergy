import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;

public class Pan5 extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public Pan5(Test win) {
		setBounds(0, 0, 400, 500);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(48, 141, 57, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(48, 178, 57, 15);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PW");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(48, 219, 57, 15);
		add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(117, 138, 163, 21);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 175, 163, 21);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(117, 216, 163, 21);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\uBCF4\uC720\uC911\uC778 \uC54C\uB808\uB974\uAE30");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(48, 258, 129, 15);
		add(lblNewLabel_3);
		
		
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
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.control);
		textArea.setBounds(58, 284, 244, 42);
		add(textArea);
		
		
		//추가 버튼
		JButton btnNewButton = new JButton("\uCD94\uAC00");
		btnNewButton.addActionListener(new ActionListener() {
			String my_al = "";
			public void actionPerformed(ActionEvent e) {
				if(my_al.equals("")) {
					my_al += (String) comboBox.getSelectedItem();
					textArea.setText(my_al);
				} else {
					my_al += ", " + (String) comboBox.getSelectedItem();
					textArea.setText(my_al);
				}
			}
		});
		btnNewButton.setBounds(249, 345, 57, 26);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnNewButton_1.setBounds(140, 424, 97, 23);
		add(btnNewButton_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uD14C\uC2BD");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("궁서", Font.PLAIN, 28));
		lblNewLabel_1_1.setBounds(12, 10, 78, 66);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setBounds(89, 53, 65, 23);
		add(lblNewLabel_1_1_1);
		
		JButton btnNewButton_2 = new JButton("\uC911\uBCF5\uAC80\uC0AC");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String check_id = textField.getText();
			}
		});
		btnNewButton_2.setBounds(292, 137, 97, 23);
		add(btnNewButton_2);
		
		
	}
}
