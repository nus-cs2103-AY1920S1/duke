package duke.command;

import duke.dukeinterface.DukeException;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Add task to the task list based on to do, deadline and event.
 */
public class AddCommand extends Command {
    /**
     * Get the description the input task.
     *
     * @param commandArr current task of the user input.
     * @return string of the description of the task.
     * @throws DukeException prompts the user if the task input is invalid.
     */
    public String getDescription(String[] commandArr) throws DukeException {
        StringJoiner description = new StringJoiner(" ");
        int index;
        String start = commandArr[0];
        if (start.equals("todo") || start.equals("t")) {
            index = commandArr.length;
        } else if (start.equals("deadline") || start.equals("d")) {
            index = Arrays.asList(commandArr).indexOf("/by");
        } else {
            index = Arrays.asList(commandArr).indexOf("/at");
        }

        for (int i = 1; i < index; i++) {
            description.add(commandArr[i]);
        }

        assert index >= 0 : printLine()
                +  "     ☹ OOPS!!! The timing of a " + commandArr[0] + " cannot be empty.\n"
                + printLine();

        if (description.toString().equals("")) {
            throw new DukeException(
                    printLine()
                            + "     ☹ OOPS!!! The description of a " + commandArr[0] + " cannot be empty.\n"
                            + printLine());
        }

        return description.toString();
    }

    /**
     * Format the time from the input of the user.
     *
     * @param commandArr current task of the user input.
     * @return formatted version of the timings.
     * @throws DukeException prompts the user of invalid time format.
     */
    public String getTime(String[] commandArr) throws DukeException {
        StringJoiner timing = new StringJoiner(" ");
        int index;
        if (commandArr[0].equals("deadline")) {
            index = Arrays.asList(commandArr).indexOf("/by");
        } else {
            index = Arrays.asList(commandArr).indexOf("/at");
        }

        for (int i = index + 1; i < commandArr.length; i++) {
            timing.add(commandArr[i]);
        }

        //Check whether there is /at or /by in the command
        assert index >= 0 : printLine()
                + "     ☹ OOPS!!! The timing of a " + commandArr[0] + " cannot be empty.\n"
                + printLine();

        if (timing.toString().equals("")) {
            throw new DukeException(
                    printLine()
                            + "     ☹ OOPS!!! The timing of a " + commandArr[0] + " cannot be empty.\n"
                            + printLine());
        }

        return timing.toString();
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
