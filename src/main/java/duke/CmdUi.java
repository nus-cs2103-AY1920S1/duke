package duke;

import java.util.List;
import java.util.Scanner;

public class CmdUi implements Ui {
    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENT = "     ";
    private Scanner input = new Scanner(System.in);

    /**
     * Returns whether there is another line of input to read.
     *
     * @return Whether there is another line of input.
     */
    boolean hasNextLine() {
        return input.hasNextLine();
    }

    /**
     * Reads a line from the input.
     *
     * @return Next line of input.
     */
    String readCommand() {
        return input.nextLine();
    }

    /**
     * Outputs a list of lines.
     *
     * @param messages List of lines.
     */
    @Override
    public void showMessage(List<String> messages) {
        System.out.println(LINE);
        for (String message : messages) {
            System.out.println(INDENT + message);
        }
        System.out.println(LINE);
        System.out.println();
    }
}
