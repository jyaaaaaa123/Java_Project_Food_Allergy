package db;

import java.util.regex.Pattern;

public class MemberException {
	
	//아이디 확인
	public void idFormat(String str) throws AuthenException {
		if(str.length() < 5 || str.length() > 20) {
			throw new AuthenException("5~20자 이내의 아이디만 가능합니다");
		}
		
		int cnt1 = 0;
		int cnt2 = 0;
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if((ch>='a' && ch<='z')||(ch>='A' && ch<='Z'))
				cnt1++;
			else if(ch>='0' && ch<='9')
				cnt2++;
		}
		
		if(cnt1==0||cnt2==0)
			throw new AuthenException("아이디는 영문자와 숫자를 혼용해서 만들어주세요");
	}
	
	//비밀번호 확인
	public void pwCheck(String pw1, String pw2) throws AuthenException {
		int cnt1 = 0;
		int cnt2 = 0;
		
		for (int i = 0; i < pw1.length(); i++) {
			char ch = pw1.charAt(i);
			if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
				cnt1++;
			else if(ch>='0'&&ch<='9')
				cnt2++;
		}
		
		if(cnt1==0||cnt2==0)
			throw new AuthenException("비밀번호는 영문자와 숫자를 혼용해서 만들어주세요");
		if(!pw1.equals(pw2))
			throw new AuthenException("비밀번호가 다릅니다");
	}
	
	//이름 확인
	public void nameCheck(String name) throws AuthenException {
		boolean check = Pattern.matches("^[ㄱ-ㅎ가-힇]*$", name);
		if (!check)
			throw new AuthenException("※이름은 한글로 입력해주세요");
	}
}
