package duke.command;

import duke.exception.DukeException;
import duke.exception.NoTimeAndDateException;
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
        assert oneLine != null;
        this.oneLine = oneLine;
    }

    /**
     * check if it is a valid time format.
     *
     * @param str The input should be checked.
     * @return true if str can be converted to a valid time format, otherwise returns false.
     */
    public static boolean isValidTime(String str) {
        LocalDateTime pattern;
        assert str != null;

        try {
            pattern = LocalDateTime.parse(str.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        String firstWord = oneLine[0];

        if (firstWord.equals("todo")) {
            addTodo(tasks);
        } else if (firstWord.equals("deadline")) {
            addDeadline(tasks);
        } else {
            addEvent(tasks);
        }
        update(tasks, storage);
        return String.format(
                Ui.frontSpace + " Got it. I've added this task: \n" + "%s\n" + Ui.frontSpace + " Now you have "
                        + tasks.getTaskList().size() + " tasks in the list.\n",
                Ui.frontSpace + "   " + tasks.getTaskList().get(tasks.size() - 1));

    }

    private void addTodo(TaskList tasks) {
        tasks.addTask(new Todo(oneLine[1].trim()));
    }

    private void addEvent(TaskList tasks) throws DukeException {
        String[] timeDate1;
        String description;
        String timeDate;
        timeDate1 = oneLine[1].trim().split("/at");
        if (timeDate1.length == 2 && isValidTime(timeDate1[1].trim())) {
            description = timeDate1[0].trim();
            timeDate = timeDate1[1].trim();
            tasks.addTask(new Event(description, timeDate));
        } else {
            if (timeDate1.length == 1 && isValidTime(timeDate1[0].trim())) {
                throw new DukeException("No task name for event");
            } else {
                if (timeDate1[0].trim().equals("")) {
                    throw new DukeException("No task name for event");
                } else {
                    throw new NoTimeAndDateException("specific date/time for event is wrong");
                }
            }
        }
    }

    private void addDeadline(TaskList tasks) throws DukeException {
        String[] timeDate1;
        String description;
        String timeDate;
        timeDate1 = oneLine[1].trim().split("/by");

        if (timeDate1.length == 2 && !timeDate1[0].trim().equals("") && isValidTime(timeDate1[1].trim())) {
            description = timeDate1[0].trim();
            timeDate = timeDate1[1].trim();
            tasks.addTask(new Deadline(description, timeDate));
        } else {
            if (timeDate1[0].trim().equals("")) {
                throw new DukeException("No task name for deadline");
            } else {
                throw new NoTimeAndDateException("specific date/time for deadline is wrong");
            }
        }
    }
}