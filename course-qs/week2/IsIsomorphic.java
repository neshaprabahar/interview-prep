import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


public class IsIsomorphic {

    public static boolean isIsomorphic (String s, String t) {

        if (s.length() != t.length()) return false;

        if (s.equals("")) return false;

        Map<Character, Character> charMap = new HashMap<>();

        Set<Character> charSet = new HashSet<>();

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (charMap.containsKey(c1)) {
                if (c2 != charMap.get(c1)) return false;
            } else {
                if (charSet.contains(c2)) return false;
            }

            charSet.add(c2);
            charMap.put(c1, c2);

        }

        return true;

    }

}