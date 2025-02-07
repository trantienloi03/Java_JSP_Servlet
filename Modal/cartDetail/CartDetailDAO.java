package cartDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cart.Cart;
import connection.GetConnection;
import factory.Factory;

public class CartDetailDAO {
	
	public int insertCartDetail(CartDetail cartDetail) throws ClassNotFoundException, SQLException {
	    int id = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql ="INSERT INTO CartDetail (Price, Quantity, CartID, ProductID) "
	    			+ "VALUES (?,?,?,?) "
	                + "SELECT SCOPE_IDENTITY(); ";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setLong(1, cartDetail.getPrice());
	    cmd.setInt(2, cartDetail.getQuantity());
	    cmd.setInt(3, cartDetail.getCartID());
	    cmd.setInt(4, cartDetail.getProductID());

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
	public int updateCartDetail(int cartID, int quantity, int productID) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "UPDATE CartDetail "
		    		+ " SET Quantity = ? "
		    		+ " WHERE CartID = ? and ProductID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setInt(1, quantity);
	    cmd.setInt(2, cartID);
	    cmd.setInt(3, productID);

	    affectedRows = cmd.executeUpdate(); // Trả về số dòng bị ảnh hưởng
	    kn.cn.close();
	    return affectedRows;
	}
	public int deleteCartDetail(int cartID, int productID) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = " delete CartDetail "
		    		+ " WHERE CartID = ? and ProductID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setInt(1, cartID);
	    cmd.setInt(2, productID);

	    affectedRows = cmd.executeUpdate(); // Trả về số dòng bị ảnh hưởng
	    kn.cn.close();
	    return affectedRows;
	}
	public CartDetail checkProductExist(int cartID, int productID) throws ClassNotFoundException, SQLException {
		CartDetail cartDetail = null;
		GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "select * from CartDetail "
		    		+ " WHERE CartID = ? and ProductID =?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setInt(1, cartID);
	    cmd.setInt(2, productID);
	    ResultSet rs = cmd.executeQuery();
	    while(rs.next()) {
	    	int _cartID = rs.getInt("CartID");
	    	int quantity = rs.getInt("Quantity");
	    	long price = rs.getLong("Price");
	    	int cartDetaiID = rs.getInt("CartDetailID");
	    	int _productID = rs.getInt("ProductID");
	    	
	    	cartDetail = new CartDetail(cartDetaiID, price, quantity, _cartID, _productID);
	    }
	    
	    rs.close();
	    kn.cn.close();
		return cartDetail;
	}
	public List<CartDetail> getLstCartDetail(int cartID) throws ClassNotFoundException, SQLException{
		List<CartDetail> cartDetail = new ArrayList<CartDetail>();
		GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "SELECT TOP (1000) [CartDetailID]\r\n"
	    		+ "      ,[Price]\r\n"
	    		+ "      ,[Quantity]\r\n"
	    		+ "      ,[CartID]\r\n"
	    		+ "      ,[ProductID]\r\n"
	    		+ "  FROM [Java_TranTienLoi].[dbo].[CartDetail]\r\n"
	    		+ "  where CartID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setInt(1, cartID);
	    ResultSet rs = cmd.executeQuery();
	    while(rs.next()) {
	    	int _cartID = rs.getInt("CartID");
	    	int quantity = rs.getInt("Quantity");
	    	long price = rs.getLong("Price");
	    	int cartDetaiID = rs.getInt("CartDetailID");
	    	int _productID = rs.getInt("ProductID");
	    	
	    	cartDetail.add(new CartDetail(cartDetaiID, price, quantity, _cartID, _productID));
	    }
	    
	    rs.close();
	    kn.cn.close();
		return cartDetail;
	}
	

}
