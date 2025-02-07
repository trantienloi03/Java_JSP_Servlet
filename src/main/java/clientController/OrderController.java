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

import order.Order;
import order.OrderBO;
import orderView.OrderDetailView;
import orderView.OrderDetailViewBO;
import user.User;
import user.UserBO;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			OrderBO oBO = new OrderBO();
			OrderDetailViewBO odvBO = new OrderDetailViewBO();
			UserBO uBO = new UserBO();
			int orderID = request.getParameter("orderID") != null ? Integer.parseInt(request.getParameter("orderID")) : 0;
			String action = request.getParameter("action") != null ? (String) request.getParameter("action") : "";
			
			if(action.equals("view")) {
				User user = (User) session.getAttribute("user");
				List<OrderDetailView> lst = odvBO.getOrderDetails(orderID);
				long tongtien = odvBO.TongTien(orderID);
				int statusID = oBO.getStatus(orderID);
				request.setAttribute("lst", lst);
				request.setAttribute("user", user);
				request.setAttribute("Total", tongtien);
				request.setAttribute("orderID", orderID);
				request.setAttribute("statusID", statusID);
				RequestDispatcher rd = request.getRequestDispatcher("client/OrderDetail.jsp");
				rd.forward(request, response);
				return;
			}
			else if(action.equals("cancel")) {
				oBO.updateOrderStatus(orderID, 3);
				response.sendRedirect("OrderController");
				return;
			}
			User user = (User)session.getAttribute("user");
			List<Order> lstOrder = oBO.getListOrderByUserID(user.getUserID());
			for (Order order : lstOrder) {
				System.out.println(order.toString());
			}
			request.setAttribute("lstOrder", lstOrder);
			RequestDispatcher rd = request.getRequestDispatcher("client/Ordered.jsp");
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
