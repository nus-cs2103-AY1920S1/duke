package duke.command;


import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.TaskWithDate;

import java.text.ParseException;

public class AddDeadlineCommand extends TextBasedCommand {
    public static final String COMMAND = "deadline";
    public static final String DISPLAY_COMMAND = COMMAND;

    /**
     * Add a deadline command (extended from text based command).
     * @param line line of input
     * @throws DukeException generic error with message
     */
    public AddDeadlineCommand(String line) throws DukeException {
        super(line, COMMAND);
    }

    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] splitData = TaskWithDate.extractDataFromLine(remainingLine, " /by ");
            if (TaskWithDate.validateData(splitData, DISPLAY_COMMAND)) {
                taskList.insertNewTask(ui, new Event(splitData[0], splitData[1]));
            }
            storage.saveTaskListToFile(taskList);
        } catch (ParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Deadline is ill formatted. Example: deadline return book /by 2/12/2019 1800");
        }
    }
}
