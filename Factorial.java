public class Factorial
{
  public static void main(String[] args)
  {
    long calc = calculate(0);
    System.out.print("Factorial.calculate(0) returned " + calc + ". ");
    if(calc == 1)
    {
      System.out.println("Test Passed!");
    }
    else
    {
      System.out.println("Test Failed!");
    }

    calc = calculate(5);
    System.out.print("Factorial.calculate(5) returned " + calc + ". ");
    if(calc == 120)
    {
      System.out.println("Test Passed!");
    }
    else
    {
      System.out.println("Test Failed!");
    }
  }

  public static long calculate(long n)
  {
    if(n < 0 || n > 20)
    {
      System.out.println("n must be a number between 0 and 19.");
      return -1;
    }
    else if(n == 0)
    {
      return 1;
    }
    else
    {
      return n * calculate(n - 1);
    }
  }
}
