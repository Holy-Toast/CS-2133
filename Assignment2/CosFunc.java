/**
* This class adds on to the Function class, defining the
* evaluate method using java's built in cosine function.
*
* @author Patrick Roeber
*/
public class CosFunc extends Function {

  /**
  * Evaluates the cosine of x.
  *
  * @param x The variable to be used when evaluating
  * @return  The value of cosine at x
  */
  public double evaluate(double x) {
    return Math.cos(x);
  }
}
