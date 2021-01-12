//210105

package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	
	Connection conn = null;
	
	public EmpDAO() { // static이므로 인스턴스 없이 바로 실행 가능
		try {
			String user = "hr"; // hr로 수정
			String pw = "hr"; // hr로 수정
			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database에 연결되었습니다.\n");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unknown error");
			e.printStackTrace();
		}
	}//end of 생성자
	
	// 데이터 한 건 입력
	public EmployeeVO insertEmp(EmployeeVO vo) {
		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "select * from emp_temp where employee_id = ?";
		String sql = "insert into emp_temp(employee_id, first_name, last_name, email, hire_date, job_id)"
				+ "values(?,?,?,?,sysdate,?)";
		
		int r = 0;
		String newSeq = null;
		EmployeeVO newVO = new EmployeeVO();
		
		// String sql1
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				newSeq = rs.getString(1);
			}
			// String sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newSeq);
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getLastName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getJobId());
			r = pstmt.executeUpdate();
			
			System.out.println(r + "건 입력.");
			
			// String sql2
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, newSeq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				newVO.setEmail(rs.getString("email"));
				newVO.setEmployeeId(rs.getInt("employee_id"));
				newVO.setFirstName(rs.getString("first_name"));
				newVO.setHireDate(rs.getString("hire_date"));
				newVO.setJobId(rs.getString("job_id"));
				newVO.setLastName(rs.getString("last_name"));
				newVO.setPhoneNumber(rs.getString("phone_number"));
				newVO.setSalary(rs.getInt("salary"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newVO;
		
	}// end of insertEmp
	
	// 삭제 기능 추가_210106
	public boolean deleteEmp(EmployeeVO vo) {
		String sql = "delete from emp_temp where employee_id = ?";
		int r = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getEmployeeId());
		
			//executeUpdate() = 결과 값을 받아올 필요가 없을 때
			//ex) insert, update, delete
			r = pstmt.executeUpdate();
			
			System.out.println(r + "건 삭제됨.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//r값 1이면 true or false
		return r == 1 ? true : false;
		
	} // end of deleteEmp
	
	public List<EmployeeVO> getEmpList() {
		String sql = "select * from emp_temp order by 1 desc";
		List<EmployeeVO> list = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//executeQuery() = 결과 값이 발생하여 값을 받아와야 할 때
			//ex) select
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVO vo = new EmployeeVO();
				vo.setEmployeeId(rs.getInt("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhoneNumber(rs.getString("phone_number"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setJobId(rs.getString("job_id"));
				vo.setSalary(rs.getInt("salary"));
				
				list.add(vo);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}//end of getEmpList()
	
	// tr 누르면 데이터 삽입
	public EmployeeVO updateEmp(EmployeeVO vo) {
		
		
		int r = 0;
		String sql = " update emp_temp "
					+ "set first_name=?,last_name=?,email=?,job_id=? where employee_id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getJobId());	
			pstmt.setInt(5, vo.getEmployeeId());
			r = pstmt.executeUpdate();
			System.out.println(r + "건 업데이트 되었다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		
		return vo;
	}// end of updateEmp
	
	
}
