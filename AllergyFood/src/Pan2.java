import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import api.ConnApi;
import api.ParsApi;

import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.SystemColor;

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
	public String pageNum = "1";
	private ImageIcon icon;
	public ParsApi papi;
	private JTextArea pageTextArea;
	public DefaultTableModel model;

	public Pan2(Test win) {
		this.win = win;
		setBounds(0, 0, 1000, 618);
		setLayout(null);
		
		
		titleLabel = new JLabel("\uC74C\uC2DD");
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
		loginButton.setBounds(870, 20, 87, 30);
		add(loginButton);
		
		
		//검색
		searchTextField = new JTextField();
		searchTextField.setBounds(34, 99, 746, 38);
		add(searchTextField);
		searchTextField.setColumns(10);
		
		
		
		
//		검색결과 테이블
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 166, 962, 365);
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
					Pan3.foodAllergyTextPane.setText(allery_list[2]);
					Pan3.foodIntextPane.setText(allery_list[4]);
					
					
					//알레르기 정보 확인
					if(Pan4.b[0]) {
						//체크 박스에 체크
						if(Test.dao.searchFoodCheck(Pan4.getLoginId(), foodname)) {
							Pan3.checkbox.setState(true);
						}
						ArrayList<String> str_al = Test.dao.callMyAllergy_arr(Pan4.getLoginId());
						for (int i = 0; i < str_al.size(); i++) {
							if(allery_list[2].contains(str_al.get(i))) {
								StyledDocument doc = Pan3.foodAllergyTextPane.getStyledDocument();
								SimpleAttributeSet styleSet = new SimpleAttributeSet();
								StyleConstants.setForeground(styleSet, Color.RED);
								doc.setCharacterAttributes(allery_list[2].indexOf(str_al.get(i)), str_al.get(i).length(), styleSet, true);
							} else {//기존 알레르기에 없는 알레르기일때 또는 알수없음일때
								if(allery_list[2].equals("알수없음")) {
									StyledDocument doc = Pan3.foodIntextPane.getStyledDocument();
									SimpleAttributeSet styleSet = new SimpleAttributeSet();
									StyleConstants.setForeground(styleSet, Color.RED);
									doc.setCharacterAttributes(allery_list[2].indexOf(str_al.get(i)), str_al.get(i).length(), styleSet, true);
									
								}
								if(allery_list[4].contains(str_al.get(i))) {
									StyledDocument doc = Pan3.foodIntextPane.getStyledDocument();
									SimpleAttributeSet styleSet = new SimpleAttributeSet();
									StyleConstants.setForeground(styleSet, Color.RED);
									doc.setCharacterAttributes(allery_list[4].indexOf(str_al.get(i)), str_al.get(i).length(), styleSet, true);
								}
							} 
						}
					} 
					
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
		
		createTable();

		scrollPane.setViewportView(table);		
		
		
		
		searchButton = new JButton("검색");
		searchButton.setBounds(832, 76, 125, 74);
		add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = searchTextField.getText();
				
				createTable();
				
				try {
					pageNum = "1";
					pageTextArea.setText("-" + pageNum + "-");
					String apiUrl = ConnApi.ConnApi_func(str, pageNum);
					papi = new ParsApi(apiUrl);
					ArrayList<String> name = papi.ParsApi_Namelist();
					ArrayList<String> manuf = papi.ParsApi_Manufacturelist();
					if(name.size() == 0) {
						JOptionPane.showMessageDialog(null, "검색결과가 없습니다.");
					} else {
						
						for (int i = 0; i < name.size(); i++) {
							if(Pan4.b[0]==true) {
								if(Test.dao.searchFoodCheck(Pan4.getLoginId(), name.get(i))) {
									table.setValueAt("★", i, 3);
								}
							}
							table.setValueAt(name.get(i), i, 1);
							table.setValueAt(manuf.get(i), i, 2);
						}
					}	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		
		
		//내정보와 로그아웃 버튼
		myInfoButton = new JButton("\uB0B4\uC815\uBCF4");
		myInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pan6.idTextArea.setText(Pan4.getLoginId());
				Pan6.nameTextArea.setText(Test.dao.MemberName(Pan4.getLoginId()));
				Pan6.myAllergyTextArea.setText(Test.dao.callMyAllergy(Pan4.getLoginId()));
				win.change("pan6");
			}
		});
		myInfoButton.setBounds(759, 19, 78, 32);
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
				createTable();
				Pan4.b[0] = false;
			}
		});
		logoutButton.setBounds(862, 20, 95, 30);
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
						pageTextArea.setText("-" + pageNum + "-");
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
				} else {
					JOptionPane.showMessageDialog(null, "전페이지가 없습니다", "경고", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		prePageButton.setBounds(339, 553, 87, 23);
		add(prePageButton);
		
		//페이지 넘기기
		JButton nextPageButton = new JButton(">");
		nextPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageNum = Integer.toString(Integer.parseInt(pageNum) + 1);
				String str = searchTextField.getText();
				try {
					String apiUrl = ConnApi.ConnApi_func(str, pageNum);
					papi = new ParsApi(apiUrl);
					ArrayList<String> name = papi.ParsApi_Namelist();
					ArrayList<String> manuf = papi.ParsApi_Manufacturelist();
					if(name.size() < 10 && name.size() != 0) {
						for (int i = 0; i < name.size(); i++) {
							table.setValueAt(name.get(i), i, 1);
							table.setValueAt(manuf.get(i), i, 2);
						}
						for (int i = name.size(); i < 10; i++) {
							table.setValueAt(" ", i, 1);
							table.setValueAt(" ", i, 2);
						}
					} else if(name.size() == 0) {
						JOptionPane.showMessageDialog(null, "다음페이지가 없습니다", "경고", JOptionPane.WARNING_MESSAGE);
						pageNum = Integer.toString(Integer.parseInt(pageNum) - 1);
					} else {
						for (int i = 0; i < name.size(); i++) {
							table.setValueAt(name.get(i), i, 1);
							table.setValueAt(manuf.get(i), i, 2);
						}
					}
					pageTextArea.setText("-" + pageNum + "-");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		nextPageButton.setBounds(484, 553, 87, 23);
		add(nextPageButton);
		
		pageTextArea = new JTextArea();
		pageTextArea.setBackground(SystemColor.control);
		pageTextArea.setEditable(false);
		pageTextArea.setBounds(445, 553, 27, 30);
		pageTextArea.setText("-1-");
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
	
	public void createTable() {
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
				"No", "\uC81C\uD488\uBA85", "\uC81C\uC870\uC0AC", "\uCCB4\uD06C"
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
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(3);
		table.getColumnModel().getColumn(0).setMinWidth(6);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(1).setMinWidth(18);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < table.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		table.setRowHeight(34);
	}
}
