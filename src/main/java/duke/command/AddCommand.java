package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Encapsulates a command that adds a task into the tasks list of Duke.
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
     * @param tasksList the tasks list of Duke.
     * @param ui the ui of Duke.
     * @param database the database of Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public void execute(TaskList tasksList, Ui ui, DukeDatabase database) throws DukeException {
        initialise(tasksList, ui, database);

        if (AddType.TODO.equals(addType)) {
            addToDo();
        } else if (AddType.DEADLINE.equals(addType)) {
            addDeadline();
        } else if (AddType.EVENT.equals(addType)) {
            addEvent();
        } else {
            throw new DukeException("Internal logic bug occurred!");
        }
    }

    /**
     * Adds to do entry to the taskList.
     */
    private void addToDo() throws DukeException {
        String topic = input.substring(4).trim();

        if ("".equals(topic)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        addTask(new ToDo(topic));
    }

    /**
     * Adds deadline entry to the taskList.
     */
    private void addDeadline() throws DukeException {
        String[] details = input.substring(8).trim().split("/by");

        if (details.length == 1 || "".equals(details[0].trim()) || "".equals(details[1].trim())) {
            throw new DukeException("The description and deadline of a deadline cannot be empty.");
        }

        try {
            String topic = details[0].stripTrailing();
            String deadlineUserInput = details[1].stripLeading();
            String deadline = formatDateAndTime(deadlineUserInput);

            addTask(new Deadline(topic, deadline));
        } catch (ParseException e) {
            ui.echo(DukeException.PREFIX + " The format of date and time is wrong!");
        }
    }

    /**
     * Adds event entry to the taskList.
     */
    private void addEvent() throws DukeException {
        String[] details = input.substring(5).trim().split("/at");

        if (details.length == 1 || "".equals(details[0].trim()) || "".equals(details[1].trim())) {
            throw new DukeException("The description and date of an event cannot be empty.");
        }

        try {
            String topic = details[0].stripTrailing();
            String dateUserInput = details[1].stripLeading();
            String date = formatDateAndTime(dateUserInput);

            addTask(new Event(topic, date));
        } catch (ParseException e) {
            ui.echo(DukeException.PREFIX + " The format of date and time is wrong!");
        }
    }

    /**
     * Adds Task entries(to do, deadline & event) to the taskList.
     */
    private void addTask(Task task) {
        taskList.addTask(task);
        ui.echo(() -> {
            System.out.printf("%sGot it. I've added this task:\n", Ui.INDENTATION_LVL1);
            System.out.printf(ui.indentAndSplit(task.toString(), Ui.INDENTATION_LVL2));
            System.out.printf(String.format("%sNow you have %s in the list.\n", Ui.INDENTATION_LVL1,
                    ui.getTaskPhrase(taskList.size())));
        });
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
