package minipro_Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import minipro_DTO.MemberDTO;

public class MemberDAO {
	
	
	private Connection getConnection() {	// DB����
		String className = "oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="hr";
		String pwd="hr";
		
		Connection conn=null;
		 try {
			 Class.forName(className);
			 conn=DriverManager.getConnection(url, user, pwd);
			 
		 }catch(SQLException | ClassNotFoundException e) {
			 System.out.println(e);
		 }
		 return conn;
	 }
	
	
	public int insert(String id, String pwd, String name, String email) {	// ȸ�����
		Connection conn = getConnection();
		
		PreparedStatement pstmt=null;
		
		StringBuilder sql = new StringBuilder();
		sql.append(" 	insert into minipro 					");
		sql.append("	values (  ?, ?, ?, ?, sysdate )			");
		
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			close(pstmt, conn); 
		}
			return result;
	}
	
	
	public int update(String pwd, String email, String id) {	//��������
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		
		StringBuilder sql = new StringBuilder();
		sql.append(" 	update minipro	    	");
		sql.append("	set						");
		sql.append("		pwd = ?				");
		sql.append("	  , email = ?			");
		sql.append("	where id = ?			");
		
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, pwd);
			pstmt.setString(2, email);
			pstmt.setString(3, id);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			close(pstmt, conn); 
		}
			return result;
	}
	
	
	public int delete(String id, String pwd) {		// ��������
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		
			StringBuilder sql = new StringBuilder();
			sql.append(" 	delete minipro				");
			sql.append("	where id = ? and pwd = ?		");
			
			int result=0;
			try {
				pstmt=conn.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				result = pstmt.executeUpdate();
				
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				close(pstmt, conn); 
			}
				return result;
	}
	
	
	public MemberDTO select(String id) {	// ����ã��
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		StringBuilder sql = new StringBuilder();
		ResultSet rs=null;
		MemberDTO dto = null;
		sql.append("  select			 			 ");
		sql.append("         id						 ");
		sql.append("        , name					 ");
		sql.append("        , email					 ");
		sql.append("        , signupdate			 ");
		sql.append("  from minipro	     			 ");
		sql.append("  where id = ?	     			 ");
		
		try {
			pstmt=conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setSignupdate(rs.getString("signupdate"));
				
				return dto;
				
			}
		
			
		}catch ( SQLException e) {
			System.out.println(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(pstmt!=null) try {conn.close();} catch(SQLException e) {}
		}
			return dto;
		
	}
	
	
	public boolean isCheck(String id) {	// �ߺ��˻�
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		StringBuilder sql = new StringBuilder();
		ResultSet rs=null;
//		boolean check = false;
		
		try {
			sql.append("  select			 			 ");
			sql.append("         id						 ");
			sql.append("  from minipro	     			 ");
			
			pstmt=conn.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("id").equals(id))
					return true;
			}
		
			
		}catch ( SQLException e) {
			System.out.println(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(pstmt!=null) try {conn.close();} catch(SQLException e) {}
		}
			
			return false;
		}
	
	public ArrayList<MemberDTO> getAll() { //��ü����
		//db�����ؼ� �ڷḦ �޾��� ��.
		//	�ڷᱸ��: List, map, set => ���⼭�� List�� ������, ArrayList: db�����Ŀ� ArrayList�� ��Ƽ� ����.
		ArrayList<MemberDTO> arr = new ArrayList<MemberDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		StringBuilder sql = new StringBuilder();
		ResultSet rs=null;
		
		try {
			sql.append("  select			 			 ");
			sql.append("         id						 ");
			sql.append("         , name					 ");
			sql.append("         , email				 ");
			sql.append("         , signupdate			 ");
			sql.append("  from minipro	     			 ");
			
			pstmt=conn.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("Email"));
				dto.setSignupdate(rs.getString("signupdate"));
				
				arr.add(dto);
			}
		
		
		}catch ( SQLException e) {
			System.out.println(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(pstmt!=null) try {conn.close();} catch(SQLException e) {}
		}
			
			
			return arr;
	}

	private void close(PreparedStatement pstmt, Connection conn) {	// pstmt, conn close�ϴ� ���.
	
		if(pstmt!=null) try { pstmt.close();} catch(SQLException e) {}
		if(conn!=null) try {conn.close();} catch(SQLException e) {}
	
	}
	
	
}
