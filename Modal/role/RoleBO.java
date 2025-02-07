package role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;

public class RoleBO {
	private RoleDAO rDAO = new RoleDAO();
	public List<Role> getRole() throws ClassNotFoundException, SQLException {
	    return rDAO.getRole();
	}
	public Role getRoleById(int roleID) throws ClassNotFoundException, SQLException {
	   return rDAO.getRoleById(roleID);
	}

}
