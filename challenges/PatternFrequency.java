import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PatternFrequency {


  // Creating class KMP to run the Knuth Morris Pratt Algorithm
  public static class KMP {

    // This returns the Longest Prefix and Suffix array.
    // Each index holds the longest prefix that is also a suffix that ends at that index of the pattern
    public int[] lpsArray(String pattern) {
      int length = pattern.length();
      int l = 0;
      int[] lps = new int[length];
      lps[0] = 0;

      for (int i = 1; i < length;) {
        if (pattern.charAt(i) == pattern.charAt(l)) {
           lps[i] = ++l;
           i++;
        } else {
          if (l == 0) {
            lps[i] = l;
            i++;
          } else {
            l = lps[l-1];
          }
        }
      }
      return lps;
    }

    // Modified KMP
    public int countMatches(String blob, String pattern, int[] lps) {

      int i = 0;
      int j = 0;
      int count = 0;

      while (i < blob.length()) {
        // checking for matches
        if (blob.charAt(i) == pattern.charAt(j)) {
          i++; j++;
        }
        // if there is a match, increase count
        if (j == pattern.length()) {

          count++;
          i = i - j + 1;
          j = 0;

        } else {

          // if there is a mismatch, match the suffix with the lps array
          if (i < blob.length() && (blob.charAt(i) != pattern.charAt(j))) {
            if (j == 0) i++;
            else j = lps[j-1];
          }

        }

      }

      return count;
    }
  }
  /**
   * Iterate through each line of input.
   */

  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    try {
      while ((line = in.readLine()) != null) {
        String[] splittedInput = line.split(";");
        String pattern = splittedInput[0];
        String blobs = splittedInput[1];
        PatternFrequency.doSomething(pattern, blobs);
      }
    } catch (Exception e) {
      System.out.println(e);
      System.out.println("Check Input!");
    }
  }

  public static void doSomething(String pattern, String blobs) {
    // Write your code here. Feel free to create more methods and/or classes

    String[] Blobs = blobs.split("\\|");
    int patternLength = pattern.length();

    boolean first = true;
    //edge case
    if (patternLength == 0) {
      for (int i = 0; i < Blobs.length; ++i) {
        if (first == true) {
          System.out.print("0");
          first = false;
        } else {
          System.out.print("|0");
        }
      }
      System.out.print("|0");
      System.out.println();
      return;
    }

    // creating new KMP object
    KMP kmp = new KMP();
    int[] lps = kmp.lpsArray(pattern);
    int sum = 0;

    for (int i = 0; i < Blobs.length; ++i) {
      int blobCount = kmp.countMatches(Blobs[i], pattern, lps);
      System.out.print(blobCount);
      System.out.print("|");
      sum += blobCount;
    }
    System.out.println(sum);

    // Sub-optimal - indexOF is O(m*n)
    /*String output = "";
    int sum = 0;
    for (int i = 0; i < Blobs.length; ++i) {
      String current = Blobs[i];
      int blobLength = current.length();
      int blobCount = 0;

      for (int j = 0; j < blobLength;) {
        j = current.indexOf(pattern, j);
        if (j == -1 ) {
          break;
        }
        blobCount++;
        j++;
      }
      output +=  String.format("%d", blobCount) + "|";
      sum += blobCount;

    }
    output += String.format("%d", sum);
    System.out.println(output);
    */

  }
}