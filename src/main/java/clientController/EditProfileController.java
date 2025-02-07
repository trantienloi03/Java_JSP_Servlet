package clientController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;
import user.UserBO;

/**
 * Servlet implementation class EditProfileController
 */
@WebServlet("/EditProfileController")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileController() {
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
			String fullName = request.getParameter("fullName") != null ? (String)request.getParameter("fullName") : "";
			String email = request.getParameter("email") != null ? (String)request.getParameter("email") : "";
			String phone = request.getParameter("phone") != null ? (String)request.getParameter("phone") : "";
			String address = request.getParameter("address") != null ? (String)request.getParameter("address") : "";
			int userID = request.getParameter("userID") != null ? Integer.parseInt(request.getParameter("userID"))  : 0;
			if(session.getAttribute("user") != null) {
				String error = "";
				String success = "";
				User user = (User)session.getAttribute("user");
				User kh = uBO.getUserById(user.getUserID());
				System.out.println(user.toString());
				if(fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
					error = "Vui lòng nhập đầy đủ thông tin";
					request.setAttribute("error", error);
					RequestDispatcher rd = request.getRequestDispatcher("client/EditProfile.jsp");
					rd.forward(request, response);
					return;
				}
				kh.setFullName(fullName);
				kh.setAddress(address);
				kh.setPhone(phone);
				kh.setUserName(email);
				uBO.updateUser(kh);
				RequestDispatcher rd = request.getRequestDispatcher("client/EditProfile.jsp");
				rd.forward(request, response);
				return;
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
