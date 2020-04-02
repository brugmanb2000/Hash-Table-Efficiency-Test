import java.util.ArrayList;

public class HashTable {

	// Enum
	enum typeOfTable {
		LINEAR, DOUBLEHASH
	}

	// Instance Variables
	private int capacity;
	private double maxLoadFactor;
	private typeOfTable typeOfTable;
	private HashObject table[];
	private int currentKeys;
	private int primeValue;
	private int duplicateCount;
	private int probeCount;




	// Constructor
	public HashTable(int capacity, double maxLoadFactor, int tableInt) {

		// Updating Table with Args
		this.capacity = capacity;
		this.primeValue = primeValue;
		this.maxLoadFactor = maxLoadFactor;

		// Create table
		table = new HashObject[capacity];

		// Counts
		currentKeys = 0;
		duplicateCount = 0;
		probeCount = 0;

		if (tableInt == 0) {
			typeOfTable = typeOfTable.LINEAR;
		}

		else {
			typeOfTable = typeOfTable.DOUBLEHASH;
		}
	}


	/* Private Methods */


	// Used to check if a double hash method is used for a linear table
	private boolean isLinear() {
		String tableType = getType();
		if (tableType == "DOUBLEHASH") {
			System.out.println("Incorrect Usage: Ignoring Step");
			return false;
		}
		return true;
	}

	// Used to check if a linear method is used for a double hash table
	private boolean isDoubleHash() {
		String tableType = getType();
		if (tableType == "LINEAR") {
			System.out.println("Incorrect Usage: Ignoring Step");
			return false;
		}
		return true;
	}

	// Get Type of Table
	private String getType() {
		return typeOfTable.toString();
	}

	// Get Capacity
	private int getCapacity() {
		return capacity;
	}

	// Regular Mod
	private int mod(double hashcode, double capacity) {
		int a = (int) (hashcode % capacity);
		if (a < 0) {
			return (int) (a + capacity);
		} else {
			return a;
		}
	}

	// Double Hash
	private int mod2(double key, double capacity) {
		int capacitySubtract = (int) (capacity - 2);
		int a = 1 + mod(key, capacitySubtract);
		return a;
	}


	/* Public Methods */

	public int getTotalElements() {
		return currentKeys;
	}

	// Add value to the table
	public void doubleHashInsert(Object obj, int key) {
   
		HashObject object = new HashObject(obj, key);
			int index = mod(key, capacity);
			ArrayList <Integer> indexAttempts = new ArrayList<Integer>();

			// Search all indexes

			// Check if original value is null
			if (table[index] == null || table[index].getStatus() == "DELETED") {
				table[index] = object;
				currentKeys++;
				table[index].increaseProbeCount();

				// For other values		
			} else if (table[index].equals(object) == true) {
				table[index].increaseDuplicateCount();
				duplicateCount++;

			} else {
				int index2 = mod2(key, capacity);
				for (int loopValue = 0; loopValue < capacity; loopValue++) {

					int newIndex = mod(index + loopValue * index2, capacity);

					if (table[newIndex] == null || table[index].getStatus() == "DELETED") {
						table[newIndex] = object;
						indexAttempts.add(newIndex);
            currentKeys++;
						
						for (int x = 0; x < indexAttempts.size(); x++) {
							table[indexAttempts.get(x)].increaseProbeCount();
						}
						break;
					} else if (table[newIndex].equals(object) == true) {
						table[index].increaseDuplicateCount();
						duplicateCount++;
						break;
					} else {
						indexAttempts.add(newIndex);


					}

				}
			}
		}

	// Add value to the table
	public void linearInsert(Object obj, int key) {
   
		HashObject object = new HashObject(obj, key);
		if (isLinear() == true) {
			int index = mod(key, capacity);
			ArrayList <Integer> indexAttempts = new ArrayList<Integer>();

			// While loop value
			int lastValue = index - 1;

			// Search all indexes
			while (index != lastValue) {

				// Update if not a duplicate
				if (table[index] == null || table[index].getStatus() == "DELETED") {
					table[index] = object;
					indexAttempts.add(index);
          currentKeys++;
						for (int x = 0; x < indexAttempts.size(); x++) {
							table[(int) indexAttempts.get(x)].increaseProbeCount();
						}
					break;

					

					// Look for Duplicates
				} else if (table[index].equals(object)) {
					table[index].increaseDuplicateCount();
					duplicateCount++;
					break;

					// Increment
				} else {

					// Increment Index
					indexAttempts.add(index);
					index++;
					
					// If index == capacity, set to 0;
					if (index == capacity) {
						index = 0;
					}
				}
			}
		}
	}

	// To String
	public String toString() {
		StringBuilder retVal = new StringBuilder();
		for (int x = 0; x < getCapacity(); x++) {
			String addValue = "table[" + x + "]: ";
			retVal.append(addValue);
			if (table[x] != null) {
				addValue = table[x].toString();
				retVal.append(addValue);
			}
			retVal.append("\n");
		}
		return retVal.toString();

	}

	// Check if Max Load Factor reached
	public boolean maxReached() {
		if (maxLoadFactor <= (double) currentKeys / capacity) {
			return true;
		}
		return false;
	}

	public int getDuplicates() {
		int dupCount = 0;
		for (int x = 0; x < getCapacity(); x++) {
			if (table[x] != null) {
				dupCount = dupCount + table[x].getDuplicateCount();
			}
		}
		return dupCount;
	}

	public int getProbes() {
		int probeCount = 0;
		for (int x = 0; x < getCapacity(); x++) {
			if (table[x] != null) {
				probeCount = probeCount + table[x].getProbeCount();
			}
		}
		return probeCount;
	}

	private void updateProbes(int probes) {
		probeCount = probeCount + probes;
	}

	public int getTableProbes() {
		return probeCount;
	}

	public int getTableDups() {
		return duplicateCount;
	}




}

