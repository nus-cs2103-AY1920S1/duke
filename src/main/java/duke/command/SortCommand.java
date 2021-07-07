package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Comparator;

public class SortCommand extends Command {

    public static final String name = "sort";

    public SortCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        if (fullCommand.split(" ").length < 2) {
            throw new DukeException("Sort commands requires a second argument");
        }
        String sortType = fullCommand.split(" ")[1];
        switch (sortType) {
        case "type":
            sortByType(tasks);
            return "Sorted by type";
        case "description":
            sortByDescription(tasks);
            return "Sorted by description";
        default:
            throw new DukeException("Sort command requires a valid argument");
        }
    }

    private void sortByDescription(TaskList tasks) {
        tasks.getTasks().sort(Comparator.comparing(Task::getDescription));
    }

    private void sortByType(TaskList tasks) {
        tasks.getTasks().sort((t1, t2) -> {
            try {
                return getTaskSymbol(t1) - getTaskSymbol(t2);
            } catch (DukeException e) {
                e.printStackTrace();
                return 0;
            }
        });
    }

    private char getTaskSymbol(Task task) throws DukeException {
        if (task instanceof Deadline) {
            return Deadline.SYMBOL;
        } else if (task instanceof Event) {
            return Event.SYMBOL;
        } else if (task instanceof Todo) {
            return Todo.SYMBOL;
        } else {
            throw new DukeException("INVALID TASK SYMBOL FOUND IN SORT");
        }
    }
}
