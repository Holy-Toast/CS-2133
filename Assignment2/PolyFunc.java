import java.util.*;


/**
* This class adds on to the function class and
* defines the methods and variables used in creating
* a polynomial.
*
* @author Patrick Roeber
*/
public class PolyFunc extends Function {
  int length;
  int[] coeffs;

  /**
  * Creates a polynomial using the given integer array.
  * The index of the array is the power of x.
  *
  * @param coefficients The integer array used to create
  *                     the polynomial.
  */
  public PolyFunc(int[] coefficients) {
    length = coefficients.length;
    coeffs = new int[length];

    for (int i=0; i<coefficients.length; i++) {
      coeffs[i] = coefficients[i];
    }
  }

  /**
  * Creates a polynomial using the given list.
  * The index of the list is the power of x.
  *
  * @param coefficients The list used to create
  *                     the polynomial.
  */
  public PolyFunc(List<Integer> coefficients) {
    length = coefficients.size();
    coeffs = new int[length];

    for(int i=0; i<coefficients.size(); i++) {
      coeffs[i] = coefficients.get(i);
    }
  }

  /**
  * Returns the index of the highest non-zero coefficient
  * in the polynomial.
  *
  * @return The degree of the polynomial
  */
  public int degree() {
    for(int i=length - 1; i >= 0; i--) {
      if (coeffs[i] != 0) {
        return i;
      }
    }
    return 0;
  }

  /**
  * Iterates over each element in the polynomial, determines
  * the representation of each element and
  * concatenates each string together to form one final string.
  *
  * @return The string representation of the polynomial
  */
  @Override
  public String toString() {
    String final_string = "";

    for(int i = length - 1; i>=0; i--) {
      if(i == degree()) {
        if(coeffs[i] == 1) {
          final_string = final_string + "x^" + i;
        }
        else if(coeffs[i] > 1) {
          final_string = final_string + "+" + coeffs[i] + "x^" + i;
        }
        else if(coeffs[i] < 0) {
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

  /**
  * Returns a polynomial which is the result of
  * the addition of the current polynomail
  * and another which is passed in as a.
  *
  * @param a The polynomial to be added with the
  *          current polynomial
  * @return  The polynomial that is the result of the
  *          addition of the two
  */
  public PolyFunc add(PolyFunc a) {
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
    PolyFunc resultPoly = new PolyFunc(result);
    return resultPoly;
  }

  /**
  * Solves the polynomial where x is equal to
  * the passed in variable.
  *
  * @param x The double to substitute x for
  * @return  The result of adding each of the terms
  *          after they have been multiplied out
  */
  public double evaluate(double x) {
    double sum = 0;
    for(int i=this.length-1; i>=0; i--) {
      sum = sum + (this.coeffs[i] * Math.pow(x, i));
    }
    return sum;
  }
}
