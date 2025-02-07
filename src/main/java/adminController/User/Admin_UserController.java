package adminController.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.User;
import user.UserBO;

/**
 * Servlet implementation class Admin_UserController
 */
@WebServlet("/Admin_UserController")
public class Admin_UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserBO uBO = new UserBO();
			List<User> lstUser = new ArrayList<User>();
			String indexcurrent = request.getParameter("index");
			int index = 1;
			if(indexcurrent != null) {
				 index = Integer.parseInt(indexcurrent);
				 System.out.println(index);
			}
			int total;
			total = uBO.Count(2);
			int Page = total/10;
			if(total % 10 !=0)
				Page +=1;
			lstUser = uBO.getListUser(index, 2);
			request.setAttribute("lstUser", lstUser);
			request.setAttribute("index", index);
			request.setAttribute("Page", Page);
			RequestDispatcher rd = request.getRequestDispatcher("admin/User/User.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
