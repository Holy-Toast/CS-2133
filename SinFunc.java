/**
* This class adds on to the Function class, defining the
* evaluate method using java's built in sine function.
*
* @author Patrick Roeber
*/
public class SinFunc extends Function {

  /**
  * Evaluates the sine of x.
  *
  * @param x The variable to be used when evaluating
  * @return  The value of sine at x
  */
  public double evaluate(double x) {
    return Math.sin(x);
  }
}
