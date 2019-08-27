package slave.elements;

/**
 * A class that formats strings to be printed
 */
public class Formatter {

    final private static String HORIZONTAL_LINE = "    ____________________________________________________________";
    final private static String INDENTATION = "     ";

    /**
     * Formats input message
     * @param text message to be formatted
     * @return Formatted message
     */
    public static String formatMessage(String text){
        String formattedMessage =  HORIZONTAL_LINE + "\n" +
                INDENTATION + text + "\n" +
                HORIZONTAL_LINE + "\n";
        return formattedMessage;
    }

    /**
     * Prints a Line
     */
    public static void printLine(){
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Adds indentation (5 spaces)
     * @param text Text to be indented
     * @return Indented text
     */
    public static String indentLine(String text){
        return INDENTATION + text;
    }

}
