package cartDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;

public class CartDetailBO {
	private CartDetailDAO cdDAO = new CartDetailDAO();
	public int insertCartDetail(CartDetail cartDetail) throws ClassNotFoundException, SQLException {
	    return cdDAO.insertCartDetail(cartDetail);
	}
	public int updateCartDetail(int cartID, int quantity, int productID) throws ClassNotFoundException, SQLException {
	    return cdDAO.updateCartDetail(cartID, quantity, productID);
	}
	public CartDetail checkProductExist(int cartID, int productID) throws ClassNotFoundException, SQLException {
		return cdDAO.checkProductExist(cartID, productID);
	}
	public int deleteCartDetail(int cartID, int productID) throws ClassNotFoundException, SQLException {
	    return cdDAO.deleteCartDetail(cartID, productID);
	}
	public List<CartDetail> getLstCartDetail(int cartID) throws ClassNotFoundException, SQLException{
		return cdDAO.getLstCartDetail(cartID);
	}
}
