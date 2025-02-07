package orderDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;

public class OrderDetailBO {
	private OrderDetailDAO odDAO = new OrderDetailDAO();
	public int insertOrderDetail(OrderDetail orderDetail) throws ClassNotFoundException, SQLException {
        return odDAO.insertOrderDetail(orderDetail);
    }
	public List<OrderDetail> getOrderDetails() throws ClassNotFoundException, SQLException {
       return odDAO.getOrderDetails();
    }

    // Phương thức lấy danh sách OrderDetail theo điều kiện OrderID
    public List<OrderDetail> getOrderDetailsByOrderID(int orderID) throws ClassNotFoundException, SQLException {
        return odDAO.getOrderDetailsByOrderID(orderID);
    }
    public boolean updateOrderDetail(int orderID, int productID, int quantity) throws ClassNotFoundException, SQLException {
        return odDAO.updateOrderDetail(orderID, productID, quantity);
    }
    public boolean deleteOrderDetail(int orderDetailID) throws ClassNotFoundException, SQLException {
        return odDAO.deleteOrderDetail(orderDetailID);
    }

}
