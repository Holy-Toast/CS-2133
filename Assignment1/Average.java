import java.util.*;

/**
* This program takes input from the user until the user 
* inputs a negative number, then it calculates the average
* of the numbers inputted, ignoring the negative number.
*
* @author Patrick Roeber
*/
public class Average {
  
  /**
  * This function takes input from the user until the user 
  * inputs a negative number, then it calculates the average
  * of the numbers inputted, ignoring the negative number.
  * Then prints it to the screen.
  *
  * @param args A String array, unused in this program.
  */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double inputNumber = 0.0;
    double total = 0.0;
    double average = 0.0;

    int i = 0;

    List<Double> inputList = new ArrayList<>();

    boolean no_negatives = true;

    System.out.println("Please enter a series of numbers. Enter a negative number to quit.");

    while (no_negatives) {
      inputNumber = input.nextDouble();
      if (inputNumber < 0) {
        no_negatives = false;
        break;
      }
      inputList.add(inputNumber);
    }

    for (i = 0; i < inputList.size(); i++) {
      total = total + inputList.get(i);
    }
    average = total / i;
    System.out.printf("You entered %d numbers averaging %1.1f.", i, average);
  }
}
