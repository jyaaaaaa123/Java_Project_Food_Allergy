import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import api.ConnApi;
import api.ParsApi;

import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import javax.swing.JTextArea;

class Pan2 extends JPanel {
	private JTextField searchTextField;
	private Test win;
	private Pan6 pan6;
	private JButton searchButton;
	private JTable table;
	private JLabel titleLabel;
	private JLabel subTitleLabel;
	private static JButton myInfoButton;
	private static JButton logoutButton;
	private static JButton loginButton;
	public String pageNum = "1";
	private ImageIcon icon;
	public ParsApi papi;
	private JTextArea pageTextArea;

	public Pan2(Test win, Pan6 pan6) {
		this.win = win;
		this.pan6 = pan6;
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
				try {
					String apiUrl = ConnApi.ConnApi_func(str, pageNum);
					papi = new ParsApi(apiUrl);
					ArrayList<String> name = papi.ParsApi_Namelist();
					ArrayList<String> manuf = papi.ParsApi_Manufacturelist();
					for (int i = 0; i < name.size(); i++) {
						table.setValueAt(name.get(i), i, 1);
						table.setValueAt(manuf.get(i), i, 2);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		
		
		//검색결과 테이블
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 169, 365, 258);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					String foodname = (String) table.getValueAt(table.getSelectedRow(), 1);
					String[] allery_list = papi.ParsApi_allergyList(foodname);
					Pan3.foodNameTextArea.setText(allery_list[0]);
					Pan3.foodManufTextArea.setText(allery_list[1]);
					Pan3.foodAllergyTextArea.setText(allery_list[2]);
					try {
						icon = new ImageIcon(new URL(allery_list[3]));
						Pan3.foodImageLabel.setIcon(icon);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					win.change("pan3");
				} 
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, null, null},
				{"2", null, null, null},
				{"3", null, null, null},
				{"4", null, null, null},
				{"5", null, null, null},
				{"6", null, null, null},
				{"7", null, null, null},
				{"8", null, null, null},
				{"9", null, null, null},
				{"10", null, null, null},
			},
			new String[] {
				"No", "\uC81C\uD488\uBA85", "\uC81C\uC870\uC0AC", "\u3141"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
				return false;
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		
		table.getColumnModel().getColumn(0).setMinWidth(6);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		table.getColumnModel().getColumn(1).setMinWidth(18);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.setRowHeight(23);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < table.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		scrollPane.setViewportView(table);		
		
		
		//내정보와 로그아웃 버튼
		myInfoButton = new JButton("\uB0B4\uC815\uBCF4");
		myInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pan6.idTextArea.setText(Pan4.getLoginId());
				pan6.nameTextArea.setText(Test.dao.MemberName(Pan4.getLoginId()));
				pan6.myAllergyTextArea.setText(Test.dao.callMyAllergy(Pan4.getLoginId()));
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
		
		boolean pagebool = true;
		//이전 페이지
		JButton prePageButton = new JButton("<");
		prePageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(pageNum) != 1) {
					if(pagebool == true) {
						pageNum = Integer.toString(Integer.parseInt(pageNum) - 1);
						pageTextArea.setText(pageNum);
						String str = searchTextField.getText();
						try {
							String apiUrl = ConnApi.ConnApi_func(str, pageNum);
							papi = new ParsApi(apiUrl);
							ArrayList<String> name = papi.ParsApi_Namelist();
							if(name.size() != 10) {
								pagebool = false;
								break;
							}
							ArrayList<String> manuf = papi.ParsApi_Manufacturelist();
							for (int i = 0; i < name.size(); i++) {
								table.setValueAt(name.get(i), i, 1);
								table.setValueAt(manuf.get(i), i, 2);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					
				} else {
					JOptionPane.showMessageDialog(null, "전페이지가 없습니다", "경고", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		prePageButton.setBounds(113, 447, 41, 23);
		add(prePageButton);
		
		//페이지 넘기기
		JButton nextPageButton = new JButton(">");
		nextPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageNum = Integer.toString(Integer.parseInt(pageNum) + 1);
				pageTextArea.setText(pageNum);
				String str = searchTextField.getText();
				try {
					String apiUrl = ConnApi.ConnApi_func(str, pageNum);
					papi = new ParsApi(apiUrl);
					ArrayList<String> name = papi.ParsApi_Namelist();
					ArrayList<String> manuf = papi.ParsApi_Manufacturelist();
					for (int i = 0; i < name.size(); i++) {
						table.setValueAt(name.get(i), i, 1);
						table.setValueAt(manuf.get(i), i, 2);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		nextPageButton.setBounds(210, 447, 41, 23);
		add(nextPageButton);
		
		pageTextArea = new JTextArea();
		pageTextArea.setBounds(171, 447, 27, 15);
		pageTextArea.setText("1");
		add(pageTextArea);
	
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
