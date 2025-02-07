package clientController;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import role.Role;
import role.RoleBO;
import user.User;
import user.UserBO;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/Register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		UserBO uBO = new UserBO();
		RoleBO rBO = new RoleBO();
		String fullName = request.getParameter("fullname") != null ? request.getParameter("fullname"):"" ;
		String username = request.getParameter("username")!= null ? request.getParameter("username"):"";
		String address = request.getParameter("address")!= null ? request.getParameter("address"):"";
		String phone = request.getParameter("phone")!= null ? request.getParameter("phone"):"";
		String pass = request.getParameter("password")!= null ? request.getParameter("password"):"";
		String roleID = request.getParameter("roleID")!= null ? request.getParameter("roleID"):"2";
		
		System.out.println(fullName);
		System.out.println(username);
		System.out.println(address);
		System.out.println(pass);
		System.out.println(phone);
		System.out.println(roleID);
		if(fullName != "") {
			Role role = rBO.getRoleById(Integer.parseInt(roleID));
			User user = new User(0, username, fullName, phone, address, pass, role, roleID);
			System.out.println(role.toString());
			System.out.println(user.toString());
			int id = uBO.InsertUser(user);
			if(id != -1) {
				RequestDispatcher rd = request.getRequestDispatcher("client/Login.jsp");
				rd.forward(request, response);
				return;
			}
			request.setAttribute("error", "Tai khoan da ton tai");
			RequestDispatcher rd = request.getRequestDispatcher("client/Register.jsp");
			rd.forward(request, response);
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("client/Register.jsp");
		rd.forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
