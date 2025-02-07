package orderDetail;

public class OrderDetail {
	private int orderDetailID;
	private long price;
	private int quantity;
	private int orderID;
	private int productID;
	public OrderDetail() {
		super();
	}
	public OrderDetail(int orderDetailID, long price, int quantity, int orderID, int productID) {
		super();
		this.orderDetailID = orderDetailID;
		this.price = price;
		this.quantity = quantity;
		this.orderID = orderID;
		this.productID = productID;
	}
	public int getOrderDetailID() {
		return orderDetailID;
	}
	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
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
	@Override
	public String toString() {
		return "OrderDetail [orderDetailID=" + orderDetailID + ", price=" + price + ", quantity=" + quantity
				+ ", orderID=" + orderID + ", productID=" + productID + "]";
	}
	
	
}
