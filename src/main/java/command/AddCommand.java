package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.String.format;

/**
 * Command to add task into data.
 *
 */
public class AddCommand extends Command {
    Task task;

    /**
     * Command to add task into data.
     *
     * @param taskDetail array used to store type of task and the instruction.
     * @throws DukeException If Description is empty.
     */
    public AddCommand(String[] taskDetail) throws DukeException {
        if (taskDetail.length < 2) {
            throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
        }
        this.task = createTask(taskDetail[0], taskDetail[1]);
        this.setExit(false);
    }

    /**
     * Creates Task of specific runtime type.
     *
     * @param type The type of task.
     * @param detail Details of Task.
     * @return The Task to be added.
     * @throws DukeException For Invalid user input.
     */
    private Task createTask(String type, String detail) throws DukeException {
        Task t;
        switch (type) {
        case "todo":
            t = new Todo(detail);
            break;
        case "deadline":
            try {
                String[] deadlineContent = detail.split(" /by ", 2);
                if (deadlineContent.length < 2) {
                    throw new DukeException("Invalid format for Deadline Task.");
                }
                String[] datetime = deadlineContent[1].split(" ", 2);
                if (datetime.length < 2) {
                    throw new DukeException("Invalid format for Deadline Task.");
                }

                LocalDate date = LocalDate.parse(datetime[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalTime time = LocalTime.parse(datetime[1], DateTimeFormatter.ofPattern("HHmm"));
                t = new Deadline(deadlineContent[0], date, time);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid Date Time format input");
            }
            break;
        case "event":
            String[] eventContent = detail.split(" /at ", 2);
            if (eventContent.length < 2) {
                throw new DukeException("Invalid format for Event Task.");
            }
            t = new Event(eventContent[0], eventContent[1]);
            break;
        default:
            t = null;
        }

        return t;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        tasklist.addTask(task);
        ui.printStatement("Got it. I've added this task:",
                format("  %s", task),
                format("Now you have %d tasks in the list.", tasklist.getTaskSize()));
        storage.updateData(tasklist);
    }
}
