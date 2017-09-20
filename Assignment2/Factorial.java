/**
* This class calculates the factorial of the numbers 0 
* and 5.
*
* @author Patrick Roeber
*/
public class Factorial {
  
  /**
  * Calls calculate twice, once with 0 and the second with
  * 5, then prints out either Test Passed or Test Failed
  * depending on whether or not the calculation was preformed
  * correctly.
  *
  * @param args unused in this function
  */
  public static void main(String[] args) {
    long calc = calculate(0);
    System.out.print("Factorial.calculate(0) returned " + calc + ". ");
    if(calc == 1) {
      System.out.println("Test Passed!");
    }
    else {
      System.out.println("Test Failed!");
    }

    calc = calculate(5);
    System.out.print("Factorial.calculate(5) returned " + calc + ". ");
    if(calc == 120) {
      System.out.println("Test Passed!");
    }
    else {
      System.out.println("Test Failed!");
    }
  }

  /**
  * Recursively finds the factorial of n.
  *
  * @param n The number of which the factorial will be
  *          taken of
  */
  public static long calculate(long n) {
    if(n < 0 || n > 20) {
      System.out.println("n must be a number between 0 and 19.");
      return -1;
    }
    else if(n == 0) {
      return 1;
    }
    else {
      return n * calculate(n - 1);
    }
  }
}
