/**
* This class is a form for which three other function classes are
* modeled after.
*
* @author Patrick Roeber
*/
public abstract class Function {
  /**
  * Evaluates the function using the variable provided.
  *
  * @param x The variable to be used when evaluating
  * @return  The value of the function at the point x
  */
  public abstract double evaluate(double x);

  /**
  * Finds the real root in between two numbers of the given
  * function.
  *
  * @param a       The lower bound of the function
  * @param b       The upper bound of the function
  * @param epsilon The degree of accuracy desired
  * @return        The double representation of the real root
  *                to the accuracy specified by epsilon
  */
  public double findRoot(double a, double b, double epsilon) {
    double x = (a + b) / 2;
    double root = 0;
    double n = epsilon;
    n = Math.pow(n, -1);

    if (Math.abs(a - x) < epsilon) {
      return x;
    }
    else {
      if ((evaluate(a) > 0 && evaluate(x) > 0) || (evaluate(a) < 0 && evaluate(x) < 0)) {
        root = findRoot(x, b, epsilon);
      }
      else {
        root = findRoot(a, x, epsilon);
      }
    }
    root = Math.floor(root * n) / n;
    return root;
  }

  /**
  * Runs several tests on the various functions created.
  *
  * @param args Input from the command line. Not used within this program.
  */
  public static void main(String[] args) {
    double sineValue = 3.14159265;
    double cosineValue = 1.57079633;
    String polyStr1 = "x^2-3";
    String polyStr2 = "x^2-1x-2";
    double ev1 = 1.73205080;
    double ev2 = 1.99999999;


    SinFunc sinFunc = new SinFunc();
    double sinTest = 0.0;

    sinTest = sinFunc.findRoot(3, 4, 0.00000001);
    if(sinTest == sineValue) {
      System.out.println("Sine Test Passed!");
    }
    else {
      System.out.println("Sine Test Failed!");
      System.out.println(sinTest);
      System.out.println("Should have been: " + sineValue);
    }

    CosFunc cosFunc = new CosFunc();
    double cosTest = 0.0;

    cosTest = cosFunc.findRoot(1, 3, 0.00000001);
    if(cosTest == cosineValue) {
      System.out.println("Cosine Test Passed!");
    }
    else {
      System.out.println("Cosine Test Failed!");
      System.out.println(cosTest);
      System.out.println("Should have been: " + cosineValue);
    }

    int[] poly1Nums = {-3, 0, 1};
    PolyFunc poly1 = new PolyFunc(poly1Nums);

    double poly1Test = 0.0;

    poly1Test = poly1.findRoot(0, 100, 0.00000001);

    if(poly1.toString().equals(polyStr1)) {
      System.out.println("String Test 1 Passed!");
    }
    else {
      System.out.println("String Test 1 Failed!");
      System.out.println(poly1);
      System.out.println("Should have been: ");
      System.out.println(polyStr1);
    }

    if(poly1Test == ev1) {
      System.out.println("Evaluate Test 1 Passed!");
    }
    else {
      System.out.println("Evaluate Test 1 Failed!");
      System.out.println(poly1Test);
      System.out.println("Should have been: ");
      System.out.println(ev1);
    }

    int[] poly2Nums = {-2, -1, 1};
    PolyFunc poly2 = new PolyFunc(poly2Nums);

    double poly2Test = 0.0;

    poly2Test = poly2.findRoot(0, 100, 0.00000001);

    if(poly2.toString().equals(polyStr2)) {
      System.out.println("String Test 2 Passed!");
    }
    else {
      System.out.println("String Test 2 Failed!");
      System.out.println(poly2);
      System.out.println("Should have been: ");
      System.out.println(polyStr2);
    }

    if(poly2Test == ev2) {
      System.out.println("Evaluate Test 2 Passed!");
    }
    else {
      System.out.println("Evaluate Test 2 Failed!");
      System.out.println(poly2Test);
      System.out.println("Should have been: ");
      System.out.println(ev2);
    }

  }
}
