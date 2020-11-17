import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

public class Pan3 extends JPanel{
	
	private Test win;
	/**
	 * Create the panel.
	 */
	public Pan3(Test win) throws IOException {
		this.win = win;
		setLayout(null);
		
		JButton btnNewButton = new JButton("µÚ·Î°¡±â");
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("pan2");
			}
		});
		btnNewButton.setBounds(146, 438, 107, 54);
		add(btnNewButton);
		
		//ÀÌ¹ÌÁö URL·Î ¹Þ¾Æ¼­ Label »ý¼º

		JLabel lblNewLabel = new JLabel(new ImageIcon(new URL("http://fresh.haccp.or.kr/prdimg/2014/20140379022137/20140379022137-1.jpg")));
		
		lblNewLabel.setBounds(65, 112, 284, 190);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uD14C\uC2BD");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("±Ã¼­", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(0, 0, 78, 66);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC0C1\uC138\uC815\uBCF4");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(77, 43, 65, 23);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC5B4\uCA4C\uAD6C \uC800\uCA4C\uAD6C");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(97, 66, 209, 31);
		add(lblNewLabel_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(98, 354, 217, 23);
		add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(97, 387, 219, 46);
		add(textArea_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uCCB4\uD06C");
		rdbtnNewRadioButton.setBounds(335, 28, 57, 23);
		add(rdbtnNewRadioButton);
		
		
		
	}
}
