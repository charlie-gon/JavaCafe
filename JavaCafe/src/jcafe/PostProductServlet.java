// 210114

package jcafe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostProductServlet
 */
@WebServlet("/PostProductServlet")
public class PostProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PostProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// 한글 인식할 수 있도록 설정
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		// parameter 이름은 html 파일의 name에 적용된 이름
		String itemNo = request.getParameter("itemNo");
		String item = request.getParameter("item");
		String category = request.getParameter("category");
		String price = request.getParameter("price");
		String content = request.getParameter("content");
		String likeIt = request.getParameter("likeIt");
		String image = request.getParameter("image");

		System.out.println(itemNo);
		System.out.println(item);
		System.out.println(category);
		System.out.println(price);
		System.out.println(content);
		System.out.println(likeIt);
		System.out.println(image);
		
		ProductVO vo = new ProductVO();
		vo.setItemNo(itemNo);
		vo.setItem(item);
		vo.setCategory(category);
		vo.setPrice(Integer.parseInt(price));
		vo.setContent(content);
		vo.setLikeIt(Integer.parseInt(likeIt));
		vo.setImage(image);
		
		ProductDAO dao = new ProductDAO();
		dao.insertProduct(vo);
		
		String script = "<script>location.href='jcafe/cafeList.html'</script>";
		response.getWriter().append(script);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
