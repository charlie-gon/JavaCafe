// 210113

package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetScheduleServlet
 */
@WebServlet("/getSchedules")
public class GetScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetScheduleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		EmpDAO dao = new EmpDAO();
		List<Schedule> list = dao.getScheduleList();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int cnt = 1;
		String json = "[";
		for(Schedule schedule : list) {
			json += "{";
			json += "\"title\":\"" + schedule.getTitle() + "\"";
			json += ",\"start\":\"" + schedule.getStartDate() + "\"";		
			json += ",\"end\":\"" + schedule.getEndDate() + "\"";
			json += ",\"url\":\"" + schedule.getUrl() + "\"";
			json += "}";
			if(list.size() != cnt++) {
				json += ",";
			}
		}
		json += "]";
		out.print(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
