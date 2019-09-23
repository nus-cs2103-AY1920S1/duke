package storage;

import java.util.List;

public class Formatter {

    private static String SPACE_FORMATTER = "     ";

    /**
     * Creates new Formatter used to format printing.
     */
    public Formatter() {

    }

    /**
     * Adds 4 spaces for indentation for printing.
     *
     * @param s String to be printed.
     * @return String that has additional indentation.
     */
    public String format(String s) {
        return SPACE_FORMATTER + s;
    }

    /**
     * Appends a variable number of strings.
     *  
     * @return Appended string with new line for each element (varargs version).
     */
    public String appendStrings(String... strings) {
        String s = "";
        for (String string: strings) {
            if (!string.equals("")) {
                s += format(string) + "\n";
            }
        }
        return s;
    }

    /**
     * Appends heading string with list and the respective number.
     * Used specifically for listing out tasks.
     *
     * @param s Title string for list description.
     * @param list List containing tasks in String form.
     * @return Appended string with new line for each element.
     */
    public String appendStrings(String s, List<String> list) {
        String newString = s + "\n";
        int count = 1;
        for (String string: list) {
            newString += format(String.format("%d. %s", count, string)) + "\n";
            count++;
        }
        return newString;
    }

}
