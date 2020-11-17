import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class Pan6 extends JPanel {

	/**
	 * Create the panel.
	 */
	public Pan6(Test win) {
		setBounds(0, 0, 400, 500);
		setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uD14C\uC2BD");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("±Ã¼­", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(12, 20, 78, 66);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uB0B4 \uC815\uBCF4");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(89, 63, 65, 23);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(24, 107, 112, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("\uC774\uB984");
		lblNewLabel_2.setBounds(153, 107, 57, 15);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uBCF4\uC720\uC911\uC778 \uC54C\uB808\uB974\uAE30");
		lblNewLabel_3.setBounds(22, 177, 107, 15);
		add(lblNewLabel_3);
		
		
		
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
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(22, 195, 335, 78);
		add(textPane);
		
		JLabel lblNewLabel_4 = new JLabel("\uC54C\uB808\uB974\uAE30 \uCD94\uAC00");
		lblNewLabel_4.setBounds(22, 291, 107, 15);
		add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("\uCD94\uAC00");
		btnNewButton.setBounds(240, 316, 78, 26);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnNewButton_1.setBounds(146, 406, 97, 36);
		add(btnNewButton_1);
		
	}
}
