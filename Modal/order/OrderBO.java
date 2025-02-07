package order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.GetConnection;

public class OrderBO {
	private OrderDAO oDAO = new OrderDAO();
	public int insertOrder(Order order) throws ClassNotFoundException, SQLException {
	    return oDAO.insertOrder(order);
	}
	public boolean updateOrder(Order order) throws ClassNotFoundException, SQLException {
	   return oDAO.updateOrder(order);
	}
	public List<Order> getListOrderByUserID(int userID) throws ClassNotFoundException, SQLException{
		return oDAO.getListOrderByUserID(userID);
	}
	public boolean updateOrderStatus(int orderID, int statusID) throws ClassNotFoundException, SQLException {
	    return oDAO.updateOrderStatus(orderID, statusID);
	}
	public int getStatus(int orderID) throws ClassNotFoundException, SQLException {
	    return oDAO.getStatus(orderID);
	}
	public Order getOrderByOrderID(int orderID) throws ClassNotFoundException, SQLException {
	    return oDAO.getOrderByOrderID(orderID);
	}
}
