package gui;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Checkbox;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.DropMode;

public class Pan3 extends JPanel{
	public static String allery_list;
	public static JTextPane foodAllergyTextPane;
	public static JTextArea foodNameTextArea;
	public static JTextArea foodManufTextArea;
	public static JTextPane foodIntextPane;
	public static JTextPane anoTextPane;
	public static JScrollPane scrollPane;
	public static JLabel foodImageLabel;
	public static Checkbox checkbox;
	private Main win;
	public boolean check = false;
	/**
	 * Create the panel.
	 */
	public Pan3(Main win) throws IOException {
		setBorder(null);
		setForeground(SystemColor.control);
		this.win = win;
		setBounds(0, 0, 890, 600);
		setLayout(null);
		
		JLabel titleLabel = new JLabel("\uC74C\uC2DD");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("궁서", Font.PLAIN, 28));
		titleLabel.setBounds(12, 10, 78, 66);
		add(titleLabel);
		
		JLabel subTitleLabel = new JLabel("\uC0C1\uC138\uC815\uBCF4");
		subTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subTitleLabel.setBounds(89, 53, 65, 23);
		add(subTitleLabel);
		
		
		
		
		//이미지 URL로 받아서 Label 생성
		
		foodImageLabel = new JLabel(new ImageIcon(new URL("http://fresh.haccp.or.kr/prdimg/2017/201704760012/201704760012-1.jpg")));
		foodImageLabel.setBounds(42, 179, 507, 320);
		add(foodImageLabel);
		
		
		
		JLabel foodAllergyLabel = new JLabel("\uC54C\uB808\uB974\uAE30 \uC131\uBD84");
		foodAllergyLabel.setBounds(571, 179, 89, 15);
		add(foodAllergyLabel);
		
		JLabel foodInLabel = new JLabel("\uC6D0\uC7AC\uB8CC");
		foodInLabel.setBounds(571, 286, 77, 15);
		add(foodInLabel);
		
		foodNameTextArea = new JTextArea();
		foodNameTextArea.setFont(new Font("Monospaced", Font.BOLD, 23));
		foodNameTextArea.setBackground(SystemColor.control);
		foodNameTextArea.setBounds(42, 114, 507, 36);
		add(foodNameTextArea);
		
		foodManufTextArea = new JTextArea();
		foodManufTextArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		foodManufTextArea.setBackground(SystemColor.control);
		foodManufTextArea.setBounds(41, 150, 497, 23);
		add(foodManufTextArea);
		
		checkbox = new Checkbox("체크");
		checkbox.setForeground(Color.BLACK);
		checkbox.setFont(new Font("Arial Black", Font.PLAIN, 12));
		checkbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(Pan1.b[0]) {
					if(e.getStateChange() == 1) {
						int result = JOptionPane.showConfirmDialog(null, "체크하시겠습니까?", "알림", JOptionPane.YES_NO_CANCEL_OPTION);
						if(result == JOptionPane.YES_OPTION) {
							Main.dao.addMemberFood(Pan1.getLoginId(), foodNameTextArea.getText(), foodManufTextArea.getText(), Pan2.image_ad);
							JOptionPane.showMessageDialog(null, "체크 완료");
						} else {
							checkbox.setState(false);
						}
					} else {//체크 해제
						int result = JOptionPane.showConfirmDialog(null, "해제하시겠습니까?", "알림", JOptionPane.YES_NO_CANCEL_OPTION);
						if(result == JOptionPane.YES_OPTION) {
							Main.dao.deleteMyfood(Pan1.getLoginId(), foodNameTextArea.getText());
							JOptionPane.showMessageDialog(null, "해제 완료");
						} else {
							checkbox.setState(true);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "로그인을 해주세요", "경고", JOptionPane.WARNING_MESSAGE);
					checkbox.setState(false);
				}	
			}
		});
		checkbox.setBounds(606, 25, 51, 46);
		add(checkbox);
		
		JButton backButton = new JButton("뒤로가기");
		backButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkbox.setState(false);
				foodAllergyTextPane.setText("");
				foodIntextPane.setText("");
				win.change("pan2");
			}
		});
		backButton.setBounds(729, 25, 116, 46);
		add(backButton);
		
		anoTextPane = new JTextPane();
		anoTextPane.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		anoTextPane.setBackground(SystemColor.control);
		anoTextPane.setBounds(571, 513, 236, 46);
		add(anoTextPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(571, 312, 274, 189);
		add(scrollPane);

		
		foodIntextPane = new JTextPane();
		foodIntextPane.setForeground(Color.BLACK);
		scrollPane.setViewportView(foodIntextPane);
		foodIntextPane.setBackground(SystemColor.control);
		foodIntextPane.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(571, 204, 274, 66);
		add(scrollPane_1);
		
		foodAllergyTextPane = new JTextPane();
		scrollPane_1.setViewportView(foodAllergyTextPane);
		foodAllergyTextPane.setBackground(SystemColor.control);
		foodAllergyTextPane.setEditable(false);
	}
}
