package promotion;

public class Promotion {
	private int promotionID;
    private double discountPercent;

    // Constructor
    public Promotion(int promotionID, double discountPercent) {
        this.promotionID = promotionID;
        this.discountPercent = discountPercent;
    }
    
    public Promotion() {
		super();
	}

	// Getter và Setter
    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

	@Override
	public String toString() {
		return "Promotion [promotionID=" + promotionID + ", discountPercent=" + discountPercent + "]";
	}
    
}
