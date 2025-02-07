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
import cartDetail.CartDetail;
import cartDetail.CartDetailBO;
import product.Product;
import product.ProductBO;
import user.User;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				HttpSession session = request.getSession();
				CartBO cBO = new CartBO();
				CartDetailBO cdBO = new CartDetailBO();
				ProductBO pBO = new ProductBO();
				if(session.getAttribute("user") != null) {
					User user = (User) session.getAttribute("user");
					int userID = user.getUserID();
					int productID = request.getParameter("productID") != null ? Integer.parseInt(request.getParameter("productID")) : 0;
					int quantity = request.getParameter("quantity") != null ? Integer.parseInt(request.getParameter("quantity")) : 0;
					System.out.println(userID);
					System.out.println(productID);
					System.out.println(quantity);
					Cart cart = cBO.getCartByCustomerID(userID);
					session.setAttribute("cartID", cart.getCartID());
					if(cart == null) {
						Cart newCart = new Cart();
						newCart.setSum(0);
						newCart.setUserID(userID);
						cart = newCart;
						cBO.insertCart(cart);
					}
					cart = cBO.getCartByCustomerID(userID);
					Product productExist = pBO.getProductByID(productID);
					if(productExist != null) {
						CartDetail cartDetailExist = cdBO.checkProductExist(cart.getCartID(), productID);
						if(cartDetailExist == null) {
							CartDetail newCartDetail = new CartDetail();
							newCartDetail.setCartID(cart.getCartID());
							newCartDetail.setQuantity(quantity);
							newCartDetail.setPrice(productExist.getPrice());
							newCartDetail.setProductID(productID);
							cdBO.insertCartDetail(newCartDetail);
							int sum = (int)session.getAttribute("sum");
							sum +=1;
							session.setAttribute("sum", sum);
							cart.setSum(sum);
							cBO.updateCart(cart);	
						}
						else {
							int _quantity = cartDetailExist.getQuantity() + quantity;
							cdBO.updateCartDetail(cart.getCartID(), _quantity, productID);
						}
					}
					RequestDispatcher rd = request.getRequestDispatcher("Home");
					rd.forward(request, response);
				
				}
				else {
					RequestDispatcher rd = request.getRequestDispatcher("client/Login.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
