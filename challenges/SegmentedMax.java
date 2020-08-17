import java.util.List;
import java.util.ArrayList;

public class SegmentedMax {

    public static long max(int k, List<Integer> segments) {
        // Assumption: n is even k <= n/2
        int n = segments.size();

        // if k == n/2 the max profit will be the entire list
        if (k == n/2) {
            long sum = 0;
            for (int i = 0; i < n; ++i) {
                sum += segments.get(i);
            }
        }

        // skip is the number of indices to skip to match the segments
        int skip = n/2;
        // To avoid counting the same numbers again
        long preSum1 = 0;
        long preSum2 = 0;

        long sum = 0;

        // Loop to find individual segment size
        for (int i = 0; i < k; ++i) {
            preSum1 += segments.get(i);
            preSum2 += segments.get(i + skip);
        }

        // Adding to find the first total sum of pair
        sum = preSum1 + preSum2;
        // Subtracting the values that will not be a part of the next pair
        preSum1 -= segments.get(0);
        preSum2 -= segments.get(skip);
        // Setting max as sum
        long max = 0;
        max = sum;

        // System.out.println(sum);

        for (int i = 1; i < skip; ++i) {
            // Getting the index of the last value in first segment
            int lastInd1 = i + k - 1;
            // Getting the index of the last value in the other segment
            int lastInd2 = (i + skip + k - 1) % n;
            // Adding to respective preSums get individual sums
            // All the other values in the segment already calculated and is in preSum1 & 2
            preSum1 += segments.get(lastInd1);
            preSum2 += segments.get(lastInd2);
            sum = preSum1 + preSum2;
            preSum1 -= segments.get(i);
            preSum2 -= segments.get(i + skip);
            max = (max >= sum) ? max : sum;
            // System.out.println(sum);
        }

        return max;

    }

    public static void main(String[] args) {
        List<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(183);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        test.add(1);
        test.add(21);
        // System.out.println(max(3, test));
    }

}