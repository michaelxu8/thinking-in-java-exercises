/****************** Exercise 12 *****************
 * Create a class called Tank that can be filled and emptied, and has a
 * termination condition that it must be empty when the object is cleaned up. Write a
 * finalize( ) that verifies this termination condition. In main( ), test the possible scenarios
 * that can occur when your Tank is used.
 ************************************************/

package c4_initialization_and_cleanup;

public class E12_Tank {
	private static int tankCounter = 0;
	private final int tankId = tankCounter++;
	private int soldierCount = 0;
	public E12_Tank(int soldierCount) {
		System.out.println("Tunk"+tankId+" with " + soldierCount + " soldiers created");
		this.soldierCount = soldierCount;
	}
	public void insertSoldier() {
		soldierCount++;
	}
	
	public void removeSoldier() {
		if (soldierCount > 0) {
			soldierCount--;
		}
	}
	
	protected void finalize() { 
		if(soldierCount != 0)
			System.err.println("Error: " + "Tank"+tankId+ " has " + soldierCount + " soldiers inside");
		else
			System.out.println("Tank"+tankId+ " finalized");
		// Normally, you’ll also do this:
		// super.finalize();
		// Call the base-class version
	}
	
	public static void main(String[] args) throws InterruptedException {
		new E12_Tank(0); //0
		new E12_Tank(0).insertSoldier(); //1
		new E12_Tank(1); //2
		new E12_Tank(1).removeSoldier(); //3
		new E12_Tank(100); //4
		System.out.println("System.gc()");
		System.gc();
		Thread.sleep(1000);
	}
}