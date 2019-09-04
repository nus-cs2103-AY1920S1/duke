package duke.command;

import duke.dukeinterface.DukeException;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Add task to the task list based on to do, deadline and event.
 */
public class addCommand extends command {
    /**
     * Get the description the input task.
     * @param commandArr current task of the user input.
     * @return string of the description of the task.
     * @throws DukeException prompts the user if the task input is invalid.
     */
    public String getDescription(String[] commandArr) throws DukeException {
        StringJoiner description = new StringJoiner(" ");
        int index;
        if (commandArr[0].equals("todo")) {
            index = commandArr.length;
        } else if (commandArr[0].equals("deadline")) {
            index = Arrays.asList(commandArr).indexOf("/by");
        } else {
            index = Arrays.asList(commandArr).indexOf("/at");
        }

        for (int i = 1; i < index; i++) {
            description.add(commandArr[i]);
        }

        if (index == -1) {
            throw new DukeException(
                    "    ____________________________________________________________\n"
                            +  "     ☹ OOPS!!! The timing of a " + commandArr[0] + " cannot be empty.\n"
                            + "    ____________________________________________________________\n");
        } else if (description.toString().equals("")) {
            throw new DukeException(
                    "    ____________________________________________________________\n"
                            + "     ☹ OOPS!!! The description of a " + commandArr[0] + " cannot be empty.\n"
                            + "    ____________________________________________________________\n");
        }

        return description.toString();
    }

    /**
     * Format the time from the input of the user.
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

        if (index == -1 || timing.toString().equals("")) {
            throw new DukeException(
                    "    ____________________________________________________________\n"
                            + "     ☹ OOPS!!! The timing of a " + commandArr[0] + " cannot be empty.\n"
                            + "    ____________________________________________________________\n");
        }

        return timing.toString();
    }
}
