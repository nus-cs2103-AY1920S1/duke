package seedu.duke.model;

import seedu.duke.core.Storage;
import seedu.duke.exception.DukeException;
import seedu.duke.model.dto.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public  abstract class Cmd {
    private String description;
    private String msg = "";

    public Cmd(String description) {
        this.description = description;
    }

    //void constructor
    public Cmd() {
        this.description = "";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Handles creating Event or Deadline task object adding tasks to the storage.
     * @param description description of the task.
     * @param cmd type of Task (Event/Deadline).
     * @param regex split indicator (/by or /at).
     * @param taskList task list.
     * @param storage storage unit which handles saving and loading of the tasks created/modified.
     * @return String output after handling the event/deadline creation.
     * @throws DukeException when user does not write full command input.
     * @throws ParseException when user inputs the date in incorrect format.
     * @throws IOException when file is not found or cannot be opened.
     */
    public String handleEventOrDeadline(String description, String cmd,
                                        String regex, List<Task> taskList,
                                        Storage storage) throws DukeException, IOException, ParseException {
        String[] arr1 = description.split(regex, 2);
        validateTime(arr1);
        String desc = arr1[0];
        String by = arr1[1];

        String output = "";
        output += storage.addTask(output, taskList, cmd, desc, by);
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
}
