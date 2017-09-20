/**
* This program calculates the Fibonacci number as given
* by the user via the command line.
*
* @author Patrick Roeber
*/
public class Fib {
  
  /**
  * This function calculates the Fibonacci number as given
  * by the user via the command line.
  *
  * @param args A String array, the first element of this array
  *             will be used to calculate the Fibonacci number
  */
  public static void main(String[] args) {
    int nth = 0;
    int fib_number = 1;

    int fib1 = 1;
    int fib2 = 1;

    if(args.length < 1) {
      System.out.println("usage: Fib <number (1 - 48)>");
    }
    else {
      nth = Integer.parseInt(args[0]);

      if(nth < 1 || nth > 48) {
        System.out.println("usage: Fib <number (1 - 48)>");
      }
      else {
        for(int i=3; i <= nth; i++) {
          fib_number = fib1 + fib2;
          fib2 = fib1;
          fib1 = fib_number;
        }
        System.out.println(fib_number);
      }
    }
  }
}
