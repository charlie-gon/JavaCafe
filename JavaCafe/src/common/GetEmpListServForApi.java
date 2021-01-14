
// 210113
// for api/database_javascript.html

package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getEmpListApi")
public class GetEmpListServForApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetEmpListServForApi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		EmpDAO dao = new EmpDAO();
		List<EmployeeVO> list = dao.getEmpList();
		PrintWriter out = response.getWriter();
		
		String json = "[";
		int cnt = 1;
		for(EmployeeVO changon : list) {
			json += "{";
			json += "\"employeeId\":\"" + changon.getEmployeeId() + "\"";
			json += ",\"firstName\":\"" + changon.getFirstName() + "\"";
			json += ",\"lastname\":\"" + changon.getLastName() + "\"";
			json += ",\"email\":\"" + changon.getEmail() + "\"";
			json += ",\"phoneNumber\":\"" + changon.getPhoneNumber() + "\"";
			json += ",\"hireDate\":\"" + changon.getHireDate() + "\"";
			json += "}";
			if(list.size() != cnt++) {
				json = ",";
			}
		}
		
		json += "]";
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
