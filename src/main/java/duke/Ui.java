package duke;

import java.util.Scanner;

public class Ui {
    private Scanner in = new Scanner(System.in);

    /**
     * Displays given string with 4 spaces indentation on newlines and enclose the display with opening and closing
     * lines.
     *
     * @param str String to Display on CLI.
     */
    public void show(String str) {
        if (!str.endsWith("\n")) {
            str += '\n';
        }
        System.out.println("    ____________________________________________________________\n     "
            + str.replaceAll("\n(?!$)", "\n     ")
            + "    ____________________________________________________________\n");
    }

    public void showWelcome() {
        show("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        return in.nextLine();
    }
}
