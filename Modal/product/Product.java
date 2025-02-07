package product;

import java.lang.annotation.Target;
import java.text.NumberFormat;
import java.util.Locale;

import factory.Factory;
import promotion.Promotion;

public class Product {
	private int iD;
	private String detailDesc;
	private String shortDesc;
	private String productName;
	private String image;
	private long price;
	private Factory factory;
	private target.Target target;
	private Promotion promotion;
	public Product() {
		super();
	}
	public Product(int iD, String detailDesc, String shortDesc, String productName, String image, long price, Factory factory,
			target.Target target, Promotion promotion) {
		super();
		this.iD = iD;
		this.detailDesc = detailDesc;
		this.shortDesc = shortDesc;
		this.productName = productName;
		this.image = image;
		this.price = price;
		this.factory = factory;
		this.target = target;
		this.promotion = promotion;
	}
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
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
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	public target.Target getTarget() {
		return target;
	}
	public void setTarget(target.Target target) {
		this.target = target;
	}
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	 public double getDiscountedPrice() {
	        if (promotion != null) {
	            return price * (1-promotion.getDiscountPercent());
	        }
	        return price; // Không có khuyến mãi thì giá không đổi
	    }

	    // Format giá thành chuỗi với 2 chữ số sau dấu phẩy
	 public String formatPrice(double price) {
		    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.ITALY);
		    return formatter.format(price);
		}
		@Override
		public String toString() {
			return "Product [iD=" + iD + ", detailDesc=" + detailDesc + ", shortDesc=" + shortDesc + ", productName="
					+ productName + ", image=" + image + ", price=" + price + ", factory=" + factory + ", target="
					+ target + ", promotion=" + promotion + "]";
		}
	
	
	
}
