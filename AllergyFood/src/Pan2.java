
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
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class Pan2 extends JPanel {
	private JTextField textField;
	private Test win;
	private JButton btnNewButton_1;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	public Pan2(Test win) {
		this.win = win;
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(34, 99, 248, 30);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("pan4");
			}
		});
		
		btnNewButton.setBounds(301, 32, 87, 30);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("검색");
		
		btnNewButton_1.setBounds(293, 99, 65, 30);
		add(btnNewButton_1);
		
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
				{null, "\uD14C\uC2A4\uD2B8", null},
				{null, "\uD14C\uC2A4\uD2B82", null},
				{null, "\uD14C\uC2A4\uD2B83", null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"No", "\uC81C\uD488\uBA85", "\uC81C\uC870\uC0AC"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMinWidth(9);
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("\uD14C\uC2BD");
		lblNewLabel.setFont(new Font("궁서", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 78, 66);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\uAC80\uC0C9");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(92, 53, 65, 23);
		add(lblNewLabel_1);
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText();
//				textPane.setText(str);
			}
		});
	}
}
