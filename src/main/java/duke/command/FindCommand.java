package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find a task.
 */
public class FindCommand extends Command {
    /** Term to be searched within the TaskList */
    private String searchTerm;

    /**
     * Constructor for FindCommand.
     *
     * @param searchTerm The term to be searched.
     */
    public FindCommand(String searchTerm){
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the command to search for a keyword.
     * It gets the arrayList<Task> from within the TaskList to be able to search,
     * then it loops through each task and searches if the description contains the keyword to be searched.
     * It then creates a new TaskList from all the matches, and then calls listAllTasks with the provided ui.
     *
     * @param tasks The TaskList to be searched
     * @param ui The ui to message the user
     * @param storage Storage if needed
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        ArrayList<Task> taskList = tasks.getToDoList();
            ArrayList<Task> matches = new ArrayList<>();
            for(Task task : taskList){
                if(task.getTaskDescription().contains(this.searchTerm)){
                    matches.add(task);
                }
            }
        new TaskList(matches).listAllTasks(ui);
    }
}
