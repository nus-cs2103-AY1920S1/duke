package duke.dukeinterface;

import java.lang.StringBuilder;

/**
 * Involves in the setup and exiting of Duke. It also ensure that the task
 * list in Duke is not violated.
 */
public class Ui {
    /**
     * This field combines strings together and form Duke's replies to user's inputs.
     */
    private StringBuilder sb;

    /**
     * Reply to the user upon startup.
     *
     * @return Duke's reply to the user upon startup.
     */
    public String greet() {
        sb = new StringBuilder();
        String logo = "    o-o    o   o  o    o  o--o \n"
                + "    |    \\    |    |   |   /    |    \n"
                + "    |     O  |    |  OO     O-o  \n"
                + "    |    /    |    |   |   \\    |    \n"
                + "    o-o     o-o  o    o   o--o\n\n";

        sb.append(logo);
        sb.append("     Hello! I'm Duke\n     What can I do for you?\n");
        sb.append("     To show a list my commands please type help\n");
        sb.append(printLine());
        sb.append(printLine());
        sb.append("     Here are the tasks that you have now:\n");
        return sb.toString();
    }

    /**
     * Repeat the words given in the user command.
     *
     * @param command input that is given by the user.
     * @return Duke's reply to the given user input.
     */
    // Echo commands entered by users
    @SuppressWarnings({"StringConcatenationInsideStringBufferAppend", "unused"})
    public String echo(String command) {
        sb = new StringBuilder();
        sb.append(printLine());
        sb.append("     " + command + "\n");
        sb.append(printLine());
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Forms the statement for user input in the GUI.
     *
     * @param command input that is given by the user.
     * @return Forms the statement that the user said for dialog box.
     */
    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    public String userCommand(String command) {
        sb = new StringBuilder();
        sb.append(printLine());
        sb.append("         User: " + command + "\n");
        sb.append(printLine());
        sb.append("\n");
        return sb.toString();
    }


    /**
     * Gives a string as a border.
     *
     * @return a string that forms the border for duke.
     */
    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}
