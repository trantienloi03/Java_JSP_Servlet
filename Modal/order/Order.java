package order;

import java.util.Date;

public class Order {
	private int orderID;
	private int userID;
	private int statusID;
	private long totalPrice;
	private Date dateTime;
	private int sum;
	private String userName;
	private String address;
	private String phone;
	
	public Order() {
		super();
	}

	public Order(int orderID, int userID, int statusID, long totalPrice, Date dateTime, int sum, String userName,
			String address, String phone) {
		super();
		this.orderID = orderID;
		this.userID = userID;
		this.statusID = statusID;
		this.totalPrice = totalPrice;
		this.dateTime = dateTime;
		this.sum = sum;
		this.userName = userName;
		this.address = address;
		this.phone = phone;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getDescriptionStatus(int statusID) {
		switch (statusID) {
		case 1: {
			return "Chờ xử lý";
		}
		case 2: {
			return "Đang giao";
		}
		case 3: {
			return "Bị từ chối";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + statusID);
		}
	}
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", userID=" + userID + ", statusID=" + statusID + ", totalPrice="
				+ totalPrice + ", dateTime=" + dateTime + ", sum=" + sum + ", userName=" + userName + ", address="
				+ address + ", phone=" + phone + "]";
	}
	
	
	
	
}
