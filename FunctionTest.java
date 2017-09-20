public class FunctionTest {
  public static void main(String[] args) {
    double answer = 0.0;
    answer = findRoot(3, 4, 0.00000001);
    System.out.println("The answer is: " + answer);
  }

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
