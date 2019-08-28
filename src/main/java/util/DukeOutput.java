package util;

/***
 * Utility class to print program feedback and outputs.
 */
public class DukeOutput {
    private static String HORIZONTAL_DIVIDER = "    ____________________________________________________________\n";

    /***
     * Prints message encapsulated in DukeMessage.
     * @param message message to be printed.
     */
    public static void printMessage(DukeMessage message) {
        String output = HORIZONTAL_DIVIDER +
                message.getMessage().replaceAll("(?m)^", "     ") + "\n"
                + HORIZONTAL_DIVIDER;

        System.out.println(output);
    }
}
