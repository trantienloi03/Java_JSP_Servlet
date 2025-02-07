package orderView;

public class OrderDetailView {
	    private int orderDetailID;
	    private int orderID;
	    private int productID;
	    private int quantity;
	    private long price;
	    private String productName;
	    private String image;
	    private long total;
	    private String fullName;
	    private String phone;
	    private String address;
	    private int status;

	    // Constructor
	    public OrderDetailView(int orderDetailID, int orderID, int productID, int quantity, long price, String productName, String image, long total, String fullName, String phone, String address, int status) {
	        this.orderDetailID = orderDetailID;
	        this.orderID = orderID;
	        this.productID = productID;
	        this.quantity = quantity;
	        this.price = price;
	        this.productName = productName;
	        this.image = image;
	        this.total = total;
	        this.fullName = fullName;
	        this.phone = phone;
	        this.address = address;
	        this.status = status;
	    }

	    // Getters and Setters
	    public int getOrderDetailID() {
	        return orderDetailID;
	    }

	    public void setOrderDetailID(int orderDetailID) {
	        this.orderDetailID = orderDetailID;
	    }

	    public int getOrderID() {
	        return orderID;
	    }

	    public void setOrderID(int orderID) {
	        this.orderID = orderID;
	    }

	    public int getProductID() {
	        return productID;
	    }

	    public void setProductID(int productID) {
	        this.productID = productID;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public long getPrice() {
	        return price;
	    }

	    public void setPrice(long price) {
	        this.price = price;
	    }

	    public String getProductName() {
	        return productName;
	    }

	    public void setProductName(String productName) {
	        this.productName = productName;
	    }

	    public String getImage() {
	        return image;
	    }

	    public void setImage(String image) {
	        this.image = image;
	    }

	    public long getTotal() {
	        return total;
	    }

	    public void setTotal(long total) {
	        this.total = total;
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
	    

	    public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return "OrderDetailView [orderDetailID=" + orderDetailID + ", orderID=" + orderID + ", productID="
					+ productID + ", quantity=" + quantity + ", price=" + price + ", productName=" + productName
					+ ", image=" + image + ", total=" + total + ", fullName=" + fullName + ", phone=" + phone
					+ ", address=" + address + ", status=" + status + "]";
		}

		
	}
