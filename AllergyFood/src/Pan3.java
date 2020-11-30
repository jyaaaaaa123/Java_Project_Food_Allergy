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
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Checkbox;

public class Pan3 extends JPanel{
	public static String allery_list;
	public static JTextPane foodAllergyTextPane;
	public static JTextArea foodNameTextArea;
	public static JTextArea foodManufTextArea;
	public static JTextPane foodIntextPane;
	public static JLabel foodImageLabel;
	public static Checkbox checkbox;
	private Test win;
	public boolean check = false;
	/**
	 * Create the panel.
	 */
	public Pan3(Test win) throws IOException {
		this.win = win;
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
		foodImageLabel.setBounds(54, 120, 306, 188);
		add(foodImageLabel);
		
		foodAllergyTextPane = new JTextPane();
		foodAllergyTextPane.setEditable(false);
		foodAllergyTextPane.setBounds(99, 336, 217, 23);
		add(foodAllergyTextPane);
		
		foodIntextPane = new JTextPane();
		foodIntextPane.setEditable(false);
		foodIntextPane.setBounds(97, 387, 246, 66);
		add(foodIntextPane);
		
		
		
		JLabel foodAllergyLabel = new JLabel("\uC54C\uB808\uB974\uAE30 \uC131\uBD84");
		foodAllergyLabel.setBounds(75, 312, 89, 15);
		add(foodAllergyLabel);
		
		JLabel foodInLabel = new JLabel("\uBCF4\uC720 \uC131\uBD84");
		foodInLabel.setBounds(77, 369, 77, 15);
		add(foodInLabel);
		
		foodNameTextArea = new JTextArea();
		foodNameTextArea.setBounds(22, 86, 176, 24);
		add(foodNameTextArea);
		
		foodManufTextArea = new JTextArea();
		foodManufTextArea.setBounds(210, 96, 150, 14);
		add(foodManufTextArea);
		
		checkbox = new Checkbox("\uCCB4\uD06C");
		checkbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(Pan4.b[0]) {
					if(e.getStateChange() == 1) {
						int result = JOptionPane.showConfirmDialog(null, "체크하시겠습니까?", "알림", JOptionPane.YES_NO_CANCEL_OPTION);
						if(result == JOptionPane.YES_OPTION) {
							Test.dao.addMemberFood(Pan4.getLoginId(), foodNameTextArea.getText(), foodManufTextArea.getText());
							JOptionPane.showMessageDialog(null, "체크 완료");
						} else {
							checkbox.setState(false);
						}
					} else {//체크 해제
						int result = JOptionPane.showConfirmDialog(null, "해제하시겠습니까?", "알림", JOptionPane.YES_NO_CANCEL_OPTION);
						if(result == JOptionPane.YES_OPTION) {
							Test.dao.deleteMyfood(Pan4.getLoginId(), foodNameTextArea.getText());
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
		checkbox.setBounds(321, 53, 55, 23);
		add(checkbox);
		
		JButton backButton = new JButton("뒤로가기");
		backButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkbox.setState(false);
				win.change("pan2");
			}
		});
		backButton.setBounds(12, 437, 78, 36);
		add(backButton);
	}
}
