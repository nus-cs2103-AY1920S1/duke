package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.VocabularyList;
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
        DukeException.checkValidity(taskDetail.length < 2,
                "Please provide a description for the task.");
        this.task = createTask(taskDetail[0], taskDetail[1]);
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
        Task newTask;
        assert (type.equals("todo") || type.equals("deadline") || type.equals("event")) : "Invalid task type";
        switch (type) {
        case "todo":
            newTask = new Todo(detail);
            break;
        case "deadline":
            try {
                String[] deadlineContent = detail.split(" /by ", 2);
                DukeException.checkValidity(deadlineContent.length < 2,
                        "Invalid format for Deadline Task.");
                String[] datetime = deadlineContent[1].split(" ", 2);
                DukeException.checkValidity(datetime.length < 2,
                        "Invalid format for Deadline Task.");

                LocalDate date = LocalDate.parse(datetime[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalTime time = LocalTime.parse(datetime[1], DateTimeFormatter.ofPattern("HHmm"));
                newTask = new Deadline(deadlineContent[0], date, time);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid Date Time format input. Should be in the format dd/MM/yyyy HHmm");
            }
            break;
        case "event":
            String[] eventContent = detail.split(" /at ", 2);
            DukeException.checkValidity(eventContent.length < 2,
                    "Invalid format for Event Task.");
            newTask = new Event(eventContent[0], eventContent[1]);
            break;
        default:
            newTask = null;
        }

        return newTask;
    }

    @Override
    public String getResponse(TaskList tasklist, Ui ui,
                              Storage storage, VocabularyList vocabularyList) throws DukeException {
        tasklist.addTask(task);
        storage.updateData(tasklist);
        return ui.generateResponse("Got it. I've added this task:",
                format("  %s", task),
                format("Now you have %d tasks in the list.", tasklist.getTaskSize()));
    }
}
