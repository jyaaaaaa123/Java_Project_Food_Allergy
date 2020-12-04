package gui;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
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
import java.awt.Image;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import api.ConnApi;
import api.ParsApi;
import db.MemberDTO;

import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

class Pan2 extends JPanel {
	private JTextField searchTextField;
	private Main win;
	private JButton searchButton;
	private JTable table;
	private JLabel titleLabel;
	private JLabel subTitleLabel;
	private static JButton myInfoButton;
	private static JButton logoutButton;
	private static JButton loginButton;
	private static JButton newButton;
	public String pageNum = "1";
	private ImageIcon icon;
	public ParsApi papi;
	private JTextPane pageTextPane;
	private boolean searchBool = false;
	public DefaultTableModel model;
	public static String image_ad;
	public static String[][] food_name_image_arr;
	public static JTextPane whoLoginTextPane;
	public static String[] myInfo;

	public Pan2(Main win) {
		this.win = win;
		setBounds(0, 0, 890, 600);
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
				win.change("pan1");
			}
		});
		loginButton.setBounds(763, 20, 87, 30);
		add(loginButton);
		
		
		//검색
		searchTextField = new JTextField();
		searchTextField.setBounds(69, 99, 579, 38);
		add(searchTextField);
		searchTextField.setColumns(10);
		
		
		
		
		//검색결과 테이블
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 147, 840, 365);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					String foodname = (String) table.getValueAt(table.getSelectedRow(), 1);
					if(foodname == null && searchBool == false) {
						JOptionPane.showMessageDialog(null, "먼저 검색을 해주세요");
					} else if (foodname == null && searchBool == true){
						JOptionPane.showMessageDialog(null, "상품이 있는 칸을 선택해주세요");
					} else {
						String[] allery_list = papi.ParsApi_allergyList(foodname);
						Pan3.foodNameTextArea.setText(allery_list[0]);
						Pan3.foodManufTextArea.setText(allery_list[1]);
						Pan3.foodAllergyTextPane.setText(allery_list[2]);
						Pan3.foodIntextPane.setText(allery_list[4]);
						
						Runnable doScroll = new Runnable() {
							public void run() {
							    Pan3.scrollPane.getVerticalScrollBar().setValue(0);
							   }
							};
						SwingUtilities.invokeLater(doScroll);

						
						//알레르기 정보 확인
						if(Pan1.b[0]) {
							//체크 박스에 체크
							if(Main.dao.searchFoodCheck(Pan1.getLoginId(), foodname)) {
								Pan3.checkbox.setState(true);
							}
							ArrayList<String> str_al = Main.dao.callMyAllergy_arr(Pan1.getLoginId());
							boolean[][] judge = new boolean[str_al.size()][2];
							String real_judge = "";
							for (int i = 0; i < str_al.size(); i++) {
								if(allery_list[2].contains(str_al.get(i))) {
									StyledDocument doc = Pan3.foodAllergyTextPane.getStyledDocument();
									SimpleAttributeSet styleSet = new SimpleAttributeSet();
									StyleConstants.setForeground(styleSet, Color.RED);
									doc.setCharacterAttributes(allery_list[2].indexOf(str_al.get(i)), str_al.get(i).length(), styleSet, true);
									judge[i][0] = true;
								} 
								if(allery_list[4].contains(str_al.get(i))) {	
									StyledDocument doc = Pan3.foodIntextPane.getStyledDocument();
									SimpleAttributeSet styleSet = new SimpleAttributeSet();
									StyleConstants.setForeground(styleSet, Color.RED);
									doc.setCharacterAttributes(allery_list[4].indexOf(str_al.get(i)), str_al.get(i).length(), styleSet, true);
									doc.getDefaultRootElement();
									judge[i][1] = true;
								}
								if(judge[i][0] == true && judge[i][1] == true) {
									real_judge += "위험";
								} else if(judge[i][0] == true || judge[i][1] == true) {
									real_judge += "주의";
								} else {
									real_judge += "안전";
								}
							}
							if(real_judge.contains("위험")) {
								Pan3.anoTextPane.setForeground(Color.RED);
								Pan3.anoTextPane.setText("위험 - 위험성 있음");
							} else {
								if(real_judge.contains("주의")) {
									Pan3.anoTextPane.setForeground(Color.ORANGE);
									Pan3.anoTextPane.setText("주의 - 확인 필요");
								} else {
									Pan3.anoTextPane.setForeground(Color.BLACK);
									Pan3.anoTextPane.setText("안전");
								}
							}
						} 
						try {
							image_ad = allery_list[3];
							icon = new ImageIcon(new URL(image_ad));
							Image img = icon.getImage();
							Image changeImg = img.getScaledInstance(500, 320, Image.SCALE_SMOOTH);
							ImageIcon changeIcon = new ImageIcon(changeImg);
							Pan3.foodImageLabel.setIcon(changeIcon);
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						win.change("pan3");
					} 
				}
			}
		});
		
		createTable();

		scrollPane.setViewportView(table);		
		
		
		
		searchButton = new JButton("검색");
		searchButton.setBounds(697, 94, 123, 47);
		add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBool = true;
				String str = searchTextField.getText();
				createTable();
				try {
					pageNum = "1";
					pageTextPane.setText(pageNum);
					String apiUrl = ConnApi.ConnApi_func(str, pageNum);
					papi = new ParsApi(apiUrl);
					ArrayList<String> name = papi.ParsApi_Namelist();
					ArrayList<String> manuf = papi.ParsApi_Manufacturelist();
					if(name.size() == 0) {
						JOptionPane.showMessageDialog(null, "검색결과가 없습니다.");
					} else {
						for (int i = 0; i < name.size(); i++) {
							if(Pan1.b[0]==true) {
								if(Main.dao.searchFoodCheck(Pan1.getLoginId(), name.get(i))) {
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
				Pan6.idTextArea.setText(Pan1.getLoginId());
				Pan6.nameTextArea.setText(Main.dao.MemberName(Pan1.getLoginId()));
				Pan6.myAllergyTextArea.setText(Main.dao.callMyAllergy(Pan1.getLoginId()));
				myInfo = getMyInfo(Pan6.idTextArea.getText(), Pan6.nameTextArea.getText(), Pan6.myAllergyTextArea.getText());
				food_name_image_arr = Main.dao.searchFoodCheck(Pan1.getLoginId());
				Pan6.foodlist.setListData(food_name_image_arr[0]);
				win.change("pan6");
			}
		});
		myInfoButton.setBounds(637, 19, 87, 32);
		add(myInfoButton);
		myInfoButton.setVisible(false);
		
		
		logoutButton = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//로그아웃 이벤트
				JOptionPane.showMessageDialog(null, "로그아웃");
				setLoginBtnTrue();
				setNewBtnTrue();
				setLogoutBtnFalse();
				setMyInfoBtnFalse();
				createTable();
				whoLoginTextPane.setText("guest 접속중 입니다");
				Pan1.b[0] = false;
			}
		});
		logoutButton.setBounds(763, 20, 95, 30);
		add(logoutButton);
		logoutButton.setVisible(false);
		
		boolean pagebool = true;
		//이전 페이지
		JButton prePageButton = new JButton("<");
		prePageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(searchBool == false) {
					JOptionPane.showMessageDialog(null, "먼저 검색을 해주세요", "경고", JOptionPane.WARNING_MESSAGE);
				} else {
					if(Integer.parseInt(pageNum) != 1) {
						if(pagebool == true) {
							pageNum = Integer.toString(Integer.parseInt(pageNum) - 1);
							pageTextPane.setText(pageNum);
							String str = searchTextField.getText();
							try {
								String apiUrl = ConnApi.ConnApi_func(str, pageNum);
								papi = new ParsApi(apiUrl);
								ArrayList<String> name = papi.ParsApi_Namelist();
								ArrayList<String> manuf = papi.ParsApi_Manufacturelist();
								for (int i = 0; i < name.size(); i++) {
									table.setValueAt(name.get(i), i, 1);
									table.setValueAt(manuf.get(i), i, 2);
									if(Main.dao.searchFoodCheck(Pan1.getLoginId(), name.get(i))) {
										table.setValueAt("★", i, 3);
									} else {
										table.setValueAt("", i, 3);
									}
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
			}
		});
		prePageButton.setBounds(340, 518, 72, 23);
		add(prePageButton);
		
		//페이지 넘기기
		JButton nextPageButton = new JButton(">");
		nextPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageNum = Integer.toString(Integer.parseInt(pageNum) + 1);
				String str = searchTextField.getText();
				//검색을 했는지 안했는지 확인하는 부분
				if(searchBool == false) {
					JOptionPane.showMessageDialog(null, "먼저 검색을 해주세요", "경고", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						String apiUrl = ConnApi.ConnApi_func(str, pageNum);
						papi = new ParsApi(apiUrl);
						ArrayList<String> name = papi.ParsApi_Namelist();
						ArrayList<String> manuf = papi.ParsApi_Manufacturelist();
						
						if(name.size() < 10 && name.size() != 0) {
							for (int i = 0; i < name.size(); i++) {
								table.setValueAt(name.get(i), i, 1);
								table.setValueAt(manuf.get(i), i, 2);
								if(Main.dao.searchFoodCheck(Pan1.getLoginId(), name.get(i))) {
									table.setValueAt("★", i, 3);
								} else {
									table.setValueAt("", i, 3);
								}
							}
							for (int i = name.size(); i < 10; i++) {
								table.setValueAt(" ", i, 1);
								table.setValueAt(" ", i, 2);
								if(Main.dao.searchFoodCheck(Pan1.getLoginId(), name.get(i))) {
									table.setValueAt("★", i, 3);
								} else {
									table.setValueAt("", i, 3);
								}
							}
						} else if(name.size() == 0) {
							JOptionPane.showMessageDialog(null, "다음페이지가 없습니다", "경고", JOptionPane.WARNING_MESSAGE);
							pageNum = Integer.toString(Integer.parseInt(pageNum) - 1);
						} else {
							for (int i = 0; i < name.size(); i++) {
								table.setValueAt(name.get(i), i, 1);
								table.setValueAt(manuf.get(i), i, 2);
								if(Main.dao.searchFoodCheck(Pan1.getLoginId(), name.get(i))) {
									table.setValueAt("★", i, 3);
								} else {
									table.setValueAt("", i, 3);
								}
							}
						}
						pageTextPane.setText(pageNum);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		nextPageButton.setBounds(464, 518, 72, 23);
		add(nextPageButton);
		
		pageTextPane = new JTextPane();
		pageTextPane.setBackground(SystemColor.control);
		StyledDocument doc = pageTextPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		pageTextPane.setEditable(false);
		pageTextPane.setBounds(410, 518, 55, 27);
		pageTextPane.setText("1");
		add(pageTextPane);
		
		//가입하기 버튼
		newButton = new JButton("\uAC00\uC785\uD558\uAE30");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pan1.whichPanelClickBackBtn[1] = true;
				win.change("pan5");
			}
		});
		newButton.setBounds(637, 20, 87, 30);
		add(newButton);
		
		whoLoginTextPane = new JTextPane();
		whoLoginTextPane.setBackground(SystemColor.control);
		whoLoginTextPane.setForeground(SystemColor.desktop);
		whoLoginTextPane.setEditable(false);
		whoLoginTextPane.setBounds(140, 20, 299, 23);
		whoLoginTextPane.setText("guest 접속중 입니다");
		add(whoLoginTextPane);
		
		
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

	public static void setNewBtnTrue() {
		newButton.setVisible(true);
	}
	
	public static void setNewBtnFalse() {
		newButton.setVisible(false);
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
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(1).setMinWidth(15);
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
	
	public static String[] getMyInfo(String id, String name, String all) {
		String[] infolist = new String[3];
		infolist[0] = id;
		infolist[1] = name;
		infolist[2] = all;
		return infolist;
	}
}
