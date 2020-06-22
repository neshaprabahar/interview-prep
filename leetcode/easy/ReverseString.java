class ReverseString {
    public void reverseString(char[] s) {
        int begin = 0;
        int end = s.length - 1;

        while(begin < end) {
            char temp = s[begin];
            s[begin++] = s[end];
            s[end--] = temp;
        }
    }
}