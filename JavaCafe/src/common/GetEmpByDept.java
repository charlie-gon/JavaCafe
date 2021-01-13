// 210113
// 차트 만들기

package common;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetEmpByDept
 */
@WebServlet("/getMembersByDept")
public class GetEmpByDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetEmpByDept() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		EmpDAO dao = new EmpDAO();
		Map<String, Integer> map = dao.getMemberByDept();
		Set<String> keySet = map.keySet(); // map의 key값 가져와서 Set에 담아주기
		
		int cnt = 1;
		String json = "[";
		for(String key : keySet) {
			// System.out.println(key + ' ' + map.get(key));
			json += "{";
			json += " \" " + key + " \": " + map.get(key);
			json += "}";
			
			// 마지막에 콤마(,) 제외
			if(map.size() != cnt++) {
				json += ",";
			}
		}
		
		json += "]";
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
