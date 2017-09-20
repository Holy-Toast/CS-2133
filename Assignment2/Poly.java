import java.util.*;

public class Poly {
  int length;
  int[] coeffs;

  public static void main(String[] args) {
    // Test the various functions of the poly class
    int polyDeg1 = 5;
    int polyDeg2 = 5;
    int polyDeg3 = 4;
    double polyEv1 = 2 + 3 - 8 + 4;
    double polyEv2 = (2 * Math.pow(2, 5)) + (3 * Math.pow(2, 4)) + (-8 * Math.pow(2, 2)) + 4;
    double polyEv3 = (-2 * Math.pow(3, 5)) + (-8 * Math.pow(3, 2)) + (2 * 3) - 6;
    String polyStr1 = "2x^5+3x^4-8x^2+4";
    String polyStr2 = "-2x^5-8x^2+2x-6";
    String polyStr3 = "3x^4-16x^2+2x-2";

    int[] poly1 = {4, 0, -8, 0, 3, 2};
    Poly polynomial1 = new Poly(poly1);

    if(polynomial1.degree() == polyDeg1) {
      System.out.println("Degree Test 1 Passed!");
    }
    else {
      System.out.println("Degree Test 1 Failed!");
      System.out.println(polynomial1.degree());
      System.out.println("Should have been: ");
      System.out.println(polyDeg1);
      System.out.println();
    }

    if(polynomial1.toString().equals(polyStr1)) {
      System.out.println("String Test 1 Passed!");
    }
    else {
      System.out.println("String Test 1 Failed!");
      System.out.println(polynomial1);
      System.out.println("Should have been: ");
      System.out.println(polyStr1);
      System.out.println();
    }

    if(polynomial1.evaluate(1) == polyEv1) {
      System.out.println("Evaluate Test 1 Passed!");
    }
    else {
      System.out.println("Evaluate Test 1 Failed!");
      System.out.println(polynomial1.evaluate(1));
      System.out.println("Should have been: ");
      System.out.println(polyEv1);
      System.out.println();
    }

    if(polynomial1.evaluate(2) == polyEv2) {
      System.out.println("Evaluate Test 1.2 Passed!");
    }
    else {
      System.out.println("Evaluate Test 1.2 Failed!");
      System.out.println(polynomial1.evaluate(2));
      System.out.println("Should have been: ");
      System.out.println(polyEv2);
      System.out.println();
    }


    int[] poly2 = {-6, 2, -8, 0, 0, -2};
    Poly polynomial2 = new Poly(poly2);

    if(polynomial2.degree() == polyDeg2) {
      System.out.println("Degree Test 2 Passed!");
    }
    else {
      System.out.println("Degree Test 2 Failed!");
      System.out.println(polynomial1.degree());
      System.out.println("Should have been: ");
      System.out.println(polyDeg2);
      System.out.println();
    }

    if(polynomial2.toString().equals(polyStr2)) {
      System.out.println("String Test 2 Passed!");
    }
    else {
      System.out.println("String Test 2 Failed!");
      System.out.println(polynomial2);
      System.out.println("Should have been: ");
      System.out.println(polyStr2);
      System.out.println();
    }

    if(polynomial2.evaluate(3) == polyEv3) {
      System.out.println("Evaluate Test 2 Passed!");
    }
    else {
      System.out.println("Evaluate Test 2 Failed!");
      System.out.println(polynomial2.evaluate(3));
      System.out.println("Should have been: ");
      System.out.println(polyEv3);
      System.out.println();
    }

    Poly polynomial3 = polynomial1.add(polynomial2);

    if(polynomial3.toString().equals(polyStr3)) {
      System.out.println("Addition Test Passed!");
    }
    else {
      System.out.println("Addition Test Failed!");
      System.out.println(polynomial3);
      System.out.println("Should have been: ");
      System.out.println(polyStr3);
      System.out.println();
    }

    if(polynomial3.degree() == polyDeg3) {
      System.out.println("Degree Test 3 Passed!");
    }
    else {
      System.out.println("Degree Test 3 Failed!");
      System.out.println(polynomial3.degree());
      System.out.println("Should have been: ");
      System.out.println(polyDeg3);
      System.out.println();
    }
  }

  public Poly(int[] coefficients) {
    // Contructs a polynomial from an array of integers
    length = coefficients.length;
    coeffs = new int[length];

    for (int i=0; i<coefficients.length; i++) {
      coeffs[i] = coefficients[i];
    }
  }

  public Poly(List<Integer> coefficients) {
    // Contructs a polynomial from a list of integers
    length = coefficients.size();
    coeffs = new int[length];

    for(int i=0; i<coefficients.size(); i++) {
      coeffs[i] = coefficients.get(i);
    }
  }

  public int degree() {
    // Returns the power of the highest non-zero term
    for(int i=length - 1; i >= 0; i--) {
      if (coeffs[i] != 0) {
        return i;
      }
    }
    return 0;
  }

  @Override
  public String toString() {
    String final_string = "";

    for(int i = length - 1; i>=0; i--) {
      if(i == degree()) {
        if(coeffs[i] == 1) {
          final_string = final_string + "x^" + i;
        }
        else if(coeffs[i] != 0) {
          final_string = final_string + coeffs[i] + "x^" + i;
        }
      }
      else if(i == 1) {
        if(coeffs[i] == 1) {
          final_string = final_string + "+x";
        }
        else if(coeffs[i] > 1) {
          final_string = final_string + "+" + coeffs[i] + "x";
        }
        else if(coeffs[i] < 0) {
          final_string = final_string + coeffs[i] + "x";
        }
      }
      else if(i == 0) {
        if(coeffs[i] > 0) {
          final_string = final_string + "+" + coeffs[i];
        }
        else if(coeffs[i] < 0) {
          final_string = final_string + coeffs[i];
        }
      }
      else {
        if(coeffs[i] > 1) {
          final_string = final_string + "+" + coeffs[i] + "x^" + i;
        }
        else if(coeffs[i] < 0) {
          final_string = final_string + coeffs[i] + "x^" + i;
        }
      }
    }
    return final_string;
  }

  public Poly add(Poly a) {
    // Adds two polynomials together
    // by adding together each scale degree in turn
    // Returns a new polynomial
    List<Integer> result = new ArrayList<>();
    int sum = 0;
    for(int i=0; i<this.length; i++) {
      for(int j=0; j<a.length; j++) {
        if(i == j) {
          sum = coeffs[i] + a.coeffs[j];
          result.add(sum);
        }
      }
    }
    Poly resultPoly = new Poly(result);
    return resultPoly;
  }

  public double evaluate(double x) {
    // Returns the value of the function at the point x
    // evaluates the polynomial as x = x
    double sum = 0;
    for(int i=this.length-1; i>=0; i--) {
      sum = sum + (this.coeffs[i] * Math.pow(x, i));
    }
    return sum;
  }
}
