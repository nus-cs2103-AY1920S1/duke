package duke.command;

import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adds a new task.
 */
public class AddCommand extends Command {
    private String[] oneLine;

    public AddCommand(String[] oneLine) {
        this.oneLine = oneLine;
    }

    /**
     * check if it is a valid time format.
     *
     * @param str The input should be checked.
     * @return true if str can be converted to a valid time format, otherwise returns false.
     */
    public static boolean isValidTime(String str) {
        try {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime localDate1 = LocalDateTime.parse(str, formatter1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {

        String description;
        String timeDate;
        String firstWord = oneLine[0];

        String[] timeDate1;
        if (firstWord.equals("todo")) {
            tasks.getTaskList().add(new Todo(oneLine[1].trim()));
        } else if (firstWord.equals("deadline")) {
            timeDate1 = oneLine[1].trim().split(" /by ");
            if (timeDate1.length == 2 && isValidTime(timeDate1[1].trim())) {
                description = timeDate1[0].trim();
                timeDate = timeDate1[1].trim();
                tasks.getTaskList().add(new Deadline(description, timeDate));
            } else {
                throw new duke.exception.NoTimeAndDateException("specific date/time for deadline is wrong");
            }
        } else {
            timeDate1 = oneLine[1].trim().split(" /at ");
            if (timeDate1.length == 2 && isValidTime(timeDate1[1].trim())) {
                description = timeDate1[0].trim();
                timeDate = timeDate1[1].trim();
                tasks.getTaskList().add(new Event(description, timeDate));
            } else {
                throw new duke.exception.NoTimeAndDateException("specific date/time for event is wrong");
            }
        }
        try {
            storage.save(tasks);
        } catch (Exception e) {
            System.out.println();
            Ui.printOutput(" duke.txt not exist");
        }

        return String.format(
                Ui.frontSpace + " Got it. I've added this task: \n" + "%s\n" + Ui.frontSpace + " Now you have "
                        + tasks.getTaskList().size() + " tasks in the list.\n",
                Ui.frontSpace + "   " + tasks.getTaskList().get(tasks.size() - 1));

    }
}