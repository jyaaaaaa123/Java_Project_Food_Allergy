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
	
//	//�ѻ���� ȸ�������� ��� �޼ҵ�(������)
//	public MemberDTO getMemberDTO(String member_id) {
//		MemberDTO dto = new MemberDTO();
//		
//		ps = null; //���
//		rs = null; //���
//		
//		try {
//			String sql = "select * from member where member_id = ?";
//			ps = con.prepareStatement(sql);
//			ps.setString(1, member_id);
//			rs = ps.executeQuery();
//			
//		if(rs.next()) {
//			dto.setId(rs.getString("member_id"));
//			dto.setName(rs.getString("member_name"));
//			dto.setPw(rs.getString("member_password"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return dto;
//	}
	
	//ȸ�� ���(���̵� �̸� ��й�ȣ)
	public void insertMember(MemberDTO mdto) {
		ps = null;
		rs = null;
		try {
			
			String sql = "insert into member(member_id, member_name, member_password)"
						+ "values(?,?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, mdto.getId());
			ps.setString(2, mdto.getName());
			ps.setString(3, mdto.getPw());
			
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
	//�˷����� ���翩�� Ȯ��
	public boolean searchAllergy(String allergy_name) {
		boolean ok = false;

		ps = null;
		rs = null;
		
		try {
			String sql = "select allergy_name from allergy where allergy_name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, allergy_name);
			
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
	
	//������ �����ϴ� �˷�����(�⺻ �˷�����)
	public boolean checkAllergy(String allergy_name) {
		boolean ok = false;

		ps = null;
		rs = null;
		
		try {
			String sql = "select allergy_name from allergy where allergy_name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, allergy_name);
			rs = ps.executeQuery();
			
			String[] a_arr = {"����", "��", "��", "ȫ��", "��¡��", "����", "����", "������",
					"�޹�", "��", "���", "ȣ��", "����", "��", "�˷�(���ݷ�)", "����", "����", "�������",
					"�߰��", "������", "�丶��", "��Ȳ���"};
			
			int cnt = 0;
			while(rs.next()) {
				for (int i = 0; i < a_arr.length; i++) {
					if(rs.getString("allergy_name").equals(a_arr[i])) {
						cnt++;
					}
				}
			}
			if(cnt != 0) {
				ok = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ok;
	}
	
	//�˷����� ���
	public void insertAllergy(String name) {
		ps = null;

		try {
			
			String sql = "insert into allergy(allergy_name)"
						+ "values(?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			
			int r = ps.executeUpdate(); //��������
			
			if(r>0) {
				System.out.println("�˷����� ���");
			} else {
				System.out.println("�˷����� ��Ͻ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//�˷����� ����
	public void deleteAllergy(String allergy_name) {
		ps = null;
		try {
			String sql = "delete from allergy where allergy_name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, allergy_name);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//ȸ�����(�˷�����)
	public void insertMemberAllergy(AllergyDTO adto, MemberDTO mdto) {
		ps = null;
		rs = null;
		try {
			
			String sql = "insert into memberallergy(member_id, allergy_name)"
						+ "values(?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, mdto.getId());
			ps.setString(2, adto.getAllergy_name());
			
			int r = ps.executeUpdate(); //��������
			
			if(r>0) {
				System.out.println("��ϿϷ�");
			} else {
				System.out.println("��Ͻ���");
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
	
	//�̸� ���
	public String MemberName(String id) {

		ps = null;
		rs = null;
		String str = null;
		
		try {
			
			String sql = "select member_name from member where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				str = rs.getString("member_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return str;
	}
	
	//Ż��
	public void deleteMember(String id) {
		ps = null;
		try {
			String sql = "delete from member where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//��������
	public void updateMember(String id, String name, String pre_id) {
		ps = null;
		
		try {
			String sql = "update member set member_id = ?, member_name = ? where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id); //������ ���̵�
			ps.setString(2, name); //������ �̸�
			ps.setString(3, pre_id); //���� id �̰ɷ� �˻�
			rs = ps.executeQuery();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//������ �˷����� �ҷ�����
	public String callMyAllergy(String id) {
		ps = null;
		rs = null;
		String str = "";
		try {
			String sql = "select allergy_name from memberallergy where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				str = cnt > 0 ? (str + ", " + rs.getString("allergy_name")) : (str + rs.getString("allergy_name"));
				cnt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	//������ �˷����� ����
	public void deleteMyAllergy(String allergy_name) {
		ps = null;
		rs = null;
		
		try {
			String sql = "delete from memberallergy where ALLERGY_NAME = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, allergy_name);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//������ ����˷����� �߰�
	public void insertMemberAllergy2(String id, String allergy_name) {
		ps = null;
		rs = null;
		try {
			
			String sql = "insert into memberallergy(member_id, allergy_name)"
						+ "values(?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, allergy_name);
			
			int r = ps.executeUpdate(); //��������
			
			if(r>0) {
				System.out.println("��ϿϷ�");
			} else {
				System.out.println("��Ͻ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
