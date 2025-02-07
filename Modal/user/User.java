package user;

import role.Role;

public class User {
    private int userID;
    private String userName;
    private String fullName;
    private String phone;
    private String address;
    private String password;
    private Role role;
    private String avatar;

    public User() {
    }

    public User(int userID, String userName, String fullName, String phone, String address, String password, Role role, String avatar) {
        this.userID = userID;
        this.userName = userName;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.role = role;
        this.avatar = avatar;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", fullName=" + fullName + ", phone=" + phone
				+ ", address=" + address + ", password=" + password + ", role=" + role + ", avatar=" + avatar + "]";
	}

    
}

