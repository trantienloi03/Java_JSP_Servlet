package cartView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;

public class CartViewBO {
	public List<CartView> getCartView(int userID) throws ClassNotFoundException, SQLException{
		List<CartView> cartViews = new ArrayList<CartView>();
		GetConnection kn = new GetConnection();
		kn.KetNoi();
		String sql = "SELECT TOP (1000) [Price]\r\n"
				+ "      ,[Quantity]\r\n"
				+ "      ,[ProductName]\r\n"
				+ "      ,[Image]\r\n"
				+ "      ,[Total]\r\n"
				+ "      ,[CartID]\r\n"
				+ "      ,[ProductID]\r\n"
				+ "      ,[UserID]\r\n"
				+ "  FROM [Java_TranTienLoi].[dbo].[ViewCart]\r\n"
				+ "  Where UserID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, userID);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			long price = rs.getLong(1);
			int quantity = rs.getInt(2);
			String productName = rs.getString(3);
			String image = rs.getString(4);
			long total = rs.getLong(5);
			int cartID = rs.getInt(6);
			int prductId = rs.getInt(7);
			int _userID = rs.getInt(8);
			cartViews.add(new CartView(cartID, prductId, _userID, price, quantity, total, productName, image));

		}
		rs.close();
		kn.cn.close();
		return cartViews;
	}
	public long TongTien(int userID) throws ClassNotFoundException, SQLException {
		long tongtien = 0;
		List<CartView> lst = this.getCartView(userID);
		for (CartView cartView : lst) {
			tongtien += cartView.getTotal();
		}
		return tongtien;
	}

}
