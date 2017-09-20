/**
* This class calculates the Ramanujan number as given by the
* user via the command line.
*
* @author Patrick Roeber
*/
public class Ramanujan {
  
  /**
  * Calculates the Ramanujan number using the first element in the args
  * array given by the user via the command line. Then calculates the 
  * percent error of that number by comparing it to Math.PI.
  *
  * @param args A String array, the first element will be used
  *             to find the Ramanujan number.
  */
  public static void main(String[] args) {
    if(args.length < 1) {
      System.out.println("usage: Ramanujan <number>");
    }
    else {
      int ramNum = Integer.parseInt(args[0]);
      double ramPI = 0;
      double total = 0;

      for(int i=0; i <= ramNum; i++) {
        ramPI = (Factorial.calculate(4 * i) * (1103 + (26390 * i))) / (Math.pow(Factorial.calculate(i), 4) * Math.pow(396, (4 * i)));
        total = total + ramPI;
      }

      total = total * (Math.sqrt(8) / 9801);

      total = 1 / total;

      double percErr = (Math.abs((total - Math.PI)) / Math.PI) * 100;

      System.out.println("Pi according to Ramanujan series: " + total);
      System.out.println("This differs from Java's value by " + percErr + " percent.");
    }
  }
}
