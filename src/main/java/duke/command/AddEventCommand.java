package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.exception.EmptyDescriptionException;
import duke.task.Event;
import duke.task.Task;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static duke.util.Parser.parseDate;

/** The AddEventCommandClass represents a command used to add events into the current list. */
public class AddEventCommand extends AddCommand {
    private String description;
    private String duration;

    public AddEventCommand(String description, String duration) {
        this.description = description;
        this.duration = duration;
    }

    /**
     * Adds an event into task list and saves the task list
     *  into the external file.
     *
     * @param tasks Current list of tasks
     * @param ui UI being used
     * @param storage Current storage in use
     * @throws IOException Error with accessing file or file writer
     * @throws EmptyDescriptionException Error when no description is provided
     * @throws ParseException Error when incorrect format for date is used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws IOException, EmptyDescriptionException, ParseException {
        Date start = parseDate(duration.split("-")[0].trim());
        Date end = parseDate(duration.split("-")[1].trim());

        Task task = new Event(description, start, end);
        tasks.add(task);
        ui.showTaskAddedMessage(task, tasks);

        storage.saveTaskList(tasks);
    }

    /**
     * Tests Exit Command.
     *
     * @return whether Command is an Exit Command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
