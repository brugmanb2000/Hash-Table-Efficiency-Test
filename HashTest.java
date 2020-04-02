import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class HashTest {

	public static void main(String[] args) throws FileNotFoundException {


		// Instance Variables
		int tableSize = 0;
		HashTable doubleHashTable;
		HashTable linearTable;


		PrimeChecker pc = new PrimeChecker();
		
			for (int x = 95500; x <= 96000; x++) {
				if (pc.checkPrime(x) == true && pc.checkPrime(x+2) == true) {
					tableSize = x+2;
					break;
				}
			}

			// Parse Args
			int dataType = Integer.parseInt(args[0]);
			double loadFactor = Double.parseDouble(args[1]);
      int debugValue = 0; 
      
      try {
        if (args[2] != null) {
		  	debugValue = Integer.parseInt(args[2]);
        }
      } catch (Exception e) {
        debugValue = 0;
      }


			// Arg Exceptions



			// Ensure Load Factor is correct value
			if (loadFactor < 0 || loadFactor > 1) {
				throw new IllegalArgumentException("Load Factor must be within 0 - 1.0.");
			}

			if (dataType != 1 && dataType != 2 && dataType != 3) {
				throw new IllegalArgumentException("Data Type must be 0 for random ints, 1 for current milliseconds, 2 for word-list.");
			}

			// Debug Value must be 1 or 0
			if (debugValue != 0 && debugValue != 1) {
				throw new IllegalArgumentException("Debug value must be 0 or 1.");
			}




			// Create Tables
			doubleHashTable = new HashTable(tableSize, loadFactor, 1);	
			linearTable = new HashTable(tableSize, loadFactor, 0);



			switch(dataType) {

			// Random Ints
			case 1:


				while (true) {
					Random rand = new Random();
					int randomNum = rand.nextInt();
					linearTable.linearInsert(randomNum, randomNum);
					doubleHashTable.doubleHashInsert(randomNum, randomNum);
					if (doubleHashTable.maxReached() == true) {
						break;
					}

				}
				break;




				// Computer Milliseconds
			case 2:


				while (true) {
					long currentTime = System.currentTimeMillis();
					linearTable.linearInsert(currentTime, (int) currentTime);
					doubleHashTable.doubleHashInsert(currentTime, (int) currentTime);
					if (doubleHashTable.maxReached() == true) {
						break;
					}
				}
				break;




				// Words
			case 3: 
				String word = null;
				int hashcode;
				Scanner sc = null;
				File file = new File("/home/JHyeh/cs321/labs/lab3/files/word-list/");
				sc = new Scanner(file);
        word = sc.next();
				while (true) {
					hashcode = word.hashCode();
					linearTable.linearInsert(word, hashcode);
					doubleHashTable.doubleHashInsert(word, hashcode);
    	    if (doubleHashTable.maxReached() == true) {
						break;
				  }
        word = sc.nextLine();
			}	
}
			// Values for debug answers
			int linearDuplicates = linearTable.getDuplicates();
			int doubleHashDuplicates = doubleHashTable.getDuplicates();
			int linearElements = linearTable.getTotalElements();
			int doubleHashElements = doubleHashTable.getTotalElements();
			int linearProbeCount = linearTable.getProbes();
			int doubleHashProbeCount = doubleHashTable.getProbes();

			debugValue(debugValue, linearTable, doubleHashTable, tableSize, loadFactor, dataType, linearDuplicates, doubleHashDuplicates, linearElements, doubleHashElements, linearProbeCount, doubleHashProbeCount);
		}	




		static void debugValue(int debugValue, HashTable linearTable, HashTable doubleHashTable, int tableSize, double loadFactor, int dataType, int linearDuplicates, int doubleHashDuplicates, int linearElements, int doubleHashElements, int linearProbeCount, int doubleHashProbeCount) {

			String dataTypeString = null;
			Double linearProbeAverage =  ((double) linearProbeCount  / (double) linearElements );
			Double doubleHashProbeAverage = ((double) doubleHashProbeCount / (double) doubleHashElements);

			switch(dataType) {

			case 1: 
				dataTypeString = "Random Ints.";
				break;
			case 2: 
				dataTypeString = "Current time on PC.";
				break;

			case 3: 
				dataTypeString = "Word-List.";
			}


			switch(debugValue) {

			case 0:

				// Console Export
				System.out.println("A good table size is found: " + tableSize);
				System.out.println("Data source type: " + dataTypeString);
				System.out.println("");
				System.out.println("Linear Table:");
				System.out.println("Input: " +(linearElements+linearDuplicates) + " elements | Total Duplicate Count: " + linearDuplicates);
				System.out.println("Load factor: " + loadFactor + ", Avg. no. of probes: " + linearProbeAverage);
				System.out.println("");
				System.out.println("Double Hash Table:");
				System.out.println("Input: " +(doubleHashElements+doubleHashDuplicates) + " elements | Total Duplicate Count: " + doubleHashDuplicates);
				System.out.println("Load factor: " + loadFactor + ", Avg. no. of probes: " + doubleHashProbeAverage);

				//			System.out.println("");
				//			System.out.println(linearTable.toString());
				//			System.out.println(doubleHashTable.toString());
				//			System.out.println("Linear Individual Added Probe Count: " + linearTable.getProbes());
				//			System.out.println("Double Individual Added Probe Count: " + doubleHashTable.getProbes());
				break;


			case 1:

				// Console Export
				System.out.println("A good table size is found: " + tableSize);
				System.out.println("Data source type: " + dataTypeString);
				System.out.println("");
				System.out.println("Linear Table:");
				System.out.println("Input: " +(linearElements+linearDuplicates) + " elements  | Total Duplicate Count: " + linearDuplicates);
				System.out.println("Load factor: " + loadFactor + ", Avg. no. of probes: " + linearProbeAverage);
				System.out.println("");
				System.out.println("Double Hash Table:");
				System.out.println("Input: " +(doubleHashElements+doubleHashDuplicates) + " elements  | Total Duplicate Count: " + doubleHashDuplicates);
				System.out.println("Load factor: " + loadFactor + ", Avg. no. of probes: " + doubleHashProbeAverage);

				// Set up File Export
				File linearFile = new File("linear-dump.txt");
				File doubleHashFile = new File("double-dump.txt");
				String linearString = linearTable.toString();
				String doubleHashString = doubleHashTable.toString();	
				FileWriter fileWriter = null;
				BufferedWriter bufferedWriter = null;

				try {
					// Print Linear
					fileWriter = new FileWriter(linearFile);
					bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(linearString);
					bufferedWriter.close();
					fileWriter.close();

					// Print Double Hash
					fileWriter = new FileWriter(doubleHashFile);
					bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(doubleHashString);
					bufferedWriter.close();
					fileWriter.close();
                             
				}  catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		static int modMain(double hashcode, double number) {
			int a = (int) (hashcode % number);
			if (a < 0) {
				return (int) (a + number);
			} else {
				return a;
			}
		}

	}