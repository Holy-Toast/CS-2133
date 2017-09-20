/**
* This program finds the real root between 3 and 4 of
* sin(x)
*
* @author Patrick Roeber
*/
public class FunctionTest {
  
  /**
  * Calculates the root between 3 and 4 of sin(x) with a
  * certainty of 0.00000001, then prints out the answer.
  *
  * @param args Input from the command line. Not used within this program.
  */
  public static void main(String[] args) {
    double answer = 0.0;
    answer = findRoot(3, 4, 0.00000001);
    System.out.println("The answer is: " + answer);
  }

  /**
  * Calculates the root between a and b of sin(x) with a
  * certainty of epsilon.
  *
  * @param a       The lower bound 
  * @param b       The upper bound
  * @param epsilon The certainty of the calculation
  * @return        The real root found between a and b
  */
  public static double findRoot(double a, double b, double epsilon) {
    double x = (a + b) / 2;
    double root = 0;
    double n = epsilon;
    n = Math.pow(n, -1);

    if (Math.abs(a - x) < epsilon) {
      return x;
    }
    else {
      if ((Math.sin(a) > 0 && Math.sin(x) > 0) || (Math.sin(a) < 0 && Math.sin(x) < 0)) {
        root = findRoot(x, b, epsilon);
      }
      else {
        root = findRoot(a, x, epsilon);
      }
    }
    root = Math.floor(root * n) / n;
    return root;
  }
}
