public class DukeTextFormatter {
    private static final String BAR = "____________________________________________________________\n";
    private static final String FOUR_SPACE = "    ";
    private static final String FIVE_SPACE = "     ";

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
