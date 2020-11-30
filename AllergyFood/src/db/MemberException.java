package db;

import java.util.regex.Pattern;

public class MemberException {
	
	//���̵� Ȯ��
	public static void idFormat(String str) throws AuthenException {
		if(str.length() < 3 || str.length() > 10) {
			throw new AuthenException("3~10�� �̳��� ���̵� �����մϴ�");
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
			throw new AuthenException("���̵�� Ư�����ڸ� ����� �� �����ϴ�");
	}
	
	//��й�ȣ Ȯ��
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
			throw new AuthenException("��й�ȣ�� �����ڿ� ���ڸ� ȥ���ؼ� ������ּ���");
	}
	
	//������� Ȯ��
	public static void birthCheck(String birth) throws AuthenException {
		
		if(birth.length() != 6)
			throw new AuthenException("��������� 6�ڸ��� �Է����ּ���");
	}
}
