package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Event;
import duke.task.TaskWithDate;

import java.text.ParseException;

import static duke.ui.ErrorMsgWithParams.BAD_DATE_FORMAT_WITH_PARAMS;

public class AddEventCommand extends TextBasedCommand {
    public static final String COMMAND = "event";
    public static final String DISPLAY_COMMAND = COMMAND;

    /**
     * Create event command (extended from text based command).
     * @param line line of user input
     * @throws DukeException generic error with message
     */
    public AddEventCommand(String line) throws DukeException {
        super(line, COMMAND);
    }

    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] splitData = TaskWithDate.extractDataFromLine(remainingLine, " /at ");
            if (TaskWithDate.validateData(splitData, DISPLAY_COMMAND)) {
                taskList.insertNewTask(ui, new Event(splitData[0], splitData[1]));
            }
            storage.saveTaskListToFile(taskList);
        } catch (ParseException | IndexOutOfBoundsException e) {
            throw new DukeException(BAD_DATE_FORMAT_WITH_PARAMS, "Event", "event");
        }
    }
}
