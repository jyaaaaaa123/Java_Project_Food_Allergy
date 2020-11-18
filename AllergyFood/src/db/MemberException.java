package db;

import java.util.regex.Pattern;

public class MemberException {
	
	//���̵� Ȯ��
	public void idFormat(String str) throws AuthenException {
		if(str.length() < 5 || str.length() > 20) {
			throw new AuthenException("5~20�� �̳��� ���̵� �����մϴ�");
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
			throw new AuthenException("���̵�� �����ڿ� ���ڸ� ȥ���ؼ� ������ּ���");
	}
	
	//��й�ȣ Ȯ��
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
			throw new AuthenException("��й�ȣ�� �����ڿ� ���ڸ� ȥ���ؼ� ������ּ���");
		if(!pw1.equals(pw2))
			throw new AuthenException("��й�ȣ�� �ٸ��ϴ�");
	}
	
	//�̸� Ȯ��
	public void nameCheck(String name) throws AuthenException {
		boolean check = Pattern.matches("^[��-����-Ş]*$", name);
		if (!check)
			throw new AuthenException("���̸��� �ѱ۷� �Է����ּ���");
	}
}
