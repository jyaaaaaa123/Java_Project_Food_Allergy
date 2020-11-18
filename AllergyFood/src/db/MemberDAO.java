package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberDAO {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "green";
	private static final String PASSWORD = "green1234";
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public MemberDAO() {
		con = getConnDB();
	}
	
	//DB���� �޼ҵ�
	public Connection getConnDB() {
		Connection con = null;
		
		try {
			Class.forName(DRIVER);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//�ѻ���� ȸ�������� ��� �޼ҵ�(������)
	public MemberDTO getMemberDTO(String member_id) {
		MemberDTO dto = new MemberDTO();
		
		ps = null; //���
		rs = null; //���
		
		try {
			String sql = "select * from member where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, member_id);
			rs = ps.executeQuery();
			
		if(rs.next()) {
			dto.setId(rs.getString("member_id"));
			dto.setName(rs.getString("member_name"));
			dto.setPw(rs.getString("member_password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//ȸ�� ���
	public void insertMember(MemberDTO dto) {
		ps = null;
		rs = null;
		try {
			
			String sql = "insert into member(member_id, member_name, member_password)"
						+ "values(?,?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getPw());
			
			int r = ps.executeUpdate(); //��������
			
			if(r>0) {
				System.out.println("���Լ���");
			} else {
				System.out.println("���Խ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//���̵� �ߺ� �˻�
	public boolean searchMemberId(String id) {
		boolean ok = false;

		ps = null;
		rs = null;
		
		try {
			String sql = "select member_id from member where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				cnt++;
			}
			if(cnt != 0) {
				ok = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ok;
	}
	
	//�α���
	public boolean[] loginMember(String id, String pw) {
		boolean[] ok = new boolean[2];
		
		ps = null;
		rs = null;
		
		try {
			String sql = "select member_id, member_password from member where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				cnt++;
				if(rs.getString("member_password").equals(pw)) {
					ok[0] = true;
				}
			}
			
			if(cnt > 0) {
				ok[1] = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ok;
	}
	
	//Ż��
	public void deleteMember(String id) {
		ps = null;
		rs = null;
		try {
			String sql = "delete from member where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
