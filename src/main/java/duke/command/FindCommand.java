package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is a <code>Command</code> to list all related tasks by a searching key word.
 */
public class FindCommand extends Command {

    String keyWord;

    /**
     * This is a class constructor specifying a searching keywrod.
     *
     * @param keyWord a string to be searched among all tasks' descriptions
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Lists all the tasks if their descriptions contain the searching keyword. If there is no matching task, a message
     * will tell the user there is no task to show.
     *
     * @param taskList {@inheritDoc}
     * @param ui       {@inheritDoc}
     * @param storage  {@inheritDoc}
     * @return a string showing all tasks in the task list filtered by <code>keyword</code>
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList resultList = new TaskList();

        for (int i = 0; i < taskList.getTotalTask(); i++) {
            Task task = taskList.getTaskAt(i);
            if (task.getDescription().contains(keyWord)) {
                resultList.add(task);
            }
        }

        return ui.showFullList(resultList);
    }
}

