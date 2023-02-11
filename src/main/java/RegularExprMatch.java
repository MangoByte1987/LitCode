public class RegularExprMatch {
    public static boolean isMatch(String s, String p) {
        int rows = s.length();
        int columns = p.length();
        if (rows == 0 || columns == 0)
            return true;
        if (columns == 0)
            return false;

        boolean result[][] = new boolean[rows + 1][columns + 1];
        result[0][0] = true;
        //for *
        for (int i = 2; i < columns + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                result[0][i] = result[0][i - 2];
            }
        }

        //for rest

        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < columns + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    result[i][j] = result[i - 1][j - 1];
                } else if (j > 1 && p.charAt(j - 1) == '*') {
                    result[i][j] = result[i][j - 2];
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        result[i][j] = result[i][j] | result[i - 1][j];
                    }
                }
            }
        }

        return result[rows][columns];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("aa", ".*"));
        System.out.println(isMatch("aa", "."));
        System.out.println(isMatch("aab", "c*a*b"));
    }
}
