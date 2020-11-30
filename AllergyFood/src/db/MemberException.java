package db;

import java.util.regex.Pattern;

public class MemberException {
	
	//아이디 확인
	public static void idFormat(String str) throws AuthenException {
		if(str.length() < 3 || str.length() > 10) {
			throw new AuthenException("3~10자 이내의 아이디만 가능합니다");
		}
		
		int cnt = 0;
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if((ch>='a' && ch<='z')||(ch>='A' && ch<='Z'))
				continue;
			else if(ch>='0' && ch<='9')
				continue;
			else {
				cnt++;
			}
		}
		
		if(cnt != 0)
			throw new AuthenException("아이디는 특수문자를 사용할 수 없습니다");
	}
	
	//비밀번호 확인
//	public static void pwCheck(String pw1, String pw2) throws AuthenException {
	public static void pwCheck(String pw1) throws AuthenException {
		int cnt1 = 0;
		int cnt2 = 0;
		
		for (int i = 0; i < pw1.length(); i++) {
			char ch = pw1.charAt(i);
			if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
				cnt1++;
			if(ch>='0'&&ch<='9')
				cnt2++;
		}
		
		if(cnt1==0&&cnt2==0)
			throw new AuthenException("비밀번호는 영문자와 숫자를 혼용해서 만들어주세요");
	}
	
	//생년월일 확인
	public static void birthCheck(String birth) throws AuthenException {
		
		if(birth.length() != 6)
			throw new AuthenException("생년월일은 6자리를 입력해주세요");
	}
}
