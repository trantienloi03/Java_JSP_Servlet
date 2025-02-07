package factory;

public class Factory {
	 	private int factoryID;
	    private String factoryName;

	    
	    public Factory() {
			super();
		}

		// Constructor
	    public Factory(int factoryID, String factoryName) {
	        this.factoryID = factoryID;
	        this.factoryName = factoryName;
	    }

	    // Getter và Setter
	    public int getFactoryID() {
	        return factoryID;
	    }

	    public void setFactoryID(int factoryID) {
	        this.factoryID = factoryID;
	    }

	    public String getFactoryName() {
	        return factoryName;
	    }

	    public void setFactoryName(String factoryName) {
	        this.factoryName = factoryName;
	    }

		@Override
		public String toString() {
			return "Factory [factoryID=" + factoryID + ", factoryName=" + factoryName + "]";
		}
	    
}
