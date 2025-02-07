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

import cart.Cart;
import cart.CartBO;
import user.User;
import user.UserBO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String pass = request.getParameter("password");
			//String er = request.getParameter("error");
			if(username != null && pass!= null){
				UserBO uBO = new UserBO();
				CartBO cBO = new CartBO();
				
				User user = uBO.checkLogin(username, pass);
				System.out.println(user.toString());
				if(user != null){
					HttpSession session = request.getSession();
					int sum = cBO.getCartByCustomerID(user.getUserID()).getSum() != 0 ? cBO.getCartByCustomerID(user.getUserID()).getSum() : 0 ;
					Cart cart = cBO.getCartByCustomerID(user.getUserID());
					session.setAttribute("cartID", cart.getCartID() != 0 ? cart.getCartID() : 0 );
					session.setAttribute("sum", sum);
					session.setAttribute("user",user);
					if(user.getRole().getRoleID() == 2) {
						RequestDispatcher rd = request.getRequestDispatcher("Home");
						rd.forward(request, response);
						return;
					}
					RequestDispatcher rd = request.getRequestDispatcher("Admin_HomeController");
					rd.forward(request, response);
					return;
				} 
				else{
					request.setAttribute("error", "username or pass is not correct!");
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("client/Login.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Đăng nhập thất bại");
			RequestDispatcher rd = request.getRequestDispatcher("client/Login.jsp");
			rd.forward(request, response);
			return;
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
