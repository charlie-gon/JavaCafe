// 210112

package jcafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	
	Connection conn = null;
	
	public ProductDAO() {
		
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
		
	} // end of 생성자
	
	// 210114
	
	public ProductVO getProduct(ProductVO vo) {
		String sql = "select * from product where item_no = ?";
		ProductVO v = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getItemNo());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				v = new ProductVO();
				v.setAlt(rs.getString("alt"));
				v.setCategory(rs.getString("category"));
				v.setContent(rs.getString("content"));
				v.setImage(rs.getString("image"));
				v.setItem(rs.getString("item"));
				v.setItemNo(rs.getString("item_no"));
				v.setLikeIt(rs.getInt("like_it"));
				v.setLink(rs.getString("link"));
				v.setPrice(rs.getInt("price"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return v;
	}
	
	public List<ProductVO> getProductList(){
		
		String sql = "select * from product order by 1";
		List<ProductVO> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setItemNo(rs.getString("item_no"));
				vo.setItem(rs.getString("item"));
				vo.setCategory(rs.getString("category"));
				vo.setPrice(rs.getInt("price"));
				vo.setLink(rs.getString("link"));
				vo.setContent(rs.getString("content"));
				vo.setLikeIt(rs.getInt("like_it"));
				vo.setAlt(rs.getString("alt"));
				vo.setImage(rs.getString("image"));
				list.add(vo);
				
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
		
		
		return list;
	}
	
	// 210114
	// 파일 업로드용 
	// product.html
	public void insertProduct(ProductVO vo) {
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?)";
		
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getItemNo());
			pstmt.setString(2, vo.getItem());
			pstmt.setString(3, vo.getCategory());
			pstmt.setInt(4, vo.getPrice());
			pstmt.setString(5, vo.getLink());
			pstmt.setString(6, vo.getContent());
			pstmt.setInt(7, vo.getLikeIt());
			pstmt.setString(8, vo.getAlt());
			pstmt.setString(9, vo.getImage());
			cnt = pstmt.executeUpdate();
			System.out.println(cnt + "건 입력");
					
			
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
		
	}

} // end of class
