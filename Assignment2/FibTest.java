public class FibTest
{
  public static void main(String[] args)
  {
    int fib5 = 5;
    int fib7 = 13;

    int fibTest1 = fibIter(5);
    if(fibTest1 == fib5) {
      System.out.println("Test Passed!");
    }
    else {
      System.out.println("Test Failed!");
      System.out.println(fibTest1);
      System.out.println("Should have been: ");
      System.out.println(fib5);
      System.out.println();
    }

    int fibTest2 = fibRecur(7);
    if(fibTest2 == fib7) {
      System.out.println("Test Passed!");
    }
    else {
      System.out.println("Test Failed!");
      System.out.println(fibTest2);
      System.out.println("Should have been: ");
      System.out.println(fib7);
      System.out.println();
    }

    long startTime = System.currentTimeMillis();
    int fib = fibRecur(40);
    long endTime = System.currentTimeMillis();
    long diffTime1 = endTime - startTime;

    startTime = System.currentTimeMillis();
    int fib1 = fibIter(40);
    endTime = System.currentTimeMillis();
    long diffTime2 = endTime - startTime;

    System.out.println("Recusion takes: " + diffTime1 + " milliseconds.");
    System.out.println("Iteration takes: " + diffTime2 + " milliseconds.");
  }
  public static int fibIter(int n)
  {
    int fib_number = 1;
    int fib1 = 1;
    int fib2 = 1;
    if(n < 1 || n > 48)
    {
      System.out.println("N should be a positive integer less than 48");
      return -1;
    }
    else
    {
      for(int i=3; i <= n; i++)
      {
        fib_number = fib1 + fib2;
        fib2 = fib1;
        fib1 = fib_number;
      }
      return fib_number;
    }
  }

  public static int fibRecur(int n)
  {
    if(n < 0 || n > 48)
    {
      System.out.println("N should be a positive integer less than 48.");
      return -1;
    }
    else if(n == 0)
    {
      return 0;
    }
    else if(n == 1 || n == 2)
    {
      return 1;
    }
    else
    {
      return fibRecur(n-1) + fibRecur(n-2);
    }
  }

  //fib(1) = 1
  //fib(2) = 1
  //fib(n) = fib(n-1) + fib(n-2)
}
