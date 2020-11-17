import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pan1 extends JPanel {
	private Test win;
	/**
	 * Create the panel.
	 */
	public Pan1(Test win) {
		this.win = win;
		setBounds(0, 0, 400, 500);
		setLayout(null);
		
		JButton start_btn = new JButton("start button");
		start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		start_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				win.change("pan2");
			}
		});
		start_btn.setIcon(new ImageIcon(Test.class.getResource("/icon/aa.png")));
		start_btn.setBounds(50, 200, 288, 247);
		start_btn.setBorderPainted(false);
		start_btn.setContentAreaFilled(false);
		start_btn.setFocusPainted(false);
		add(start_btn);
		
		JLabel lblNewLabel_2 = new JLabel("\uD504\uB85C\uC81D\uD2B8");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("±√º≠", Font.PLAIN, 50));
		lblNewLabel_2.setBounds(50, 50, 300, 124);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("\uD14C\uC2A4\uD2B8");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("HY∞ﬂ∏Ì¡∂", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(110, 150, 166, 34);
		add(lblNewLabel_1);
	}

}
