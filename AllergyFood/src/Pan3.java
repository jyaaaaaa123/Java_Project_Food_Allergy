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
		
		JLabel titleLabel = new JLabel("TEST");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("±Ã¼­", Font.PLAIN, 28));
		titleLabel.setBounds(12, 10, 78, 66);
		add(titleLabel);
		
		JLabel subTitleLabel = new JLabel("\uC0C1\uC138\uC815\uBCF4");
		subTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subTitleLabel.setBounds(89, 53, 65, 23);
		add(subTitleLabel);
		
		
		JButton backButton = new JButton("µÚ·Î°¡±â");
		backButton.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("pan2");
			}
		});
		backButton.setBounds(146, 438, 107, 54);
		add(backButton);
		
		//ÀÌ¹ÌÁö URL·Î ¹Þ¾Æ¼­ Label »ý¼º

		JLabel foodImageLabel = new JLabel(new ImageIcon(new URL("http://fresh.haccp.or.kr/prdimg/2014/20140379022137/20140379022137-1.jpg")));
		
		foodImageLabel.setBounds(65, 112, 284, 190);
		add(foodImageLabel);
		
		
		
		JLabel foodNameLabel = new JLabel("\uC5B4\uCA4C\uAD6C \uC800\uCA4C\uAD6C");
		foodNameLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		foodNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		foodNameLabel.setBounds(97, 66, 209, 31);
		add(foodNameLabel);
		
		JTextArea foodAllergyTextArea = new JTextArea();
		foodAllergyTextArea.setBounds(99, 336, 217, 23);
		add(foodAllergyTextArea);
		
		JTextArea foodIntextArea = new JTextArea();
		foodIntextArea.setBounds(97, 387, 219, 46);
		add(foodIntextArea);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uCCB4\uD06C");
		rdbtnNewRadioButton.setBounds(335, 28, 57, 23);
		add(rdbtnNewRadioButton);
		
		JLabel foodAllergyLabel = new JLabel("\uC54C\uB808\uB974\uAE30 \uC131\uBD84");
		foodAllergyLabel.setBounds(75, 312, 89, 15);
		add(foodAllergyLabel);
		
		JLabel foodInLabel = new JLabel("\uBCF4\uC720 \uC131\uBD84");
		foodInLabel.setBounds(77, 369, 77, 15);
		add(foodInLabel);
		
		
		
	}
}
