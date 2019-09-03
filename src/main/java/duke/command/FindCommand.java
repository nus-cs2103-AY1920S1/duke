package duke.command;

import java.util.ArrayList;

import duke.component.TaskList;
import duke.component.Storage;
import duke.task.Task;

public class FindCommand extends Command {
    private String searchString;

    /**
     *  Constructs a <code>FindCommand</code> object with a given search string.
     *  @param command raw command string that generated this <code>Command</code> object.
     *  @param searchString <code>String</code> to search for in descriptions of <code>Task</code> objects.
     */
    public FindCommand(String command, String searchString) {
        super(command);
        this.searchString = searchString;
    }
    
    /**
     *  Executes this command with the supplied <code>TaskList</code> and <code>Storage</code> objects.
     *  @param tasks associated <code>TaskList</code> object to execute the command with.
     *  @param fileMgr associated <code>Storage</code> object to execute the command with.
     *  @return a <code>String</code> containing the output of executing this command.
     */
    public String execute(TaskList tasks, Storage fileMgr) {
        ArrayList<Task> results = tasks.searchTask(this.searchString);
        int numResults = results.size();

        StringBuilder list = new StringBuilder();
        list.append("Here are the matching tasks in your list:");
        
        for (int i = 0; i < numResults; i++) {
            list.append(String.format("\n%d. ", i + 1));
            list.append(results.get(i).toString());
        }
        return list.toString();
    }
}
