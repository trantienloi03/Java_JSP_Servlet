package product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBO {
	ProductDAO pDAO = new ProductDAO();
	public int Count(int factoryID ,int targetID, String ProductName ) throws Exception{
		return pDAO.Count(factoryID, targetID, ProductName);
	}
	public ArrayList<Product> getListProducts(int index, int factoryID ,int targetID,String ProductName) throws Exception{
		return pDAO.getListProducts(index, factoryID, targetID, ProductName);
	}
	public ArrayList<Product> getListSimilarProducts(int id) throws Exception{
		return pDAO.getListSimilarProducts(id);
	}
	public Product getProductByID (int id) throws SQLException, Exception {
		return pDAO.getProductByID(id);
	}
	public int insertProduct(Product product) throws ClassNotFoundException, SQLException {
		return pDAO.insertProduct(product);
	}
	public int updateProduct(Product product) throws ClassNotFoundException, SQLException {
		return pDAO.updateProduct(product);
	}
	public int deleteProduct(int productID) throws ClassNotFoundException, SQLException {
		return pDAO.deleteProduct(productID);
	}
}
