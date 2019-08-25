public class Formatter {

    final private static String HORIZONTAL_LINE = "\t____________________________________________________________";
    final private static String INDENTATION = "     ";

    public static String formatMessage(String text){
        String formattedMessage =  HORIZONTAL_LINE + "\n" +
                INDENTATION + text + "\n" +
                HORIZONTAL_LINE + "\n";
        return formattedMessage;
    }

    public static void printLine(){
        System.out.println(HORIZONTAL_LINE);
    }

    public static String indentLine(String text){
        return INDENTATION + text;
    }

}
