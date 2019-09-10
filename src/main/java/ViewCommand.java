import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

/**
 * Represent user command to find tasks using keyword provided.
 */

public class ViewCommand extends Command {

    protected LocalDate date;
    private TaskList tasks;
    private static String DATE_FORMAT_FOR_VIEW = "d/M/yyyy";

    /**
     * Represents an action to find tasks of a specific date.
     *
     * @param command Type of task
     * @param date    Date and time.
     */
    public ViewCommand(String command, String date) {
        super(command);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_VIEW));
    }

    /**
     * Finds all tasks for a specific date.
     *
     * @param tasks   List of tasks.
     * @param ui      User Interface.
     * @param storage Storage of tasks.txt files.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ArrayList<Task> filteredTasks = tasks.getTaskArr()
                                                .stream()
                                                .filter(this::hasSameDate)
                                                .collect(Collectors.toCollection(ArrayList<Task>::new));
        if (filteredTasks.size() == 0) {
            throw new DukeException("There is no tasks on " + date + ".");
        }
        this.tasks = new TaskList(filteredTasks);
    }

    private boolean hasSameDate(Task task) {
        if (!(task instanceof TaskWithDateTime)) {
            return false;
        }

        TaskWithDateTime taskWithDateTime = (TaskWithDateTime) task;
        LocalDate dateOfTask = taskWithDateTime.getLocalDateTime().toLocalDate();

        return dateOfTask.isEqual(date);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder printStr = new StringBuilder();
        printStr.append("Schedule for " + date + ":\n");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            if (i == tasks.getTaskCount() - 1) {
                printStr.append((i + 1) + ". " + tasks.getTask(i));
                break;
            }
            printStr.append((i + 1) + ". " + tasks.getTask(i) + "\n");
        }
        return printStr.toString();
    }
}
