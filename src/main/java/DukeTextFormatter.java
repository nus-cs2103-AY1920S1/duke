public class DukeTextFormatter {
    public static final String bar = "____________________________________________________________\n";
    public static final String fourspace = "    ";
    public static final String fivespace = "     ";

    public static String makeFormattedText(String inputString) {
        //returns the input String, but looking nicer.
        String [] splitString = inputString.split("\n");
        StringBuilder sb = new StringBuilder();

        sb.append(fourspace);
        sb.append(bar);

        for(String s : splitString) {
            sb.append(fivespace);
            sb.append(s);
            sb.append('\n');
        }

        sb.append(fourspace);
        sb.append(bar);

        return sb.toString();
    }
}
