package clientController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import order.Order;
import order.OrderBO;
import orderDetail.OrderDetail;
import orderDetail.OrderDetailBO;
import user.User;

/**
 * Servlet implementation class ConfirmOrder
 */
@WebServlet("/ConfirmOrder")
public class ConfirmOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			CartDetailBO cdBO = new CartDetailBO();
			OrderBO oBO = new OrderBO();
			OrderDetailBO odBO = new OrderDetailBO();
			CartBO cBO = new CartBO();
			
			User user = (User)session.getAttribute("user");
			int cartID =(Integer)session.getAttribute("cartID");
			int sum = 0;
			long totalPrice = 0;
			Order order = new Order();
			List<CartDetail> lstCartDetail = cdBO.getLstCartDetail(cartID);
			for (CartDetail cartDetail : lstCartDetail) {
				System.out.println(cartDetail.toString());
				sum+=1;
				totalPrice += cartDetail.getPrice()*cartDetail.getQuantity();
				
			}
			System.out.println(sum);
			System.out.println(totalPrice);
			order.setStatusID(1);
			order.setPhone(user.getPhone() !=null? user.getPhone() : "");
			order.setUserID(user.getUserID());
			order.setUserName(user.getFullName());
			order.setAddress(user.getAddress());
			order.setSum(sum);
			order.setTotalPrice(totalPrice);
			
			int orderID = oBO.insertOrder(order);
			System.out.println(orderID);
			
			OrderDetail orderDetail = new OrderDetail();
			for (CartDetail cartDetail : lstCartDetail) {
				orderDetail.setOrderID(orderID);
				orderDetail.setPrice(cartDetail.getPrice());
				orderDetail.setQuantity(cartDetail.getQuantity());
				orderDetail.setProductID(cartDetail.getProductID());
				
				cdBO.deleteCartDetail(cartDetail.getCartID(), cartDetail.getProductID());
				odBO.insertOrderDetail(orderDetail);	
			}
			Cart cart = cBO.getCartByCustomerID(user.getUserID());
			cart.setSum(0);
			cBO.updateCart(cart);
			session.setAttribute("sum", 0);
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("OrderController");
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
