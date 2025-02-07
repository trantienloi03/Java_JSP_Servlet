package cart;

public class Cart {
	private int cartID;
	private int sum;
	private int userID;
	public Cart() {
		super();
	}
	public Cart(int cartID, int sum, int userID) {
		super();
		this.cartID = cartID;
		this.sum = sum;
		this.userID = userID;
	}
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "Cart [cartID=" + cartID + ", sum=" + sum + ", userID=" + userID + "]";
	}
	
	

}
