package Data;

import java.util.List;

public class Formatter {

    /**
     * Creates new Formatter used to format printing.
     */
    public Formatter() {}

    /**
     * Adds 4 spaces for indentation for printing.
     * @param s String to be printed.
     * @return String that has additional indentation.
     */
    public String format(String s) {
        return "     " + s;
    }

    /**
     * Prints line for formatting.
     */
    private void printLine() {
        System.out.println("    ____________________________________________________________");
    }


    /**
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
     * @return Appended string with new line for each element.
     */
    public String appendStrings(String s, List<String> list) {
        String newString = "";
        newString += s + "\n";
        int count = 1;
        for (String string: list) {
            newString += format(String.format("%d. %s", count, string)) + "\n";
            count++;
        }
       return newString;
    }


    /**
     * Prints strings in their respective lines with format.
     * @param s String with line breaks.
     */
    public void printFormat(String s) {
        printLine();
        String[] lines = s.split("\n");
        for (String line: lines) {
            System.out.println(format(line));
        }
        printLine();
    }


}
