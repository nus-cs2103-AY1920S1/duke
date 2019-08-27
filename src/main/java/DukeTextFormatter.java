/**
 * A static class that handles text formatting for Duke's responses to the user.
 */
public class DukeTextFormatter {
    private static final String BAR = "____________________________________________________________\n";
    private static final String FOUR_SPACE = "    ";
    private static final String FIVE_SPACE = "     ";

    /**
     * Formats a <code>String</code> to look nicer when printed to the console. Generally, this method should also be 
     * called whenever a call is made to one of DukeUi's messages.
     * 
     * @param inputString The <code>String</code> to be printed to the console.
     * @return The formatted <code>String</code>
     */
    public static String makeFormattedText(String inputString) {
        //Splits the string into separate lines
        String [] splitString = inputString.split("\n");

        StringBuilder sb = new StringBuilder();

        sb.append(FOUR_SPACE);
        sb.append(BAR);

        //Indents each line of the String to be formatted
        for (String s : splitString) {
            sb.append(FIVE_SPACE);
            sb.append(s);
            sb.append('\n');
        }

        sb.append(FOUR_SPACE);
        sb.append(BAR);

        return sb.toString();
    }
}
