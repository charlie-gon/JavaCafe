package jcafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getProduct
 */
@WebServlet("/getProduct")
public class getProductServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public getProductServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String item = request.getParameter("item_no");
		ProductVO vo = new ProductVO();
		vo.setItemNo("bean_001");
		
		ProductDAO dao = new ProductDAO();
		ProductVO result = dao.getProduct(vo);
		
		String json = "{";
		json += "\"item_no\":\"" + result.getItemNo() + "\"";
		json += ",\"item\":\"" + result.getItem() + "\"";
		json += ",\"category\":\"" + result.getCategory() + "\"";
		json += ",\"price\":\"" + result.getPrice() + "\"";
		json += ",\"link\":\"" + result.getLink() + "\"";
		json += ",\"content\":\"" + result.getContent() + "\"";
		json += ",\"like_it\":\"" + result.getLikeIt() + "\"";
		json += ",\"alt\":\"" + result.getAlt() + "\"";
		json += ",\"image\":\"" + result.getImage() + "\"";
		json += "}";
		
		response.getWriter().append(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
