package orderDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;

public class OrderDetailDAO {
	public int insertOrderDetail(OrderDetail orderDetail) throws ClassNotFoundException, SQLException {
        int id = 0;
        GetConnection kn = new GetConnection();
        kn.KetNoi();

        String sql = "INSERT INTO OrderDetail (Price, Quantity, OrderID, ProductID) "
                   + "VALUES (?, ?, ?, ?); "
                   + "SELECT SCOPE_IDENTITY();";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        // Gán giá trị cho các tham số
        cmd.setLong(1, orderDetail.getPrice());
        cmd.setInt(2, orderDetail.getQuantity());
        cmd.setInt(3, orderDetail.getOrderID());
        cmd.setInt(4, orderDetail.getProductID());

        boolean hasResultSet = cmd.execute();

        if (hasResultSet) {
            ResultSet rs = cmd.getResultSet();
            if (rs.next()) {
                id = rs.getInt(1); // Lấy OrderDetailID vừa được chèn
            }
        }
        kn.cn.close();
        return id;
    }
	public List<OrderDetail> getOrderDetails() throws ClassNotFoundException, SQLException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        GetConnection kn = new GetConnection();
        kn.KetNoi();

        String sql = "SELECT OrderDetailID, Price, Quantity, OrderID, ProductID FROM OrderDetail";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        ResultSet rs = cmd.executeQuery();

        while (rs.next()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderDetailID(rs.getInt("OrderDetailID"));
            orderDetail.setPrice(rs.getLong("Price"));
            orderDetail.setQuantity(rs.getInt("Quantity"));
            orderDetail.setOrderID(rs.getInt("OrderID"));
            orderDetail.setProductID(rs.getInt("ProductID"));
            orderDetails.add(orderDetail);
        }
        kn.cn.close();
        return orderDetails;
    }

    // Phương thức lấy danh sách OrderDetail theo điều kiện OrderID
    public List<OrderDetail> getOrderDetailsByOrderID(int orderID) throws ClassNotFoundException, SQLException {
        List<OrderDetail> orderDetails = new ArrayList();
        GetConnection kn = new GetConnection();
        kn.KetNoi();

        String sql = "SELECT OrderDetailID, Price, Quantity, OrderID, ProductID FROM OrderDetail WHERE OrderID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setInt(1, orderID);

        ResultSet rs = cmd.executeQuery();

        while (rs.next()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderDetailID(rs.getInt("OrderDetailID"));
            orderDetail.setPrice(rs.getLong("Price"));
            orderDetail.setQuantity(rs.getInt("Quantity"));
            orderDetail.setOrderID(rs.getInt("OrderID"));
            orderDetail.setProductID(rs.getInt("ProductID"));
            orderDetails.add(orderDetail);
        }
        kn.cn.close();
        return orderDetails;
    }
    public boolean updateOrderDetail(int orderID, int productID, int quantity) throws ClassNotFoundException, SQLException {
        boolean isUpdated = false;
        GetConnection kn = new GetConnection();
        kn.KetNoi();

        String sql = "UPDATE OrderDetail SET Quantity = ? WHERE OrderID = ? AND ProductID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        // Gán giá trị cho các tham số
        cmd.setInt(1, quantity);
        cmd.setInt(2, orderID);
        cmd.setInt(3, productID);

        int rowsAffected = cmd.executeUpdate(); // Trả về số dòng bị ảnh hưởng
        if (rowsAffected > 0) {
            isUpdated = true; // Cập nhật thành công
        }

        kn.cn.close();
        return isUpdated;
    }
    public boolean deleteOrderDetail(int orderDetailID) throws ClassNotFoundException, SQLException {
        boolean isDeleted = false;
        GetConnection kn = new GetConnection();
        kn.KetNoi();

        String sql = "DELETE FROM OrderDetail WHERE OrderDetailID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        // Gán giá trị cho tham số
        cmd.setInt(1, orderDetailID);

        int rowsAffected = cmd.executeUpdate(); // Trả về số dòng bị ảnh hưởng
        if (rowsAffected > 0) {
            isDeleted = true; // Xóa thành công
        }

        kn.cn.close();
        return isDeleted;
    }

}
