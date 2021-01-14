// 210114

package jcafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")

// 파일 크기 설정
// C 드라이브 밑에 tmp 폴더 생성해야
@MultipartConfig(location = "c:/tmp", maxFileSize = 1024000L, maxRequestSize=-1, fileSizeThreshold=1024)

public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileUpload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		// 한글 인식할 수 있도록 설정
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Collection<Part> parts = request.getParts();
		String filename = "";
		
		// /images 경로는
		// C:\Dev\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JavaCafe\images
		String filepath = request.getServletContext().getRealPath("/images");
		for(Part part : parts) {
			filename = part.getSubmittedFileName();
			part.write(filepath + "/" + part.getSubmittedFileName());
		}
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("opener.frm.image.value='" + filename + "';");
		out.print("window.close();");
		out.print("</script>");
	}

}
