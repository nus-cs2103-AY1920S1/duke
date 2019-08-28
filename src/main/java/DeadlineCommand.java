package duke.command;

import duke.exception.DukeException;
import duke.component.TaskList;
import duke.component.Ui;
import duke.database.Storage;
import duke.task.Deadline;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadlineCommand extends Command {
    /**
     * Constructor of DeadlineCommand class.
     * @param input Task input by user.
     * @param type Type of task.
     */
    public DeadlineCommand(String input, String type) {
        super(input, type);
    }

    /**
     * Execute method to execute deadline command.
     * @param tasks List of task.
     * @param ui The ui of duke program.
     * @param storage The Database of duke program.
     * @throws DukeException If deadline command fail to be executed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        deadline(super.input, tasks, ui);
    }

    /**
     * "deadline" command to enter deadline description and deadline time.
     * @param data deadline description and deadline of task.
     * @throws DukeException If description and time of deadline is empty.
     */
    public static void deadline(String data, TaskList tasks, Ui ui) throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException(ui.INDENT_COMMENT +"\u2639 OOPS !!! " + "The description of a deadline cannot be empty.");
        }

        String[] result = data.split("/by");

        if (result.length <= 1) {
            throw new DukeException(ui.INDENT_COMMENT +"\u2639 OOPS !!! " + "Deadline is needed.");
        }

        String achieve = result[0].trim();
        String timeline = result[1].trim();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
        DateFormat outputformat = new SimpleDateFormat("d MMMM yyyy',' h:mm a");
        Date date = null;
        String formatted_Date = null;
        try {
            date= df.parse(timeline);
            formatted_Date = outputformat.format(date);
            tasks.getTask().add(new Deadline(achieve, formatted_Date));
            System.out.println(ui.INDENT_COMMENT + "Got it. I've added this task: ");
            System.out.println(ui.INDENT_TASK + tasks.getTask().get(tasks.getItemNo()));
            tasks.setItemNo(tasks.getItemNo() + 1);
            System.out.println(ui.INDENT_COMMENT + "Now you have " + tasks.getItemNo() + " tasks in the list.");
        } catch (ParseException pe) {
            System.out.println(ui.INDENT_COMMENT + "Invalid Data and Time Format");
        }
    }
}
