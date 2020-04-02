
public class HashObject {

	// Enum the Statuses
	enum Status
	{
		OPEN, DELETED, IN_USE
	}


	// Instance Variables
	private Object object;
	private int duplicateCount;
	private int probeCount;
	private Enum status;
	private int key;

	// Constructor
	public HashObject(Object object, int key) {
		this.object = object;
		status = Status.OPEN;
		duplicateCount = 0;
		probeCount = 0;
		this.key = key;

	}

	/* Public Methods */

	public void changeStatus(Status status) {
		this.status = status;
	}

	public String getStatus() {
		return status.toString();
	}

	public int getProbeCount() {
		return probeCount;
	}

	public int getDuplicateCount() {
		return duplicateCount;
	}

	public Object getObject() {
		return object;
	}

	public void increaseProbeCount() {
		probeCount++;
	}

	public void increaseDuplicateCount() {
		duplicateCount++;
	}

	public int getKey() {
		return key;
	}

	public boolean equals(HashObject object) {
		if (this.getObject().equals(object.getObject()) == true) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		if (object != null) {
			return (object + " " + this.getDuplicateCount()   + " " +  this.getProbeCount());
		}
		return "";
	}


}
