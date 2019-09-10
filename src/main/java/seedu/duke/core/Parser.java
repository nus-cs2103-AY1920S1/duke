package seedu.duke.core;

import seedu.duke.exception.DukeException;
import seedu.duke.model.Cmd;
import seedu.duke.model.CmdDelete;
import seedu.duke.model.CmdDone;
import seedu.duke.model.CmdList;
import seedu.duke.model.dto.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public class Parser {
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
    public Cmd parseCommand(String input, String cmd, String description,
                               List<Task> list, Storage storage, Ui ui) throws DukeException,
            ParseException, IOException {
        String output = "";
        Cmd command = new Cmd();

        if (input.equals("list")) {
            command = new CmdList(list, output);
        } else if (cmd.equals("delete")) {
            command = new CmdDelete(description, list, storage);
        } else if (cmd.equals("todo")) {
            command.setMsg(storage.addTask(output, list, cmd,
                    description, "__dummy__"));
        } else if (cmd.equals("deadline")) {
            command.setMsg(handleEventOrDeadline(description, cmd, " /by ", list, storage));
        } else if (cmd.equals("event")) {
            command.setMsg(handleEventOrDeadline(description, cmd, " /at ", list, storage));
        } else if (cmd.equals("done")) {
            command = new CmdDone(description, list, ui, storage);
        } else if (cmd.equals("find")) {
            command.setMsg(ui.displayList(output, storage.searchTask(list, description.split(" "))));
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return command;
    }

    public String handleEventOrDeadline(String description, String cmd,
                              String regex, List<Task> taskList,
                              Storage storage) throws DukeException, IOException, ParseException{
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
