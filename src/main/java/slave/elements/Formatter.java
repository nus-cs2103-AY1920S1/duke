package slave.elements;

class Formatter {

    final private static String HORIZONTAL_LINE = "    ____________________________________________________________";
    final private static String INDENTATION = "     ";

    static String formatMessage(String text) {
        return HORIZONTAL_LINE + "\n" +
                INDENTATION + text + "\n" +
                HORIZONTAL_LINE + "\n";
    }

    static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    static String indentLine(String text) {
        return INDENTATION + text;
    }

}
