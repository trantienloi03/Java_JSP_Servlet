package product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.GetConnection;
import factory.Factory;
import promotion.Promotion;
import target.Target;



public class ProductDAO {
	public static int Count(int factoryID ,int targetID, String ProductName ) throws Exception{
		int total = 0;
		GetConnection cnn = new GetConnection();
		cnn.KetNoi();
		String sql = "SELECT COUNT(*) AS TongSoSP FROM Product                  \r\n"
				+ "    WHERE (ISNULL(?, '') = '' OR FactoryID = ?)    \r\n"
				+ "      AND (ISNULL(?, '') = '' OR TargetID = ?)    \r\n"
				+ "      AND (ISNULL(?, '') = '' OR ProductName LIKE N'%' + ? + '%'); ";
		PreparedStatement cmd = cnn.cn.prepareStatement(sql);
		cmd.setInt(1, factoryID);
		cmd.setInt(2, factoryID);
		cmd.setInt(3, targetID);
		cmd.setInt(4, targetID);
		cmd.setString(5, ProductName);
		cmd.setString(6, ProductName);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			 total = rs.getInt(1);
		}
		rs.close();
		cnn.cn.close();
		return total;
	}
	public ArrayList<Product> getListProducts(int index, int factoryID ,int targetID, String ProductName) throws Exception{
		ArrayList<Product> lst = new ArrayList<Product>();

		GetConnection cnn = new GetConnection();
		cnn.KetNoi();
		String sql = "SELECT * FROM Products                  \r\n"
				+ "    WHERE (ISNULL(?, '') = '' OR FactoryID = ?)    \r\n"
				+ "      AND (ISNULL(?, '') = '' OR TargetID = ?)    \r\n"
				+ "      AND (ISNULL(?, '') = '' OR ProductName LIKE N'%' + ? + '%')"
				+ "    ORDER BY ProductID                                 \r\n"
				+ "    OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY;";
		
		PreparedStatement cmd = cnn.cn.prepareStatement(sql);
		cmd.setInt(1, factoryID);
		cmd.setInt(2, factoryID);
		cmd.setInt(3, targetID);
		cmd.setInt(4, targetID);
		cmd.setString(5, ProductName);
		cmd.setString(6, ProductName);
		cmd.setInt(7, (index-1)*12);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("ProductID");
			String detailDesc = rs.getString("DetailDesc");
			String shortDesc = rs.getString("ShortDesc");
			String productName = rs.getString("ProductName");
			String image = rs.getString("Image");
			String factoryName = rs.getString("Name");
			String targetName = rs.getString("TargetName");
			double discount = rs.getDouble("Discount_percent");
			long price = rs.getLong("Price");
			int factoryID1 = rs.getInt("FactoryID");
			int targetID1 = rs.getInt("TargetID");
			int promotionID = rs.getInt("PromotionID");
			Factory factory = new Factory(factoryID1, factoryName);
			Target target = new Target(targetID1, targetName);
			Promotion promotion = new Promotion(promotionID, discount);
			Product product = new Product(id, detailDesc, shortDesc, productName, image, price, factory, target, promotion);
			lst.add(product);
		}
		rs.close();
		cnn.cn.close();
		
		return lst;
	}
	public Product getProductByName (String name) throws Exception, SQLException {
		Product p = new Product();
		GetConnection cnn = new GetConnection();
		cnn.KetNoi();
		String sql = "select * from Products where ProductName = ?";
		PreparedStatement cmd = cnn.cn.prepareStatement(sql);
		cmd.setString(1, name);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("ProductID");
			String detailDesc = rs.getString("DetailDesc");
			String shortDesc = rs.getString("ShortDesc");
			String productName = rs.getString("ProductName");
			String image = rs.getString("Image");
			String factoryName = rs.getString("Name");
			String targetName = rs.getString("TargetName");
			double discount = rs.getDouble("Discount_percent");
			long price = rs.getLong("Price");
			int factoryID1 = rs.getInt("FactoryID");
			int targetID1 = rs.getInt("TargetID");
			int promotionID = rs.getInt("PromotionID");
			Factory factory = new Factory(factoryID1, factoryName);
			Target target = new Target(targetID1, targetName);
			Promotion promotion = new Promotion(promotionID, discount);
			
			p.setiD(id);
			p.setDetailDesc(detailDesc);
			p.setShortDesc(shortDesc);
			p.setProductName(productName);
			p.setImage(image);
			p.setPrice(price);
			p.setFactory(factory);
			p.setTarget(target);
			p.setPromotion(promotion);
			break;
		}
		rs.close();
		
		cnn.cn.close();
		return p;
	}
	public Product getProductByID (int id) throws Exception, SQLException {
		Product p = new Product();
		GetConnection cnn = new GetConnection();
		cnn.KetNoi();
		String sql = "select * from Products where ProductID = ?";
		PreparedStatement cmd = cnn.cn.prepareStatement(sql);
		cmd.setInt(1, id);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int _id = rs.getInt("ProductID");
			String detailDesc = rs.getString("DetailDesc");
			String shortDesc = rs.getString("ShortDesc");
			String productName = rs.getString("ProductName");
			String image = rs.getString("Image");
			String factoryName = rs.getString("Name");
			String targetName = rs.getString("TargetName");
			double discount = rs.getDouble("Discount_percent");
			long price = rs.getLong("Price");
			int factoryID1 = rs.getInt("FactoryID");
			int targetID1 = rs.getInt("TargetID");
			int promotionID = rs.getInt("PromotionID");
			Factory factory = new Factory(factoryID1, factoryName);
			Target target = new Target(targetID1, targetName);
			Promotion promotion = new Promotion(promotionID, discount);
			
			p.setiD(_id);
			p.setDetailDesc(detailDesc);
			p.setShortDesc(shortDesc);
			p.setProductName(productName);
			p.setImage(image);
			p.setPrice(price);
			p.setFactory(factory);
			p.setTarget(target);
			p.setPromotion(promotion);
			break;
		}
		rs.close();
		
		cnn.cn.close();
		return p;
	}
	public ArrayList<Product> getListSimilarProducts(int id) throws Exception{
		ArrayList<Product> lst = new ArrayList<Product>();

		GetConnection cnn = new GetConnection();
		cnn.KetNoi();
		Product Product = this.getProductByID(id);
		String sql = "SELECT TOP(10) * FROM Products " +
	             "WHERE (FactoryID = ?) " +
	             "OR (TargetID = ?) " +
	             "OR ProductName LIKE N'%' + ? + '%' " +
	             "ORDER BY ProductID";
		
		PreparedStatement cmd = cnn.cn.prepareStatement(sql);
		cmd.setInt(1, Product.getFactory().getFactoryID());
		cmd.setInt(2, Product.getTarget().getTargetID());
		cmd.setString(3, Product.getProductName());
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int _id = rs.getInt("ProductID");
			String detailDesc = rs.getString("DetailDesc");
			String shortDesc = rs.getString("ShortDesc");
			String productName = rs.getString("ProductName");
			String image = rs.getString("Image");
			String factoryName = rs.getString("Name");
			String targetName = rs.getString("TargetName");
			double discount = rs.getDouble("Discount_percent");
			long price = rs.getLong("Price");
			int factoryID1 = rs.getInt("FactoryID");
			int targetID1 = rs.getInt("TargetID");
			int promotionID = rs.getInt("PromotionID");
			Factory factory = new Factory(factoryID1, factoryName);
			Target target = new Target(targetID1, targetName);
			Promotion promotion = new Promotion(promotionID, discount);
			Product product = new Product(_id, detailDesc, shortDesc, productName, image, price, factory, target, promotion);
			lst.add(product);
		}
		rs.close();
		cnn.cn.close();
		
		return lst;
	}
	public int insertProduct(Product product) throws ClassNotFoundException, SQLException {
	    int id = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "INSERT INTO Product (DetailDesc, ShortDesc, ProductName, Image, Price, FactoryID, TargetID, PromotionID) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?); "
	               + "SELECT SCOPE_IDENTITY();";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set các tham số
	    cmd.setString(1, product.getDetailDesc());
	    cmd.setString(2, product.getShortDesc());
	    cmd.setString(3, product.getProductName());
	    cmd.setString(4, product.getImage());
	    cmd.setLong(5, product.getPrice());
	    cmd.setInt(6, product.getFactory().getFactoryID());
	    cmd.setInt(7, product.getTarget().getTargetID());
	    cmd.setInt(8, product.getPromotion().getPromotionID());

	    boolean hasResultSet = cmd.execute();

	    if (hasResultSet) {
	        ResultSet rs = cmd.getResultSet();
	        if (rs.next()) {
	            id = rs.getInt(1); // Trả về ProductID mới
	        }
	    }
	    kn.cn.close();
	    return id;
	}
	public int updateProduct(Product product) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "UPDATE Product SET DetailDesc = ?, ShortDesc = ?, ProductName = ?, Image = ?, Price = ?, "
	               + "FactoryID = ?, TargetID = ?, PromotionID = ? WHERE ProductID = ?";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set các tham số
	    cmd.setString(1, product.getDetailDesc());
	    cmd.setString(2, product.getShortDesc());
	    cmd.setString(3, product.getProductName());
	    cmd.setString(4, product.getImage());
	    cmd.setLong(5, product.getPrice());
	    cmd.setInt(6, product.getFactory().getFactoryID());
	    cmd.setInt(7, product.getTarget().getTargetID());
	    cmd.setInt(8, product.getPromotion().getPromotionID());
	    cmd.setInt(9, product.getiD());

	    affectedRows = cmd.executeUpdate(); // Trả về số dòng bị ảnh hưởng
	    kn.cn.close();
	    return affectedRows;
	}
	public int deleteProduct(int productID) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "DELETE FROM Product WHERE ProductID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setInt(1, productID);

	    affectedRows = cmd.executeUpdate(); // Trả về số dòng bị xóa
	    kn.cn.close();
	    return affectedRows;
	}


}
