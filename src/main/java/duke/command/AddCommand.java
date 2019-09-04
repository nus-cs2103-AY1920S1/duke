package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Encapsulates a command that adds a task into the tasks list of duke.Duke.
 */
public class AddCommand extends Command {
    private AddType addType;

    /**
     * Represents the type of add command.
     * TODO: to add a ToDo Task, DEADLINE: to add a Deadline Task, EVENT: to add an Event task.
     */
    public enum AddType {
        TODO, DEADLINE, EVENT;
    }

    /**
     * Constructs an AddCommand object.
     *
     * @param addType the type of adding command.
     * @param input the user's input.
     */
    public AddCommand(AddType addType, String input) {
        super(input);
        this.addType = addType;
    }

    /**
     * Executes the adding command accordingly.
     *
     * @param tasksList the tasks list of duke.Duke.
     * @param database the database of duke.Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public String execute(DukeDatabase database, TaskList tasksList) throws DukeException {
        initialise(database, tasksList);

        Task task;
        if (AddType.TODO.equals(addType)) {
            task = addToDo();
        } else if (AddType.DEADLINE.equals(addType)) {
            task = addDeadline();
        } else if (AddType.EVENT.equals(addType)) {
            task = addEvent();
        } else {
            throw new DukeException("Internal logic bug occurred!");
        }

        String response = String.format("Got it I've added this task:\n%s\nNow you have %s in the list.\n",
                task.toString(), getTaskPhrase(taskList.size()));

        return response;
    }

    /**
     * Adds to do entry to the taskList.
     */
    private ToDo addToDo() throws DukeException {
        String topic = input.substring(4).trim();

        if ("".equals(topic)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        ToDo toDo = new ToDo(topic);
        taskList.addTask(toDo);

        return toDo;
    }

    /**
     * Adds deadline entry to the taskList.
     */
    private Deadline addDeadline() throws DukeException {
        String[] details = input.substring(8).trim().split("/by");

        if (details.length <= 1 || "".equals(details[0].trim()) || "".equals(details[1].trim())) {
            throw new DukeException("The description and deadline of a deadline cannot be empty.");
        }

        try {
            String topic = details[0].stripTrailing();
            String deadlineUserInput = details[1].stripLeading();
            String deadline = formatDateAndTime(deadlineUserInput);

            Deadline deadlineObject = new Deadline(topic, deadline);
            taskList.addTask(deadlineObject);

            return deadlineObject;
        } catch (ParseException e) {
            throw new DukeException("The format of date and time is wrong!");
        }
    }

    /**
     * Adds event entry to the taskList.
     */
    private Event addEvent() throws DukeException {
        String[] details = input.substring(5).trim().split("/at");

        if (details.length <= 1 || "".equals(details[0].trim()) || "".equals(details[1].trim())) {
            throw new DukeException("The description and date of an event cannot be empty.");
        }

        try {
            String topic = details[0].stripTrailing();
            String dateUserInput = details[1].stripLeading();
            String date = formatDateAndTime(dateUserInput);

            Event event = new Event(topic, date);
            taskList.addTask(event);

            return event;
        } catch (ParseException e) {
            throw new DukeException("The format of date and time is wrong!");
        }
    }

    /**
     * Formats the date time given by the user.
     *
     * @param dateTime date and time given by user in format: dd/MM/yyyy HHmm (24 Hours format).
     * @return formatted date and time in format: d Month yyyy, h:mm (12 Hours format).
     * @throws ParseException if the format of date and time given by user is incorrect.
     */
    private String formatDateAndTime(String dateTime) throws ParseException {
        DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        DateFormat outputFormatter = new SimpleDateFormat("d MMMM yyyy, h:mm a");
        Date date = inputFormatter.parse(dateTime);

        return outputFormatter.format(date);
    }
}
