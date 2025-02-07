package cart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cartDetail.CartDetail;
import connection.GetConnection;

public class CartDAO {
	
	public int insertCart(Cart cart) throws ClassNotFoundException, SQLException {
	    int id = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql ="INSERT INTO Cart (Sum, UserID) "
	    			+ "VALUES (?,?) "
	                + "SELECT SCOPE_IDENTITY(); ";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setInt(1, cart.getSum());
	    cmd.setInt(2, cart.getUserID());

	    boolean hasResultSet = cmd.execute();

	    if (hasResultSet) {
	        ResultSet rs = cmd.getResultSet();
	        if (rs.next()) {
	            id = rs.getInt(1); // Trả về ID mới hoặc -1 nếu đã tồn tại
	        }
	        rs.close();
	    }
	    kn.cn.close();
	    return id;
	}
	public int updateCart(Cart cart) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "UPDATE Cart "
		    		+ " SET Sum = ? "
		    		+ " WHERE UserID = ? and CartID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setInt(1, cart.getSum());
	    cmd.setInt(2, cart.getUserID());
	    cmd.setInt(3, cart.getCartID());


	    affectedRows = cmd.executeUpdate(); // Trả về số dòng bị ảnh hưởng
	    kn.cn.close();
	    return affectedRows;
	}
	public Cart getCartByCustomerID(int customerID) throws ClassNotFoundException, SQLException {
		Cart cart = null;
		GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "select * from Cart "
		    		+ " WHERE UserID = ? ";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setInt(1, customerID);
	    ResultSet rs = cmd.executeQuery();
	    while(rs.next()) {
	    	int cartID = rs.getInt("CartID");
	    	int sum = rs.getInt("Sum");
	    	int userID = rs.getInt("UserID");
	    	
	    	cart = new Cart(cartID, sum, userID);
	    }
	    rs.close();
	    kn.cn.close();
	    return cart;	
	}
	public int delete(int userID) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "  delete from Cart\r\n"
	    		+ "  where UserID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setInt(1, userID);



	    affectedRows = cmd.executeUpdate(); // Trả về số dòng bị ảnh hưởng
	    kn.cn.close();
	    return affectedRows;
	}

}
