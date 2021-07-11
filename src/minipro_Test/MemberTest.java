package minipro_Test;

import java.util.ArrayList;
import java.util.Scanner;

import minipro_DAO.MemberDAO;
import minipro_DTO.MemberDTO;

public class MemberTest {

	public static void main(String[] args) {
		
		MemberDAO dao = new MemberDAO();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("| 1.ȸ������  | 2.ȸ������   | 3.ȸ������   | 4.ȸ������  | 5.��ü����  | 6.����  |");
			System.out.println("��ȣ���� >>");
			
			String choice = sc.nextLine();
			
			switch(choice) {
			case "1":	//����
				System.out.println("����� ���̵� �Է�.");
				String id=sc.nextLine();
				boolean checkResult=dao.isCheck(id);
				if(checkResult==true) {
					System.out.println("�̹� ��ϵ� ���̵��Դϴ�.");
				}else {
					System.out.println("�̸��� �Է��ϼ���.");
					String name = sc.nextLine();
					System.out.println("�̸����� �Է��ϼ���.");
					String email = sc.nextLine();
					System.out.println("�н����带 �Է��ϼ���.");
					String pwd = sc.nextLine();
					int insertResult = dao.insert(id, pwd, name, email);
					
					if(insertResult>0)
						System.out.println("���ԿϷ�");
					else
						System.out.println("���Խ���!");
				}
				break;
				
			case "2":	// ����
				System.out.println("���̵� �Է�.");
				id = sc.nextLine();
				checkResult=dao.isCheck(id);
				if(checkResult==true) {
					System.out.println("������ �н����� �Է�.");
					String pwd = sc.nextLine();
					System.out.println("������ �̸��� �Է�.");
					String email = sc.nextLine();
					dao.update(pwd, email, id);
					int updateResult = dao.update(pwd, email, id);
					
					if(updateResult>0)
						System.out.println("�����Ϸ�!");
					else
						System.out.println("��������..");
				}else
					System.out.println("���̵� �����ϴ�.");
				
				
				break;
				
			case "3":	//����
				System.out.println("������ ���̵� �Է�.");
				id = sc.nextLine();
				checkResult=dao.isCheck(id);
				if(checkResult==true) {
					System.out.println("�н����� �Է�");
					String pwd = sc.nextLine();
					dao.delete(id, pwd);
					int deleteResult = dao.delete(id, pwd);
					System.out.println(deleteResult);
					
					if(deleteResult>0)
						System.out.println("��������!");
					else
						System.out.println("��������..");
					
				}else
					System.out.println("���̵� �����ϴ�.");
				break;
				
			case "4":	//����
				System.out.println("ã�� ���̵� �Է�.");
				id = sc.nextLine();
				checkResult=dao.isCheck(id);
				if(checkResult==true) {
					MemberDTO selectResult = dao.select(id);
					System.out.println("���̵�\t �̸�\t �̸���\t\t    ��������\t");
					System.out.println("-------------------------------------------------------");
					System.out.println(selectResult.getId()+"   "+ selectResult.getName()+"   "+selectResult.getEmail()+"   "+selectResult.getSignupdate());
				}else
					System.out.println("���̵� �����ϴ�.");
				
				break;
				
			case "5":	// ��ü����
				ArrayList<MemberDTO> getAllResult = dao.getAll();
				getAllResult = dao.getAll();
				for( MemberDTO index:getAllResult)
					System.out.println(index);
				break;
				
			case "6":	// ����
				System.out.println("���α׷� ����.");
				System.exit(0);
			default:
				System.out.println("�Է¿���!!");
			}//	switch
			
			
			
			
		}//	while
		
		
	}

}
