package adminController.Order;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.Order;
import order.OrderBO;
import orderDetail.OrderDetailBO;

/**
 * Servlet implementation class Admin_EditOrderDetailController
 */
@WebServlet("/Admin_EditOrderDetailController")
public class Admin_EditOrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_EditOrderDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderDetailID = request.getParameter("orderDetailID") != null ? Integer.parseInt(request.getParameter("orderDetailID")) : 0;
		int orderID = request.getParameter("orderID") != null ? Integer.parseInt(request.getParameter("orderID")) : 0;
		int productID = request.getParameter("productID") != null ? Integer.parseInt(request.getParameter("productID")) : 0;
		int quantity = request.getParameter("quantity") != null ? Integer.parseInt(request.getParameter("quantity")) : 0;
		String action = request.getParameter("action");
		try {
		OrderBO oBO = new OrderBO();
		OrderDetailBO odBO = new OrderDetailBO();
		
		System.out.println(orderDetailID);
		System.out.println(orderID);
		System.out.println(productID);
		System.out.println(quantity);
		System.out.println(action);
		if(action.equals("delete")) {
				odBO.deleteOrderDetail(orderDetailID);
				Order order = oBO.getOrderByOrderID(orderID);
				order.setSum(order.getSum() - 1);
				oBO.updateOrder(order);
			
		}
		else {
				odBO.updateOrderDetail(orderID, productID, quantity);	
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Admin_OrderController?action=view&orderID="+orderID);
		rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			RequestDispatcher rd = request.getRequestDispatcher("Admin_OrderController?action=view&orderID="+orderID);
			rd.forward(request, response);
		} catch (SQLException e) {
			RequestDispatcher rd = request.getRequestDispatcher("Admin_OrderController?action=view&orderID="+orderID);
			rd.forward(request, response);
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
