package cart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.GetConnection;

public class CartBO {
	private CartDAO cDAO = new CartDAO();
	public int insertCart(Cart cart) throws ClassNotFoundException, SQLException {
	    return cDAO.insertCart(cart);
	}
	public int updateCart(Cart cart) throws ClassNotFoundException, SQLException {
	   return cDAO.updateCart(cart);
	}
	public Cart getCartByCustomerID(int customerID) throws ClassNotFoundException, SQLException {
		return cDAO.getCartByCustomerID(customerID);
	}
	public int delete(int userID) throws ClassNotFoundException, SQLException {
	    return cDAO.delete(userID);
	}


}
