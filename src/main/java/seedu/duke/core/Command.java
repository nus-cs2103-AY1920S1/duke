package seedu.duke.core;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.TaskListEmptyException;
import seedu.duke.model.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public class Command {
    /**
     * Parses the command input from Scanner object and executes accordingly.
     * @param input single line input which includes all necessary command and details.
     * @param cmd cmd which is already parsed from input string.
     * @param description task description.
     * @param list task list
     * @param ui UI object.
     * @param storage storage unit which handles saving and updating the task list.
     * @throws DukeException when user does not write full command input.
     * @throws ParseException when user inputs the date in incorrect format.
     * @throws IOException when file is not found or cannot be opened.
     */
    public String parseCommand(String input, String cmd, String description,
                             List<Task> list, Storage storage, Ui ui) throws DukeException,
            ParseException, IOException {
        String output = "";

        if (input.equals("list")) {
            output += ui.displayList(output, list);
        } else if (cmd.equals("delete")) {
            try {
                if (description.equals("") || description == null) {
                    throw new DukeException("Entered index empty..");
                } else {
                    int index = Integer.valueOf(description) - 1;
                    output += storage.removeTask(output, list, index);
                }
            } catch (TaskListEmptyException e) {
                output += e;
            } catch (DukeException e) {
                output += e;
            }
        } else if (cmd.equals("todo")) {
            output += storage.addTask(output, list, cmd, description, "__dummy__");
        } else if (cmd.equals("deadline")) {
            String[] arr1 = description.split(" /by ", 2);
            validateTime(arr1);
            String desc = arr1[0];
            String by = arr1[1];

            output += storage.addTask(output, list, cmd, desc, by);
        } else if (cmd.equals("event")) {
            String[] arr2 = description.split(" /at ", 2);
            validateTime(arr2);
            String desc = arr2[0];
            String at = arr2[1];

            output += storage.addTask(output, list, cmd, desc, at);
        } else if (cmd.equals("done")) {
            if (description.equals("") || description == null) {
                throw new DukeException("Oops! list index not entered for 'done'");
            } else {

                int index = Integer.valueOf(description) - 1;
                list.get(index).markAsDone();

                storage.saveTask(list);
                output = "Nice! I've marked this task as done:\n";
                output = displayTask(output, list, index);
            }
        } else if (cmd.equals("find")) {
            output += ui.displayList(output, storage.searchTask(list, description));
        } else {
            //incorrect command
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return output;
    }

    /**
     * validates whether user input correctly specified the time and description.
     * @param arr string array which includes both description and /at or /by for Event and Deadline objects.
     * @throws DukeException when user inputs empty task description or does not specify after /at or /by.
     */
    public void validateTime(String[] arr) throws DukeException {
        if (arr[0].equals("")) {
            throw new DukeException("Oops! please specify task description");
        } else if (!arr[0].equals("") && arr.length == 1) {
            throw new DukeException("Oops! please specify time (/at, /by ...");
        }
    }



    /**
     * Prints only the specified task from task list.
     * @param list Task list (ArrayList) where all tasks are stored
     * @param index index of which task user want to print out.
     */
    public String displayTask(String output, List<Task> list, int index) {
        if (index >= 0) {
            output = new StringBuilder(output)
                    .append(list.get(index) + "\n").toString() ;
        }
        return output;
    }
}
