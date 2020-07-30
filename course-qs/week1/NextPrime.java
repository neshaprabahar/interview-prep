public class NextPrime {
    public static boolean isPrime(int n) {

        if (n <= 1) return false;
        if (n <= 3) return true;

        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {

            if ((n % i == 0) || (n % (i + 2) == 0)) return false;

        }

        return true;
    }

    public static int nextPrime(int n) {
        if (n == 1) return 2;
        if (n == 2) return 3;

        int k = n;
        if (n % 2 == 0) {
            if (isPrime(n + 1)) return n + 1;
            else k++;
        }

        while (true) {
            k += 2;
            if (isPrime(k)) return k;
        }

    }

    // public static void main(String[] args) {
    //     System.out.println(nextPrime(37));
    //     System.out.println(nextPrime(49));

    // }
}