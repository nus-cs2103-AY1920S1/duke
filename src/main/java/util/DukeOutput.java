package util;

public class DukeOutput {
    private static String HORIZONTAL_DIVIDER = "\t____________________________________________________________\n";

    public static void printMessage(String message) {
        String output = HORIZONTAL_DIVIDER + message.replaceAll("(?m)^", "\t ") +
                "\n" + HORIZONTAL_DIVIDER;

        System.out.println(output);
    }
}
