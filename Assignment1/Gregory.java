/**
* This program emulates PI using the Gregory series.
*
* @author Patrick Roeber
*/
public class Gregory {
  
  /**
  * Takes in a number from the command line, then uses the Gregory
  * series to aproximate PI. Then prints out the result and the percent
  * error using Math.PI as the given value.
  *
  * @param args A String array, the first element of which will be the
  *             number of times to calculate the Gregory series
  */
  public static void main(String[] args) {
    int gregNum = 0;
    double gregPI = 0;
    double total = 0.0;
    double percErr = 0.0;

    if(args.length < 1) {
      System.out.println("usage: Gregory <number>");
    }
    else {
      gregNum = Integer.parseInt(args[0]);

      for(int i=1; i <= gregNum; i++) {
        gregPI = ( (Math.pow(-1, (i+1) ) ) / ( (2 * i) - 1) );
        total = total + gregPI;
      }

      total = total * 4;

      percErr = ( (total - Math.PI) / Math.PI) * 100;

      percErr = Math.abs(percErr);

      System.out.println("Pi according to Gregory series: " + total);
      System.out.println("This differs from Java's value by " + percErr + " percent.");
    }
  }
}
