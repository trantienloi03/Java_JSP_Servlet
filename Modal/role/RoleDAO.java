package role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;
import user.User;

public class RoleDAO {
	public List<Role> getRole() throws ClassNotFoundException, SQLException {
	    List<Role> roleList = new ArrayList();
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "SELECT TOP (1000) [RoleID]\r\n"
	    		+ "      ,[Name]\r\n"
	    		+ "  FROM [Java_TranTienLoi].[dbo].[Role]";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    ResultSet rs = cmd.executeQuery();

	    // Duyệt qua ResultSet và thêm User vào danh sách
	    while (rs.next()) {
	        int roleID = rs.getInt("UserID");
	        String roleName = rs.getString("UserName");
	        Role role = new Role(roleID, roleName);
	        roleList.add(role);
	    }
	    rs.close();
	    kn.cn.close();
	    return roleList;
	}
	public Role getRoleById(int roleID) throws ClassNotFoundException, SQLException {
	    Role role = null;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    // Câu lệnh SQL để lấy User theo ID
	    String sql = "SELECT TOP (1000) [RoleID]\r\n"
	    		+ "      ,[Name]\r\n"
	    		+ "  FROM [Java_TranTienLoi].[dbo].[Role]\r\n"
	    		+ "  where RoleID = ?";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setInt(1, roleID);
	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        int _roleID = rs.getInt("RoleID");
	        String roleName = rs.getString("Name");
	         role = new Role(_roleID, roleName);
	    }
	    rs.close();
	    kn.cn.close();
	    return role;
	}

}
