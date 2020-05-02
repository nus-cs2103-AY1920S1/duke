package seedu.duke.core;

import seedu.duke.exception.DukeException;
import seedu.duke.model.Cmd;
import seedu.duke.model.CmdDeadline;
import seedu.duke.model.CmdDelete;
import seedu.duke.model.CmdDone;
import seedu.duke.model.CmdEvent;
import seedu.duke.model.CmdFind;
import seedu.duke.model.CmdList;
import seedu.duke.model.CmdTodo;
import seedu.duke.model.CmdUpdate;
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
        Cmd command;

        if (input.equals("list")) {
            command = new CmdList(list, output);
        } else if (cmd.equals("delete")) {
            command = new CmdDelete(description, list, storage);
        } else if (cmd.equals("todo")) {
            command = new CmdTodo(storage, output, list, cmd, description, "__dummy__");
        } else if (cmd.equals("deadline")) {
            command = new CmdDeadline(description, cmd, list, storage);
            //command.setMsg(handleEventOrDeadline(description, cmd, " /by ", list, storage));
        } else if (cmd.equals("event")) {
            command = new CmdEvent(description, cmd, list, storage);
            //command.setMsg(handleEventOrDeadline(description, cmd, " /at ", list, storage));
        } else if (cmd.equals("done")) {
            command = new CmdDone(description, list, ui, storage);
        } else if (cmd.equals("find")) {
            command = new CmdFind(ui, output, storage, list, description);
            //command.setMsg(ui.displayList(output, storage.searchTask(list, description.split(" "))));
        } else if (cmd.equals("update")) {
            command = new CmdUpdate(list, description, storage);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return command;
    }
}
