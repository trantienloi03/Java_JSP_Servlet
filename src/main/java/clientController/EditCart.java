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
import cartDetail.CartDetailBO;
import user.User;

/**
 * Servlet implementation class EditCart
 */
@WebServlet("/EditCart")
public class EditCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				CartBO cBO = new CartBO();
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				int userID = user.getUserID();
				Cart cart = cBO.getCartByCustomerID(userID);
				
				int cartID = request.getParameter("cartID") != null ? Integer.parseInt(request.getParameter("cartID")) : 0;
				int productID = request.getParameter("productID") != null ? Integer.parseInt(request.getParameter("productID")) : 0;
				int quantity = request.getParameter("quantity") != null ? Integer.parseInt(request.getParameter("quantity")) : 0;
				String action = request.getParameter("action");
				
				System.out.println(cartID);
				System.out.println(productID);
				System.out.println(quantity);
				System.out.println(action);
				CartDetailBO cdBO = new CartDetailBO();
				if(action.equals("delete")) {
					cdBO.deleteCartDetail(cartID, productID);
					int sum = (Integer)session.getAttribute("sum");
					System.out.println(sum);
					if(sum>0){
						sum = sum - 1;
						session.setAttribute("sum", sum);
						cart.setSum(sum);
						cBO.updateCart(cart);
					}
					
				}
				else {
					cdBO.updateCartDetail(cartID, quantity, productID);
				}
				RequestDispatcher rd = request.getRequestDispatcher("CartController");
				rd.forward(request, response);
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
