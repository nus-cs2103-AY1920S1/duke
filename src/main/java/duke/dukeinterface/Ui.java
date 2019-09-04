package duke.dukeinterface;

import java.lang.StringBuilder;

/**
 * Involves in the setup and exiting of Duke. It also ensure that the task
 * list in Duke is not violated.
 */
public class Ui {
    private StringBuilder sb;

    public String greet() {
        sb = new StringBuilder();
        String logo = "    o-o    o   o  o    o  o--o \n" +
                "    |    \\    |    |   |   /    |    \n" +
                "    |     O  |    |  OO     O-o  \n" +
                "    |    /    |    |   |   \\    |    \n" +
                "    o-o     o-o  o    o   o--o\n\n";

        sb.append(logo);
        sb.append("     Hello! I'm Duke\n     What can I do for you?\n");
        sb.append(printLine());
        sb.append(printLine());
        sb.append("     Here are the tasks that you have now:\n");
        return sb.toString();
    }

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
     * @return a string that forms the border for duke.
     */
    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}
