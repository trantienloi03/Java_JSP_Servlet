package orderView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cartView.CartView;
import connection.GetConnection;

public class OrderDetailViewBO {
	public List<OrderDetailView> getOrderDetails(int orderID) throws ClassNotFoundException, SQLException {
	    List<OrderDetailView> orderDetails = new ArrayList();
	    GetConnection kn = new GetConnection(); // Đối tượng để kết nối cơ sở dữ liệu
	    kn.KetNoi(); // Phương thức kết nối

	    // Câu lệnh SQL để lấy dữ liệu từ ViewOrderDetail dựa trên OrderID
	    String sql = "SELECT TOP (1000) [OrderDetailID]\r\n"
	    		+ "      ,[OrderID]\r\n"
	    		+ "      ,[ProductID]\r\n"
	    		+ "      ,[Quantity]\r\n"
	    		+ "      ,[Price]\r\n"
	    		+ "      ,[ProductName]\r\n"
	    		+ "      ,[Image]\r\n"
	    		+ "      ,[Total]\r\n"
	    		+ "      ,[FullName]\r\n"
	    		+ "      ,[Phone]\r\n"
	    		+ "      ,[Address]\r\n"
	    		+ "      ,[StatusID]\r\n"
	    		+ "  FROM [Java_TranTienLoi].[dbo].[ViewOrderDetail] WHERE (ISNULL(?, '') = '' OR OrderID = ?)";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql); // Chuẩn bị câu lệnh SQL
	    cmd.setInt(1, orderID); // Gán giá trị OrderID vào tham số SQL
	    cmd.setInt(2, orderID);
	    ResultSet rs = cmd.executeQuery(); // Thực thi câu lệnh SQL

	    // Lặp qua kết quả truy vấn và tạo danh sách đối tượng OrderDetail
	    while (rs.next()) {
	        int orderDetailID = rs.getInt(1);
	        int _orderID = rs.getInt(2);
	        int productID = rs.getInt(3);
	        int quantity = rs.getInt(4);
	        long price = rs.getLong(5);
	        String productName = rs.getString(6);
	        String image = rs.getString(7);
	        long total = rs.getLong(8);
	        String fullName = rs.getString(9);
	        String phone = rs.getString(10);
	        String address = rs.getString(11);
	        int statusID = rs.getInt(12);

	        // Thêm đối tượng OrderDetail vào danh sách
	        orderDetails.add(new OrderDetailView(orderDetailID, _orderID, productID, quantity, price, productName, image, total, fullName, phone, address, statusID));
	    }

	    // Đóng kết nối và ResultSet
	    rs.close();
	    kn.cn.close();

	    return orderDetails; // Trả về danh sách các OrderDetail
	}
	public long TongTien(int orderID) throws ClassNotFoundException, SQLException {
		long tongtien = 0;
		List<OrderDetailView> lst = this.getOrderDetails(orderID);
		for (OrderDetailView orderDetailView : lst) {
			tongtien += orderDetailView.getTotal();
		}
		return tongtien;
	}


}
