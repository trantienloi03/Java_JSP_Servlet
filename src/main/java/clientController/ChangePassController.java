package clientController;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserBO;

/**
 * Servlet implementation class ChangePassController
 */
@WebServlet("/ChangePassController")
public class ChangePassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		HttpSession session = request.getSession();
		UserBO uBO  = new UserBO();
		String oldPass = request.getParameter("currentPassword") != null ? (String)request.getParameter("currentPassword") : "";
		String newPass = request.getParameter("newPassword") != null ? (String)request.getParameter("newPassword") : "";
		String confirmPass = request.getParameter("confirmPassword") != null ? (String)request.getParameter("confirmPassword") : "";
		int userID = request.getParameter("userID") != null ? Integer.parseInt(request.getParameter("userID"))  : 0;
		if(session.getAttribute("user") != null) {
			if(!newPass.equals(confirmPass)) {
				request.setAttribute("error", "Xác nhận mật khẩu sai");
				request.setAttribute("newPass", newPass);
				request.setAttribute("confirmPass", confirmPass);
				RequestDispatcher rd = request.getRequestDispatcher("client/ChangePass.jsp");
				rd.forward(request, response);
				return;
			}
			Boolean result = uBO.changePassword(userID, oldPass, newPass);
			if(result == true) {
				RequestDispatcher rd = request.getRequestDispatcher("client/Login.jsp");
				rd.forward(request, response);
				return;
			}
			String tb = "";
			if(result == false) {
				tb = "Mật khẩu cũ không đúng";
			}
			request.setAttribute("errorOldPass", tb);
			request.setAttribute("newPass", newPass);
			request.setAttribute("confirmPass", confirmPass);
			RequestDispatcher rd = request.getRequestDispatcher("client/ChangePass.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("client/Login.jsp");
			rd.forward(request, response);
		}
		} catch (Exception e) {
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
