package factory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;

public class FactoryDAO {
	public int Count() throws Exception{
		int total = 0;
		GetConnection kn = new GetConnection();
		kn.KetNoi();
		String sql = "SELECT COUNT(*) AS total FROM Factory                  \r\n";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			 total = rs.getInt(1);
		}
		rs.close();
		kn.cn.close();
		return total;
	}
	public List<Factory> getListFactory(int index) throws Exception, SQLException{
		GetConnection cnn = new GetConnection();
		cnn.KetNoi();
		List<Factory> lstFactory = new ArrayList<Factory>();
		String sql = "select * from Factory "
				+ "ORDER BY FactoryID \r\n"
				+ "OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY;";
		PreparedStatement cmd = cnn.cn.prepareStatement(sql);
		cmd.setInt(1, (index-1)*10);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			
			lstFactory.add(new Factory(id, name));
		}
		rs.close();cnn.cn.close();
		return lstFactory;
	}
	public int insertFactory(Factory factory) throws ClassNotFoundException, SQLException {
	    int id = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "IF EXISTS (SELECT * FROM Factory WHERE Name = ?) "
	               + "SELECT -1 "
	               + "ELSE "
	               + "BEGIN "
	               + "    INSERT INTO Factory (Name) VALUES (?) "
	               + "    SELECT SCOPE_IDENTITY(); "
	               + "END";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setString(1, factory.getFactoryName());
	    cmd.setString(2, factory.getFactoryName());

	    boolean hasResultSet = cmd.execute();

	    if (hasResultSet) {
	        ResultSet rs = cmd.getResultSet();
	        if (rs.next()) {
	            id = rs.getInt(1); // Trả về ID mới hoặc -1 nếu đã tồn tại
	        }
	    }
	    kn.cn.close();
	    return id;
	}
	public int updateFactory(Factory factory) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "UPDATE Factory SET Name = ? WHERE FactoryID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setString(1, factory.getFactoryName());
	    cmd.setInt(2, factory.getFactoryID());

	    affectedRows = cmd.executeUpdate(); // Trả về số dòng bị ảnh hưởng
	    kn.cn.close();
	    return affectedRows;
	}
	public int deleteFactory(int factoryID) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "DELETE FROM Factory WHERE FactoryID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setInt(1, factoryID);

	    affectedRows = cmd.executeUpdate(); // Trả về số dòng bị xóa
	    kn.cn.close();
	    return affectedRows;
	}
	public Factory getFactoryById(int factoryId) throws ClassNotFoundException, SQLException {
	    Factory factory = null;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "SELECT FactoryID, Name FROM Factory WHERE FactoryID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setInt(1, factoryId);

	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        int id = rs.getInt("FactoryID");
	        String name = rs.getString("Name");
	        factory = new Factory(id, name);
	    }

	    kn.cn.close();
	    return factory;
	}




}
