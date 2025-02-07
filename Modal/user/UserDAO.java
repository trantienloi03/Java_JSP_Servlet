package user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;
import factory.Factory;
import product.Product;
import promotion.Promotion;
import role.Role;
import target.Target;


public class UserDAO {
	
	public User checkLogin(String username, String pass) throws Exception, SQLException {
		User user = null;
		GetConnection cnn = new GetConnection();
		cnn.KetNoi();
		String sql = "select * from CheckLogin where UserName = ? and Password = ?";
		PreparedStatement cmd = cnn.cn.prepareStatement(sql);
		cmd.setString(1, username);
		cmd.setString(2, pass);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("UserID");
			String fullname = rs.getString("FullName");
			String _username = rs.getString("UserName");
			String avatar = rs.getString("Avatar");
			String _pass = rs.getString("Password");
			String phone = rs.getString("Phone");
			String address = rs.getString("Address");
			int roleID = rs.getInt("RoleID");
			String roleName = rs.getString("Name");
			
			Role role = new Role(roleID, roleName);
			user = new User(id, _username, fullname, phone, address, _pass, role, avatar);
			
		}
		return user;
	}
	public int InsertUser(User user) throws ClassNotFoundException, SQLException {
		int id = 0;
		GetConnection kn = new GetConnection();
		kn.KetNoi();

		String sql = "if exists(select * from [User] where UserName = ? ) "
		           + "select -1 "
		           + "else "
		           + "begin "
		           + "    insert into [User] (UserName, FullName, Phone, Address, Password, RoleID, Avatar) "
		           + "    values(?,?,?,?,?,?,?); "
		           + "    select scope_identity(); "
		           + "end";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);

		// Set các tham số
		cmd.setString(1, user.getUserName());
		cmd.setString(2, user.getUserName());
		cmd.setString(3, user.getFullName());
		cmd.setString(4, user.getPhone());
		cmd.setString(5, user.getAddress());
		cmd.setString(6, user.getPassword());
		cmd.setInt(7, user.getRole().getRoleID());
		cmd.setString(8, user.getAvatar());

		// Sử dụng execute để xử lý cả SELECT và UPDATE
		boolean hasResultSet = cmd.execute();

		if (hasResultSet) {
		    // Lấy ResultSet khi có kết quả trả về
		    ResultSet rs = cmd.getResultSet();
		    if (rs.next()) {
		        id = rs.getInt(1); // Lấy giá trị scope_identity() hoặc -1
		    }
		} else {
		    // Trường hợp không có ResultSet
		    int affectedRows = cmd.getUpdateCount();
		    System.out.println("Số dòng bị ảnh hưởng: " + affectedRows);
		}

		kn.cn.close();
		return id;


	}
	public int updateUser(User user) throws ClassNotFoundException, SQLException {
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "update [User] "
	               + "set UserName = ?, FullName = ?, Phone = ?, Address = ?, Password = ?, RoleID = ?, Avatar = ? "
	               + "where UserID = ?; ";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set các tham số
	    cmd.setString(1, user.getUserName()); // Kiểm tra UserID
	    cmd.setString(2, user.getFullName());
	    cmd.setString(3, user.getPhone());
	    cmd.setString(4, user.getAddress());
	    cmd.setString(5, user.getPassword());
	    cmd.setInt(6, user.getRole().getRoleID());
	    cmd.setString(7, user.getAvatar());
	    cmd.setInt(8, user.getUserID()); // Cập nhật dựa trên UserID

	    int result = -1;
	    boolean hasResultSet = cmd.execute();

	    if (hasResultSet) {
	        ResultSet rs = cmd.getResultSet();
	        if (rs.next()) {
	            result = rs.getInt(1); // Lấy giá trị trả về: -1 hoặc 1
	        }
	    }

	    kn.cn.close();
	    return result; // Trả về -1 nếu không tồn tại, 1 nếu cập nhật thành công
	}
	public int deleteUser(int userID) throws ClassNotFoundException, SQLException {
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "if not exists(select * from User where UserID = ?) "
	               + "select -1 " // Trả về -1 nếu User không tồn tại
	               + "else "
	               + "begin "
	               + "    delete from User where UserID = ?; "
	               + "    select 1; " // Trả về 1 nếu xóa thành công
	               + "end";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số
	    cmd.setInt(1, userID); // Kiểm tra UserID
	    cmd.setInt(2, userID); // Xóa UserID

	    int result = -1;
	    boolean hasResultSet = cmd.execute();

	    if (hasResultSet) {
	        ResultSet rs = cmd.getResultSet();
	        if (rs.next()) {
	            result = rs.getInt(1); // Lấy giá trị trả về: -1 hoặc 1
	        }
	    }

	    kn.cn.close();
	    return result; // Trả về -1 nếu không tồn tại, 1 nếu xóa thành công
	}
	public boolean changePassword(int userID, String oldPassword, String newPassword) throws ClassNotFoundException, SQLException {
		boolean isPasswordChanged = false;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    
	    String sql = "UPDATE [User] SET Password = ? WHERE UserID = ? AND Password = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    
	    cmd.setString(1, newPassword); 
	    cmd.setInt(2, userID);  
	    cmd.setString(3, oldPassword); 
	    int affectedRows = cmd.executeUpdate();
	    isPasswordChanged = (affectedRows > 0);
	    cmd.close();
	    kn.cn.close();

	    return isPasswordChanged;
	}
	public List<User> getAllUsers() throws ClassNotFoundException, SQLException {
	    List<User> userList = new ArrayList();
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    // Câu lệnh SQL để lấy tất cả User
	    String sql = "SELECT u.UserID, u.UserName, u.FullName, u.Phone, u.Address, u.Password, u.RoleID, r.Name AS RoleName, u.Avatar " +
	                 "FROM [User] u " +
	                 "JOIN Role r ON u.RoleID = r.RoleID";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Thực thi câu lệnh SQL
	    ResultSet rs = cmd.executeQuery();

	    // Duyệt qua ResultSet và thêm User vào danh sách
	    while (rs.next()) {
	        int id = rs.getInt("UserID");
	        String username = rs.getString("UserName");
	        String fullName = rs.getString("FullName");
	        String phone = rs.getString("Phone");
	        String address = rs.getString("Address");
	        String password = rs.getString("Password");
	        int roleID = rs.getInt("RoleID");
	        String roleName = rs.getString("RoleName");
	        String avatar = rs.getString("Avatar");

	        // Tạo đối tượng Role
	        Role role = new Role(roleID, roleName);

	        // Tạo đối tượng User
	        User user = new User(id, username, fullName, phone, address, password, role, avatar);

	        // Thêm vào danh sách
	        userList.add(user);
	    }
	    rs.close();
	    kn.cn.close();
	    return userList;
	}
	public User getUserById(int userId) throws ClassNotFoundException, SQLException {
	    User user = null;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    // Câu lệnh SQL để lấy User theo ID
	    String sql = "SELECT u.UserID, u.UserName, u.FullName, u.Phone, u.Address, u.Password, u.RoleID, r.Name AS RoleName, u.Avatar " +
	                 "FROM [User] u " +
	                 "JOIN Role r ON u.RoleID = r.RoleID " +
	                 "WHERE u.UserID = ?";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Set tham số UserID vào câu lệnh SQL
	    cmd.setInt(1, userId);

	    // Thực thi câu lệnh SQL
	    ResultSet rs = cmd.executeQuery();

	    // Nếu có kết quả, tạo đối tượng User
	    if (rs.next()) {
	        int id = rs.getInt("UserID");
	        String username = rs.getString("UserName");
	        String fullName = rs.getString("FullName");
	        String phone = rs.getString("Phone");
	        String address = rs.getString("Address");
	        String password = rs.getString("Password");
	        int roleID = rs.getInt("RoleID");
	        String roleName = rs.getString("RoleName");
	        String avatar = rs.getString("Avatar");

	        // Tạo đối tượng Role
	        Role role = new Role(roleID, roleName);

	        // Tạo đối tượng User
	        user = new User(id, username, fullName, phone, address, password, role, avatar);
	    }
	    rs.close();
	    // Đóng kết nối
	    kn.cn.close();
	    return user;
	}
	public static int Count(int roleID) throws Exception{
		int total = 0;
		GetConnection cnn = new GetConnection();
		cnn.KetNoi();
		String sql = "SELECT COUNT(*) AS TongSo FROM [User] Where RoleID = ?                 \r\n";
		PreparedStatement cmd = cnn.cn.prepareStatement(sql);
		cmd.setInt(1, roleID);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			 total = rs.getInt(1);
		}
		rs.close();
		cnn.cn.close();
		return total;
	}
	public ArrayList<User> getListUser(int index, int _roleID) throws Exception{
		ArrayList<User> lst = new ArrayList<User>();

		GetConnection cnn = new GetConnection();
		cnn.KetNoi();
		String sql = "SELECT u.UserID, u.UserName, u.FullName, u.Phone, u.Address, u.Password, u.RoleID, r.Name AS RoleName, u.Avatar \r\n"
				+ "	  FROM [User] u \r\n"
				+ "	  JOIN Role r ON u.RoleID = r.RoleID \r\n"
				+ "	  WHERE u.RoleID = ?"
				+ " ORDER BY u.UserID \r\n "
				+ " OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY;";
		
		PreparedStatement cmd = cnn.cn.prepareStatement(sql);
		cmd.setInt(1, _roleID);
		cmd.setInt(2, (index-1)*12);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("UserID");
	        String username = rs.getString("UserName");
	        String fullName = rs.getString("FullName");
	        String phone = rs.getString("Phone");
	        String address = rs.getString("Address");
	        String password = rs.getString("Password");
	        int roleID = rs.getInt("RoleID");
	        String roleName = rs.getString("RoleName");
	        String avatar = rs.getString("Avatar");

	        // Tạo đối tượng Role
	        Role role = new Role(roleID, roleName);

	        // Tạo đối tượng User
	        User user = new User(id, username, fullName, phone, address, password, role, avatar);
			lst.add(user);
		}
		rs.close();
		cnn.cn.close();
		
		return lst;
	}





}
