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
     * @param storage storage unit which handles saving & updating the task list.
     * @throws DukeException when user does not write full command input.
     * @throws ParseException when user inputs the date in incorrect format.
     * @throws IOException when file is not found or cannot be opened.
     */
    public void parseCommand(String input, String cmd, String description,
                             List<Task> list, Storage storage) throws DukeException,
            ParseException, IOException {
        if (input.equals("list")) {
            displayList(list);
        } else if (cmd.equals("delete")) {
            try {
                if (description.equals("") || description == null) {
                    throw new DukeException("Entered index empty..");
                } else {
                    int index = Integer.valueOf(description) - 1;
                    storage.removeTask(list, index);
                }
            } catch (TaskListEmptyException e) {
                System.out.println(e);
            }
        } else if (cmd.equals("todo")) {
            storage.addTask(list, cmd, description, "__dummy__");
        } else if (cmd.equals("deadline")) {
            String[] arr1 = description.split(" /by ", 2);
            validateTime(arr1);
            String desc = arr1[0];
            String by = arr1[1];

            storage.addTask(list, cmd, desc, by);
        } else if (cmd.equals("event")) {
            String[] arr2 = description.split(" /at ", 2);
            validateTime(arr2);
            String desc = arr2[0];
            String at = arr2[1];

            storage.addTask(list, cmd, desc, at);
        } else if (cmd.equals("done")) {
            int index = Integer.valueOf(description) - 1;
            list.get(index).markAsDone();
            storage.saveTask(list);

            System.out.println("Nice! I've marked this task as done:");
            displayTask(list, index);
        } else {
            //incorrect command
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
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
     * Prints all the tasks inside task list.
     * @param list Task list (ArrayList) where all tasks are stored.
     */
    public void displayList(List<Task> list) {
        System.out.println("Here are the tasks in your list:");
        int index = 0;
        for (Task task : list) {
            index++;
            System.out.println(index + "." + task);
        }
    }

    /**
     * Prints only the specified task from task list.
     * @param list Task list (ArrayList) where all tasks are stored
     * @param index index of which task user want to print out.
     */
    public void displayTask(List<Task> list, int index) {
        if (index >= 0) {
            System.out.println(list.get(index));
        }
    }
}
