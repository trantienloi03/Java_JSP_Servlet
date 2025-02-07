package cartView;

public class CartView {
	private int cartID;
	private int productID;
	private int userID;
	private long price;
	private int quantity;
	private long total;
	private String productName;
	private String image;
	public CartView() {
		super();
	}
	public CartView(int cartID, int productID, int userID, long price, int quantity, long total, String productName,
			String image) {
		super();
		this.cartID = cartID;
		this.productID = productID;
		this.userID = userID;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
		this.productName = productName;
		this.image = image;
	}
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
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
	@Override
	public String toString() {
		return "CartView [cartID=" + cartID + ", productID=" + productID + ", userID=" + userID + ", price=" + price
				+ ", quantity=" + quantity + ", total=" + total + ", productName=" + productName + ", image=" + image
				+ "]";
	}
	

}
