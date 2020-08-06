import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Finance {
  /**
   * Iterate through each line of input.
   */
  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    // error handling
    try {
      while ((line = in.readLine()) != null) {
        Finance.matchBenchmark(line);
      }
    } catch (Exception e) {
      System.out.println(e);
      System.out.println("Check Input");
    }
  }

  public static void matchBenchmark(String input) {
    // Access your code here. Feel free to create other classes as required

    String[] inputs = input.split(":");
    String portfolio = inputs[0];
    String benchmark = inputs[1];

    Map<String, Integer> allAssets = new HashMap<>();

    String[] pAssets = portfolio.split("\\|");

    for (int i = 0; i < pAssets.length; ++i) {
      String[] asset = pAssets[i].split(",");
      allAssets.put(asset[0] + "," + asset[1], Integer.parseInt(asset[2]));
    }

    String[] bAssets = benchmark.split("\\|");

    // negative value - buy, positive value - sell

    for (int i = 0; i < bAssets.length; ++i) {
      String[] asset = bAssets[i].split(",");
      String company = asset[0] + "," + asset[1];
      if(allAssets.keySet().contains(company)) {
        allAssets.put(company,
            allAssets.get(company) - Integer.parseInt(asset[2]));
      } else {
        allAssets.put(company, Integer.parseInt(asset[2]) * -1);
      }

    }

    // Sorting by company name, then asset type
    List<String> sortedKeys = new ArrayList<>(allAssets.keySet());
    Collections.sort(sortedKeys);

    for (String key: sortedKeys) {
      int diff = allAssets.get(key);

      if (diff == 0) continue;

      if (diff > 0) {
        System.out.print("SELL,");
      } else if (diff < 0) {
        System.out.print("BUY,");
        diff *= -1;
      }

      System.out.print(key + ",");
      System.out.println(diff);
    }

  }

}