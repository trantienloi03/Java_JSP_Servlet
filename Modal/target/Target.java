package target;

public class Target {
	private int targetID;
    private String targetName;

    // Constructor
    public Target(int targetID, String targetName) {
        this.targetID = targetID;
        this.targetName = targetName;
    }

    // Getter và Setter
    public int getTargetID() {
        return targetID;
    }

    public void setTargetID(int targetID) {
        this.targetID = targetID;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

	@Override
	public String toString() {
		return "Target [targetID=" + targetID + ", targetName=" + targetName + "]";
	}
    
}
