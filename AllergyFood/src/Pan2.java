import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Box;
import java.awt.Dimension;

class Pan2 extends JPanel {
	private JTextField searchTextField;
	private Test win;
	private JButton searchButton;
	private JTable table;
	private JLabel titleLabel;
	private JLabel subTitleLabel;
	private static JButton myInfoButton;
	private static JButton logoutButton;
	private static JButton loginButton;

	public Pan2(Test win) {
		this.win = win;
		setLayout(null);
		
		
		titleLabel = new JLabel("\uD14C\uC2BD");
		titleLabel.setFont(new Font("궁서", Font.PLAIN, 28));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(12, 10, 78, 66);
		add(titleLabel);
		
		subTitleLabel = new JLabel("\uAC80\uC0C9");
		subTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subTitleLabel.setBounds(92, 53, 65, 23);
		add(subTitleLabel);
		
		//로그인 버튼
		loginButton = new JButton("로그인");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("pan4");
			}
		});
		loginButton.setBounds(285, 34, 87, 30);
		add(loginButton);
		
		
		//검색
		searchTextField = new JTextField();
		searchTextField.setBounds(34, 99, 248, 30);
		add(searchTextField);
		searchTextField.setColumns(10);
		
		
		searchButton = new JButton("검색");
		searchButton.setBounds(293, 99, 65, 30);
		add(searchButton);
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = searchTextField.getText();
//				textPane.setText(str);
			}
		});
		
		
		
		//검색결과 테이블
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 169, 324, 274);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					System.out.print(table.getValueAt(table.getSelectedRow(), 1) + "\n");
					win.change("pan3");
				} 
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "\uD14C\uC2A4\uD2B8", null, null},
				{null, "\uD14C\uC2A4\uD2B82", null, null},
				{null, "\uD14C\uC2A4\uD2B83", null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"No", "\uC81C\uD488\uBA85", "\uC81C\uC870\uC0AC", "\u3141"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMinWidth(8);
		table.getColumnModel().getColumn(1).setMinWidth(18);
		table.getColumnModel().getColumn(3).setPreferredWidth(23);
		scrollPane.setViewportView(table);		
		
		
		//내정보와 로그아웃 버튼
		myInfoButton = new JButton("\uB0B4\uC815\uBCF4");
		myInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("pan6");
			}
		});
		myInfoButton.setBounds(210, 33, 78, 32);
		add(myInfoButton);
		myInfoButton.setVisible(false);
		
		
		logoutButton = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//로그아웃 이벤트
				JOptionPane.showMessageDialog(null, "로그아웃");
				setLoginBtnTrue();
				setLogoutBtnFalse();
				setMyInfoBtnFalse();
			}
		});
		logoutButton.setBounds(293, 34, 95, 30);
		add(logoutButton);
		logoutButton.setVisible(false);
	
	}
	
	public static void setLoginBtnTrue() {
		loginButton.setVisible(true);
	}
	
	public static void setLoginBtnFalse() {
		loginButton.setVisible(false);
	}
	
	public static void setMyInfoBtnTrue() {
		myInfoButton.setVisible(true);
	}
	
	public static void setMyInfoBtnFalse() {
		myInfoButton.setVisible(false);
	}
	
	public static void setLogoutBtnTrue() {
		logoutButton.setVisible(true);
	}
	
	public static void setLogoutBtnFalse() {
		logoutButton.setVisible(false);
	}
	
	
}
