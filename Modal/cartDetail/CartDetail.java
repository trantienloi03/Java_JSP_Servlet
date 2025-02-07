package cartDetail;

public class CartDetail {
	private int cartDetailID;
	private long price;
	private int quantity;
	private int cartID;
	private int productID;
	public CartDetail() {
		super();
	}
	public CartDetail(int cartDetailID, long price, int quantity, int cartID, int productID) {
		super();
		this.cartDetailID = cartDetailID;
		this.price = price;
		this.quantity = quantity;
		this.cartID = cartID;
		this.productID = productID;
	}
	public int getCartDetailID() {
		return cartDetailID;
	}
	public void setCartDetailID(int cartDetailID) {
		this.cartDetailID = cartDetailID;
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
	@Override
	public String toString() {
		return "CartDetail [cartDetailID=" + cartDetailID + ", price=" + price + ", quantity=" + quantity + ", cartID="
				+ cartID + ", productID=" + productID + "]";
	}
	
	

}
