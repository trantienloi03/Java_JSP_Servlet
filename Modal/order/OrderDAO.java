package order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.GetConnection;

public class OrderDAO {
	public int insertOrder(Order order) throws ClassNotFoundException, SQLException {
	    int id = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    // Câu lệnh SQL
	    String sql = "INSERT INTO [Order] (TotalPrice, UserID, StatusID, Date, Sum, UserName, Address, Phone) "
	               + "VALUES (?, ?, ?, GETDATE(), ?, ?, ?, ?);";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    // Gán giá trị cho các tham số
	    cmd.setLong(1, order.getTotalPrice());
	    cmd.setInt(2, order.getUserID());
	    cmd.setInt(3, order.getStatusID());
	    cmd.setInt(4, order.getSum());
	    cmd.setString(5, order.getUserName());
	    cmd.setString(6, order.getAddress());
	    cmd.setString(7, order.getPhone());

	    // Thực thi câu lệnh INSERT
	    cmd.executeUpdate();

	    // Lấy ID mới được chèn vào
	    ResultSet rs = cmd.getGeneratedKeys();
	    if (rs.next()) {
	        id = rs.getInt(1); // Lấy ID từ cột đầu tiên của ResultSet
	    }

	    rs.close();
	    kn.cn.close();
	    return id;
	}
	public boolean updateOrder(Order order) throws ClassNotFoundException, SQLException {
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    // Câu lệnh SQL
	    String sql = "UPDATE [Order] "
	               + "SET TotalPrice = ?, UserID = ?, StatusID = ?, Date = ?, Sum = ?, UserName = ?, Address = ?, Phone = ? "
	               + "WHERE OrderID = ?";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Gán giá trị cho các tham số
	    cmd.setLong(1, order.getTotalPrice());
	    cmd.setInt(2, order.getUserID());
	    cmd.setInt(3, order.getStatusID());
	    cmd.setDate(4, new java.sql.Date(order.getDateTime().getTime()));
	    cmd.setInt(5, order.getSum());
	    cmd.setString(6, order.getUserName());
	    cmd.setString(7, order.getAddress());
	    cmd.setString(8, order.getPhone());
	    cmd.setInt(9, order.getOrderID());

	    // Thực thi
	    int rowsUpdated = cmd.executeUpdate();

	    kn.cn.close();
	    return rowsUpdated > 0;
	}
	public List<Order> getListOrderByUserID(int userID) throws ClassNotFoundException, SQLException{
		List<Order> order = new ArrayList<Order>();
		GetConnection kn = new GetConnection();
		kn.KetNoi();
		String sql = "SELECT TOP (1000) [OrderID]\r\n"
				+ "      ,[TotalPrice]\r\n"
				+ "      ,[UserID]\r\n"
				+ "      ,[StatusID]\r\n"
				+ "      ,[Date]\r\n"
				+ "      ,[Sum]\r\n"
				+ "      ,[UserName]\r\n"
				+ "      ,[Address]\r\n"
				+ "      ,[Phone]\r\n"
				+ "  FROM [Java_TranTienLoi].[dbo].[Order]\r\n"
				+ "  where (ISNULL(?, '') = '' OR UserID = ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, userID);
		cmd.setInt(2, userID);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int orderID = rs.getInt(1);
			long totalPrice = rs.getLong(2);
			int _userID = rs.getInt(3);
			int statusID = rs.getInt(4);
			Date ngayMua = rs.getDate(5);
			int quantity = rs.getInt(6);
			String userName = rs.getString(7);
			String address = rs.getString(8);
			String phone = rs.getString(9);
			
			order.add(new Order(orderID, _userID, statusID, totalPrice, ngayMua, quantity, userName, address, phone));
			
		}
		return order;
		
	}
	public boolean updateOrderStatus(int orderID, int statusID) throws ClassNotFoundException, SQLException {
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    // Câu lệnh SQL
	    String sql = "UPDATE [Order] "
	               + "SET StatusID = ? "
	               + "WHERE OrderID = ?";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Gán giá trị cho các tham số
	    cmd.setLong(1, statusID);
	    cmd.setInt(2, orderID);


	    // Thực thi
	    int rowsUpdated = cmd.executeUpdate();

	    kn.cn.close();
	    return rowsUpdated > 0;
	}
	public int getStatus(int orderID) throws ClassNotFoundException, SQLException {
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();
	    int status = 0;
	    // Câu lệnh SQL
	    String sql = "SELECT TOP (1000) [OrderID]\r\n"
	    		+ "      ,[StatusID]\r\n"
	    		+ "  FROM [Java_TranTienLoi].[dbo].[Order]\r\n"
	    		+ "  where OrderID = ?";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Gán giá trị cho các tham số
	    cmd.setLong(1, orderID);
	    ResultSet rs = cmd.executeQuery();
	    while(rs.next()) {
	    	status = rs.getInt(2);
	    }
	    rs.close();
	    kn.cn.close();
	    return status;
	}
	public Order getOrderByOrderID(int orderID) throws ClassNotFoundException, SQLException {
	    Order order = null; // Khởi tạo giá trị null
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    // Câu lệnh SQL
	    String sql = "SELECT * "
	               + "FROM [Order] WHERE OrderID = ?";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Gán giá trị cho tham số
	    cmd.setInt(1, orderID);

	    // Thực thi câu lệnh
	    ResultSet rs = cmd.executeQuery();

	    // Nếu có kết quả, lấy bản ghi đầu tiên
	    if (rs.next()) {
	        int _orderID = rs.getInt("OrderID");
	        long totalPrice = rs.getLong("TotalPrice");
	        int _userID = rs.getInt("UserID");
	        int statusID = rs.getInt("StatusID");
	        Date date = rs.getDate("Date");
	        int sum = rs.getInt("Sum");
	        String userName = rs.getString("UserName");
	        String address = rs.getString("Address");
	        String phone = rs.getString("Phone");

	        // Tạo đối tượng Order
	        order = new Order(_orderID, _userID, statusID, totalPrice, date, sum, userName, address, phone);
	    }

	    // Đóng kết nối
	    rs.close();
	    kn.cn.close();

	    return order; // Trả về đối tượng Order (hoặc null nếu không tìm thấy)
	}



}
