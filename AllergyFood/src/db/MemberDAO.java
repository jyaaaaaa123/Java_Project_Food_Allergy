package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	//DB연결 메소드
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
	
//	//한사람의 회원정보를 얻는 메소드(내정보)
//	public MemberDTO getMemberDTO(String member_id) {
//		MemberDTO dto = new MemberDTO();
//		
//		ps = null; //명령
//		rs = null; //결과
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
	
	//회원 등록(아이디 이름 비밀번호)
	public void insertMember(MemberDTO mdto) {
		ps = null;
		rs = null;
		try {
			
			String sql = "insert into member(member_id, member_name, member_password, member_birth)"
						+ "values(?,?,?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, mdto.getId());
			ps.setString(2, mdto.getName());
			ps.setString(3, mdto.getPw());
			ps.setInt(4, mdto.getMember_birth());
			
			int r = ps.executeUpdate(); //실행저장
			
			if(r>0) {
				System.out.println("가입성공");
			} else {
				System.out.println("가입실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//알레르기 존재여부 확인
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
	
	//기존에 존재하는 알레르기(기본 알레르기)
	public boolean checkAllergy(String allergy_name) {
		boolean ok = false;

		ps = null;
		rs = null;
		
		try {
			String sql = "select allergy_name from allergy where allergy_name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, allergy_name);
			rs = ps.executeQuery();
			
			String[] a_arr = {"새우", "굴", "게", "홍합", "오징어", "전복", "고등어", "조개류",
					"메밀", "밀", "대두", "호두", "땅콩", "잣", "알류(가금류)", "우유", "쇠고기", "돼지고기",
					"닭고기", "복숭아", "토마토", "아황산류"};
			
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
	
	//알레르기 등록
	public void insertAllergy(String name) {
		ps = null;

		try {
			
			String sql = "insert into allergy(allergy_name)"
						+ "values(?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			
			int r = ps.executeUpdate(); //실행저장
			
			if(r>0) {
				System.out.println("알레르기 등록");
			} else {
				System.out.println("알레르기 등록실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//알레르기 삭제
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
	
	//회원등록(알레르기)
	public void insertMemberAllergy(AllergyDTO adto, MemberDTO mdto) {
		ps = null;
		rs = null;
		try {
			
			String sql = "insert into memberallergy(member_id, allergy_name)"
						+ "values(?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, mdto.getId());
			ps.setString(2, adto.getAllergy_name());
			
			int r = ps.executeUpdate(); //실행저장
			
			if(r>0) {
				System.out.println("등록완료");
			} else {
				System.out.println("등록실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//아이디 중복 검사
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
	
	//로그인
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
	
	//이름 얻기
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
	
	//탈퇴
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
	
	
	//정보수정
	public void updateMember(String id, String name, String pre_id) {
		ps = null;
		
		try {
			String sql0 = "alter table membercheck disable constraint fk_MEMBERCHECK1";
			ps = con.prepareStatement(sql0);
			rs = ps.executeQuery();
			
			updateIdCheck(id, pre_id);
			
			String sql = "update member set member_id = ?, member_name = ? where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id); //수정할 아이디
			ps.setString(2, name); //수정할 이름
			ps.setString(3, pre_id); //현재 id 이걸로 검색
			rs = ps.executeQuery();
			
			
			String sql1 = "alter table membercheck enable constraint fk_MEMBERCHECK1";
			ps = con.prepareStatement(sql1);
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//내정보 알레르기 불러오기
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
	
	//내정보 알레르기 리턴이 배열인 형태로
	public ArrayList<String> callMyAllergy_arr(String id) {
		ps = null;
		rs = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			String sql = "select allergy_name from memberallergy where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("allergy_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//내정보 알레르기 삭제
	public void deleteMyAllergy(String allergy_name, String id) {
		ps = null;
		rs = null;
		
		try {
			String sql = "delete from memberallergy where ALLERGY_NAME = ? and member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, allergy_name);
			ps.setString(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//내정보 멤버알레르기 추가
	public void insertMemberAllergy2(String id, String allergy_name) {
		ps = null;
		rs = null;
		try {
			
			String sql = "insert into memberallergy(member_id, allergy_name)"
						+ "values(?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, allergy_name);
			
			int r = ps.executeUpdate(); //실행저장
			
			if(r>0) {
				System.out.println("등록완료");
			} else {
				System.out.println("등록실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//체크시 푸드 정보등록
	public void addMemberFood(String id, String food_name, String food_factory, String food_image) {
		ps = null;
		rs = null;
		try {
			
			String sql = "insert into membercheck(member_id, food_name, food_factory, food_image)"
						+ "values(?,?,?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, food_name);
			ps.setString(3, food_factory);
			ps.setString(4, food_image);
			
			int r = ps.executeUpdate(); //실행저장
			if(r>0) {
				System.out.println("체크완료");
			} else {
				System.out.println("체크실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//체크한 푸드인지 확인
	public boolean searchFoodCheck(String id, String food_name) {
		boolean ok = false;

		ps = null;
		rs = null;
		
		try {
			String sql = "select member_id from membercheck where member_id = ? and food_name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, food_name);
			
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
	
	//아이디 내에서 체크한 푸드 이름과 이미지 정보 리턴
	public String[][] searchFoodCheck(String id) {
			ArrayList<String> foodNameList = new ArrayList<String>();
			ArrayList<String> foodImageList = new ArrayList<String>();
			ps = null;
			rs = null;
			
			try {
				String sql = "select food_name, food_image from membercheck where member_id = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				
				
				rs = ps.executeQuery();
				while(rs.next()) {
					foodNameList.add(rs.getString("food_name"));
					foodImageList.add(rs.getString("food_image"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String[][] array = new String[2][foodNameList.size()];
			int size = 0;
			for(String temp : foodNameList) {
				array[0][size++] = temp;
			}
			size = 0;
			for(String temp : foodImageList) {
				array[1][size++] = temp;
			}
			
			return array;
	}
	
		
	//체크해제시 삭제
	public void deleteMyfood(String id, String food_name) {
		ps = null;
		rs = null;
		
		try {
			String sql = "delete from membercheck where member_id = ? and food_name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, food_name);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//내정보 수정시 id변경
	public void updateIdCheck(String id, String pre_id) {
		ps = null;
		rs = null;
		
		try {
			String sql = "update membercheck set member_id = ? where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pre_id);
			ps.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//내 id 찾기
	public ArrayList<String> searchMyId(String member_name, int member_birth) {
		ps = null;
		rs = null;
		
		ArrayList<String> list = new ArrayList<String>();
		try {
			String sql = "select member_id from member where member_name = ? and member_birth = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, member_name);
			ps.setInt(2, member_birth);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString("member_id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
