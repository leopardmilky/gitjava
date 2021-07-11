package minipro_Test;

import java.util.ArrayList;
import java.util.Scanner;

import minipro_DTO.MemberDTO;
import minipro_Service.MemberDAO;

public class MemberTest {

	public static void main(String[] args) {
		
		MemberDAO dao = new MemberDAO();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("| 1.회원가입  | 2.회원수정   | 3.회원삭제   | 4.회원보기  | 5.전체보기  | 6.종료  |");
			System.out.println("번호선택 >>");
			
			String choice = sc.nextLine();
			
			switch(choice) {
			case "1":	//가입
				System.out.println("등록할 아이디 입력.");
				String id=sc.nextLine();
				boolean checkResult=dao.isCheck(id);
				if(checkResult==true) {
					System.out.println("이미 등록된 아이디입니다.");
				}else {
					System.out.println("이름을 입력하세요.");
					String name = sc.nextLine();
					System.out.println("이메일을 입력하세요.");
					String email = sc.nextLine();
					System.out.println("패스워드를 입력하세요.");
					String pwd = sc.nextLine();
					int insertResult = dao.insert(id, pwd, name, email);
					
					if(insertResult>0)
						System.out.println("가입완료");
					else
						System.out.println("가입실패!");
				}
				break;
				
			case "2":	// 수정
				System.out.println("아이디 입력.");
				id = sc.nextLine();
				checkResult=dao.isCheck(id);
				if(checkResult==true) {
					System.out.println("수정할 패스워드 입력.");
					String pwd = sc.nextLine();
					System.out.println("수정할 이메일 입력.");
					String email = sc.nextLine();
					dao.update(pwd, email, id);
					int updateResult = dao.update(pwd, email, id);
					
					if(updateResult>0)
						System.out.println("수정완료!");
					else
						System.out.println("수정실패..");
				}else
					System.out.println("아이디가 없습니다.");
				
				
				break;
				
			case "3":	//삭제
				System.out.println("삭제할 아이디 입력.");
				id = sc.nextLine();
				checkResult=dao.isCheck(id);
				if(checkResult==true) {
					System.out.println("패스워드 입력");
					String pwd = sc.nextLine();
					dao.delete(id, pwd);
					int deleteResult = dao.delete(id, pwd);
					System.out.println(deleteResult);
					
					if(deleteResult>0)
						System.out.println("삭제성공!");
					else
						System.out.println("삭제실패..");
					
				}else
					System.out.println("아이디가 없습니다.");
				break;
				
			case "4":	//보기
				System.out.println("찾을 아이디 입력.");
				id = sc.nextLine();
				checkResult=dao.isCheck(id);
				if(checkResult==true) {
					MemberDTO selectResult = dao.select(id);
					System.out.println("아이디\t 이름\t 이메일\t\t    가입일자\t");
					System.out.println("-------------------------------------------------------");
					System.out.println(selectResult.getId()+"   "+ selectResult.getName()+"   "+selectResult.getEmail()+"   "+selectResult.getSignupdate());
				}else
					System.out.println("아이디가 없습니다.");
				
				break;
				
			case "5":	// 전체보기
				ArrayList<MemberDTO> getAllResult = dao.getAll();
				getAllResult = dao.getAll();
				for( MemberDTO index:getAllResult)
					System.out.println(index);
				break;
				
			case "6":	// 종료
				System.out.println("프로그램 종료.");
				System.exit(0);
			default:
				System.out.println("입력오류!!");
			}//	switch
			
			
			
			
		}//	while
		
		
	}

}
