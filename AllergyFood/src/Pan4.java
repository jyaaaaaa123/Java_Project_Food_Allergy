import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pan4 extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Pan4(Test win) {
		setBounds(0, 0, 400, 500);
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(67, 186, 192, 32);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(67, 228, 192, 32);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.setBounds(280, 186, 97, 74);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 194, 57, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PW");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 236, 57, 15);
		add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("\uAC00\uC785\uD558\uAE30");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("pan5");
			}
		});
		btnNewButton_1.setBounds(236, 303, 97, 23);
		add(btnNewButton_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uD14C\uC2BD");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("±Ã¼­", Font.PLAIN, 28));
		lblNewLabel_1_1.setBounds(12, 10, 78, 66);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\uB85C\uADF8\uC778");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setBounds(89, 53, 65, 23);
		add(lblNewLabel_1_1_1);

	}
}
