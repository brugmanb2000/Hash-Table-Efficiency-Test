import java.math.BigInteger;
import java.util.Scanner;

public class PrimeChecker {

	// Constructor

	public PrimeChecker() {
	}

	// Methods

	// Check if original is prime
	public boolean checkPrime(long mod) {

		if (isPrime(mod - 1, mod) == true) {
			return true;
		}

		return false;
	}

	private boolean isPrime(long prime, long mod) {

		BigInteger primeNum = BigInteger.valueOf(prime); 
		BigInteger modNum = BigInteger.valueOf(mod);
		BigInteger base = new BigInteger("3");
		BigInteger base2 = new BigInteger("5");

		base = base.modPow(primeNum, modNum);
		base2 = base2.modPow(primeNum, modNum);

		int test = base.intValue();
		int test2 = base2.intValue();
		if (test == 1) {
			if (test2 == 1) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}





	//		if (mod(Math.pow(3, prime-1), mod) != 1) {
	//			return false;
	//		} else {
	//			if (mod(Math.pow(5, prime-1), mod) == 1) {
	//				return true;
	//			} else {
	//				return false;
	//			} 
	//		}
	//	}
}
