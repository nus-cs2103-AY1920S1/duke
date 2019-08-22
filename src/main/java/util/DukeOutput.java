package util;

public class DukeOutput {
    private static String HORIZONTAL_DIVIDER = "    ____________________________________________________________\n";

    public static void printMessage(DukeMessage message) {
        String output = HORIZONTAL_DIVIDER +
                message.getMessage().replaceAll("(?m)^", "     ") + "\n"
                + HORIZONTAL_DIVIDER;

        System.out.println(output);
    }
}
