package util;

public class DukeOutput {
    private static String HORIZONTAL_DIVIDER = "    ____________________________________________________________\n";

    public static void printMessage(String message) {
        String output = HORIZONTAL_DIVIDER + message.replaceAll("(?m)^", "     ") + HORIZONTAL_DIVIDER;

        System.out.println(output);
    }
}
