package target;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;
import factory.Factory;

public class TargetDAO {
	public List<Target> getListTarget() throws Exception, SQLException{
		GetConnection cnn = new GetConnection();
		cnn.KetNoi();
		List<Target> lstTarget = new ArrayList<Target>();
		String sql = "select * from Target";
		PreparedStatement cmd = cnn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			
			lstTarget.add(new Target(id, name));
		}
		rs.close();cnn.cn.close();
		return lstTarget;
	}
	public int insertTarget(Target target) throws ClassNotFoundException, SQLException {
	    int id = 0; // ID được trả về sau khi thêm
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "INSERT INTO Target (TargetName) VALUES (?) SELECT SCOPE_IDENTITY()";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Gán tham số
	    cmd.setString(1, target.getTargetName());

	    // Thực thi lệnh
	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        id = rs.getInt(1); // Lấy giá trị ID tự tăng
	    }

	    kn.cn.close();
	    return id;
	}
	public int updateTarget(Target target) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0; // Số dòng bị ảnh hưởng
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "UPDATE Target SET TargetName = ? WHERE TargetID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Gán tham số
	    cmd.setString(1, target.getTargetName());
	    cmd.setInt(2, target.getTargetID());

	    // Thực thi lệnh
	    affectedRows = cmd.executeUpdate();

	    kn.cn.close();
	    return affectedRows;
	}
	public int deleteTarget(int targetID) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0; // Số dòng bị xóa
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "DELETE FROM Target WHERE TargetID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Gán tham số
	    cmd.setInt(1, targetID);

	    // Thực thi lệnh
	    affectedRows = cmd.executeUpdate();

	    kn.cn.close();
	    return affectedRows;
	}
	public Target getTargetById(int targetId) throws ClassNotFoundException, SQLException {
	    Target target = null;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "SELECT TargetID, TargetName FROM Target WHERE TargetID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setInt(1, targetId);

	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        int id = rs.getInt("TargetID");
	        String name = rs.getString("TargetName");
	        target = new Target(id, name);
	    }

	    kn.cn.close();
	    return target;
	}

}
