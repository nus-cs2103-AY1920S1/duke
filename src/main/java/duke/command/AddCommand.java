package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.UI;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCommand extends Command {
    private AddType addType;

    public enum AddType {
        TODO, DEADLINE, EVENT;
    }

    public AddCommand(AddType addType, String input) {
        super(input);
        this.addType = addType;
    }

    public void execute(TaskList tasks, UI ui, DukeDatabase database) throws DukeException {
        initialise(tasks, ui, database);

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

    // add to do entry to the taskList.
    private void addToDo() throws DukeException {
        String topic = input.substring(4).trim();

        if ("".equals(topic)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        addTask(new ToDo(topic));
    }

    // add deadline entry to the taskList.
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

    // add event entry to the taskList.
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

    // add Task entries(to do, deadline & event) to the taskList.
    private void addTask(Task task) {
        taskList.addTask(task);
        ui.echo(() -> {
            System.out.printf("%sGot it. I've added this task:\n", UI.INDENTATION_LVL1);
            System.out.printf(ui.indentAndSplit(task.toString(), UI.INDENTATION_LVL2));
            System.out.printf(String.format("%sNow you have %s in the list.\n", UI.INDENTATION_LVL1,
                    ui.getTaskPhrase(taskList.size())));
        });
    }

    private String formatDateAndTime(String dateTime) throws ParseException {
        DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        DateFormat outputFormatter = new SimpleDateFormat("d MMMM yyyy, h:mm a");
        Date date = inputFormatter.parse(dateTime);

        return outputFormatter.format(date);
    }
}
