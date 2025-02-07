package user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.GetConnection;
import role.Role;

public class UserBO {
	
	private UserDAO uDAO = new UserDAO();
	public User checkLogin(String username, String pass) throws SQLException, Exception {
		return uDAO.checkLogin(username, pass);
	}
	public int InsertUser(User user) throws ClassNotFoundException, SQLException {
		return uDAO.InsertUser(user);
	}
	public int updateUser(User user) throws ClassNotFoundException, SQLException {
		return uDAO.updateUser(user);
	}
	public int deleteUser(int userID) throws ClassNotFoundException, SQLException {
		return uDAO.deleteUser(userID);
	}
	public User getUserById(int userId) throws ClassNotFoundException, SQLException {
	    return uDAO.getUserById(userId);
	}
	public boolean changePassword(int userID, String oldPassword, String newPassword) throws ClassNotFoundException, SQLException {
	    return uDAO.changePassword(userID, oldPassword, newPassword);
	}
	public ArrayList<User> getListUser(int index, int roleID) throws Exception{
		return uDAO.getListUser(index, roleID);
	}
	public int Count(int roleID) throws Exception{
		return uDAO.Count(roleID);
	}
}
