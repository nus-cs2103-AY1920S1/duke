public class DukeUtil {
    public static String concatStrings(String[] strings, String delimiter, int from, int to) {
        StringBuilder output = new StringBuilder();

        for (int i = from; i < to; i++) {
            output.append(strings[i]);
            output.append(delimiter);
        }

        if (to >= from) {
            output.append(strings[to]);
        }

        return output.toString();
    }

    public static int getIndexOf(String[] strings, String pattern) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(pattern)) {
                return i;
            }
        }

        return -1;
    }
}
