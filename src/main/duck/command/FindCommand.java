package duck.command;

import duck.task.Task;
import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;

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
     * @param taskList the task list that provides information about users' current tasks and to be modified
     * @param ui       the <code>Ui</code> object to handle input and output
     * @param storage  the <code>Storage</code> object to load and record data
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList resultList = new TaskList();

        for (int i = 0; i < taskList.getTotalTask(); i++) {
            Task task = taskList.getTaskAt(i);
            if (task.getDescription().contains(keyWord)) {
                resultList.add(task);
            }
        }

        ui.showFullList(resultList);
    }
}

