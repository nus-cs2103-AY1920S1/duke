package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.exception.EmptyDescriptionException;
import duke.task.Deadline;
import duke.task.Task;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static duke.util.Parser.parseDate;

/** Represents command to add a deadline task into task list which has yet to be executed. */
public class AddDeadlineCommand extends AddCommand {
    private String description;
    private String date;

    public AddDeadlineCommand(String description, String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Adds a deadline into the task list and saves the task list
     *  into the external file.
     *
     * @param tasks Current {@link TaskList}
     * @param ui {@link Ui} being used
     * @param storage Current {@link Storage} in use
     * @throws IOException Error with accessing file or {@link java.io.FileWriter}
     * @throws EmptyDescriptionException Error when no description is provided
     * @throws ParseException Error when incorrect format for {@link Date} is used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws IOException, EmptyDescriptionException, ParseException {
        Date deadlineDate = parseDate(date);
        Task task = new Deadline(description, deadlineDate);
        tasks.add(task);
        ui.showTaskAddedMessage(task, tasks);
        storage.saveTaskList(tasks);
    }

    /**
     * Test Exit Command.
     *
     * @return Whether command is an Exit Command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
