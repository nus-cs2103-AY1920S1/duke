package duke;

import java.util.List;

public class Formatter {

    /**
     * Creates new Formatter used to format printing.
     */
   Formatter() {}

    /**
     * Adds 4 spaces for indentation for printing.
     * @param s String to be printed.
     * @return String that has additional indentation.
     */
    private String format(String s) {
        return "     " + s;
    }

    /**
     * Prints line for formatting.
     */
    private void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints strings in their respective lines with format.
     * @param s String with line breaks.
     */
    void printFormat(String s) {
        printLine();
        String[] lines = s.split("\n");
        for (String line: lines) {
            System.out.println(format(line));
        }
        printLine();
    }

    /**
     * Prints strings in their respective lines with format.
     * @param strings variable number of strings to be printed line by line.
     */
    void printFormat(String...strings) {
        printLine();
        for (String string: strings) {
            System.out.println(format(string));
        }
        printLine();
    }

    /**
     * Prints strings in list form for the list command.
     * @param strings list of Strings to be printed in the stipulated list format.
     */
    private void printFormat(List<String> strings) {
        int count = 1;
        for (String string: strings) {
            if (string != null) {
                System.out.println(format(String.format("%d. %s", count, string)));
                count++;
            }
        }
    }

    /**
     * Prints strings in their respective lines with format.
     * @param list list of Strings to be printed line by line.
     */
    void printFormat(String s, List<String> list) {
        printLine();
        System.out.println(format(s));
        printFormat(list);
        printLine();
    }
}
