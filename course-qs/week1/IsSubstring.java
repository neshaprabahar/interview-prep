public class IsSubstring {

    public static boolean isSubstring(String string, String sub) {
        int count = 0;

        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == sub.charAt(count)) {
                count++;
            } else {
                count = 0;
            }
        }

        return (count == sub.length()) ? true : false;
    }

}