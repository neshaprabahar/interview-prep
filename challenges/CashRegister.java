/*
 * I have commented every change I have made
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CashRegister {
  /**
   * Iterate through each line of input.
   */

/* Created a private inner class (static as it will be accessed from a static context
              in calculateChange())
              This class should help with getting commonly used values within
              the calculateChange() method
              Design choice: easy to iterate over - only need to make changes to this class
              and not the calculateChange() method
*/

  private static class changeHelper {

    // The HashMap will store the String version of the denominations
    private Map<Double, String> denNames;
    // The last six denominations, are the first six are just the last six divided by 100
    private double[] denominations;

    // This method populates the denNames instance field and returns it
    public Map<Double, String> getStringsMap() {
      denNames = new HashMap<>();
      denNames.put(.01,   "One Pence");
      denNames.put(.02,   "Two Pence");
      denNames.put(.05,   "Five Pence");
      denNames.put(.1,    "Ten Pence");
      denNames.put(.2,    "Twenty Pence");
      denNames.put(.5,    "Fifty Pence");
      denNames.put(1.0,   "One Pound");
      denNames.put(2.0,   "Two Pounds");
      denNames.put(5.0,   "Five Pounds");
      denNames.put(10.0,  "Ten Pounds");
      denNames.put(20.0,  "Twenty Pounds");
      denNames.put(50.0,  "Fifty Pounds");

      return denNames;

    }

    // This method returns an array of the denominations
    public double[] getDenominations() {
      int i = 0;
      int length = 6;
      denominations = new double[length];
      denominations[i++] = 50;
      denominations[i++] = 20;
      denominations[i++] = 10;
      denominations[i++] = 5;
      denominations[i++] = 2;
      denominations[i++] = 1;

      return denominations;
    }

  }

  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in);
    BufferedReader in = new BufferedReader(reader);

    // Clarification: The prompt says this code already takes care of reading lines of input,
    //  however it only takes one cash and one purchasePrice
    // If I could change STDOUT, I would create a menu program to
    //  keep entering different numbers till the cashier would exit


    // Added an another exception handling case in the case denominations goes out of bounds

    try {
        double purchasePrice = Double.parseDouble(in.readLine());
        double cash = Double.parseDouble(in.readLine());
        CashRegister.calculateChange(purchasePrice, cash);
    } catch (ArrayIndexOutOfBoundsException arrE) {
        System.out.println(arrE);
        System.out.println("Disruption while calcultating change");
    } catch (Exception e) {
        System.out.println(e);
    }
  }

  public static void calculateChange(double purchasePrice, double cash) {
    // Access your code here. Feel free to create other classes as required

    // Truncating any decimals from the 1000th place
    // For example outputs 12.999 and 70.012 -> will truncate to 12.99 and 70.01
    //          to prevent unexpected behaviour
    String fullCashString = String.format("%f",(cash));
    int index = fullCashString.indexOf('.');
    String cashString = fullCashString.substring(0, index + 3);
    cash = Double.parseDouble(cashString);

    String fullPriceString = String.format("%f",(purchasePrice));
    index = fullPriceString.indexOf('.');
    String priceString = fullPriceString.substring(0, index + 3);
    purchasePrice = Double.parseDouble(priceString);


    if (purchasePrice == cash) {
      System.out.println("Zero");
      return;
    }

    if (purchasePrice > cash) {
      System.out.println("ERROR");
      return;
    }

    // This flag checks if the first denomination has been printed, to avoid printing the comma before
    boolean flag = false;

    // Formatted to string and then back to double because of unexpected arithmetic issues
    //       with IEEE representation of double

    int pounds = (int) (cash - purchasePrice);
    // getting hte amount of pence
    String changeString = String.format("%f",(cash - purchasePrice));
    index = changeString.indexOf('.');
    String penceString = changeString.substring(index + 1, index + 3);
    int pence = Integer.parseInt(penceString);

    // creating an instance of the private class created
    changeHelper register = new changeHelper();

    //getting the pre defined values
    double[] denominations = register.getDenominations();
    Map<Double, String> output = register.getStringsMap();

    int remaining = pounds;

    // First for-loop is to get all the pound notes and determine if there is any change >= 1 pound
    for (int i = 0; i < denominations.length +1; ++i) {
      int notes = remaining/(int)denominations[i];
      remaining -= (int)denominations[i] * notes;
      // Printing hte change in pounds
      for (int j = 0; j < notes; j++) {
        if (flag == true) {
          System.out.print(", ");
        }
        System.out.print(output.get(denominations[i]));
        flag = true;
      }

    }

    remaining = pence;

    // Calculating the decimal change
    for (int i = 0; i < denominations.length; ++i) {
      int coins = remaining/(int)denominations[i];
      remaining -= (int)denominations[i] * coins;
      // Printing the change in pence
      for (int j = 0; j < coins; j++) {
        if (flag == true) {
          System.out.print(", ");
        }
        System.out.print(output.get(denominations[i]/100));
        flag = true;
      }

    }

    System.out.println();

  }

}