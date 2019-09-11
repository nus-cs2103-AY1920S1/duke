package duke.command;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.LinkedList;

public class SortCommand extends Command {

    private String way;

    /**
     * Initialises a FindCommand.
     *
     * @param way The way to sort the list by.
     */
    public SortCommand(String way) {
        this.way = way;
    }

    /**
     * Executes the Sort Command and sorts the list by the way specified.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param storage The Storage for saving tasks to file.
     * @return The response string.
     */
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder("Sorted list by " + way + ":\n");
        LinkedList<Task> t = new LinkedList<>();
        switch (way) {
        case ("name"):
            t = tasks.sortByName();
            break;
        case ("date"):
            t = tasks.sortByDate();
            break;
        default:
            break;
        }
        t.forEach(x ->
                sb.append(tasks.getList().indexOf(x) + 1).append(". ").append(x).append("\n"));
        return sb.toString();
    }

    /**
     * Checks if it is a bye command.
     *
     * @return A boolean: true if it is a bye command.
     */
    public boolean isExit() {
        return false;
    }
}