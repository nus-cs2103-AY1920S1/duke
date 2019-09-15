package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a <code>FindTagCommand</code>.
 * A< <code>FindTagCommand</code> corresponds to a command searching for all the tasks that are labelled with this tag.
 */
public class FindTagCommand extends Command {
    protected String tag;

    /**
     * Constructor for <code>FindTagCommand</code>.
     * @param tag Input tag that is being searched for.
     */
    public FindTagCommand(String tag) {
        this.tag = tag;
    }

    /**
     * Iterates through all the tasks and finds those with matching tags.
     * @param tasks Instance of <code>TaskList</code> which stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> which handles user input and outputs.
     * @param storage Instance of <code>Storage</code> which stores and loads information to and from the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> actions = tasks.getList();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < actions.size(); i++) {
            Task task = actions.get(i);
            String tags = task.getTagsAsStrings();
            if (tags.contains(tag)) {
                matchingTasks.add(task);
            }
        }
        ui.printTasksMatchingTag(tag, matchingTasks);
    }
}
