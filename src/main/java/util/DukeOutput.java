package util;

/***
 * <p>
 * Utility class to print program feedback and outputs.
 * </p>
 */
public class DukeOutput {
    private static String HORIZONTAL_DIVIDER = "    ____________________________________________________________\n";
    private static DukeOutputImplementation implementation;

    /***
     * <p>
     * Configures DukeOutput to use alternative output implementation.
     * </p>
     * @param implementation class to handle output instead.
     */
    public static void setUpDukeOutput(DukeOutputImplementation implementation) {
        DukeOutput.implementation = implementation;
    }

    /***
     * <p>
     * Prints message encapsulated in DukeMessage.
     * </p>
     * @param message message to be printed.
     */
    public static void printMessage(DukeMessage message) {
        String output = HORIZONTAL_DIVIDER
                + message.getMessage().replaceAll("(?m)^", "     ") + "\n"
                + HORIZONTAL_DIVIDER;

        if (implementation == null) {
            System.out.println(output);
        } else {
            implementation.printDukeOutput(output);
        }
    }
}
