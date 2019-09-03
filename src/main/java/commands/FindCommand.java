package commands;

import storage.Storage;
import util.TaskList;
import ui.Ui;
import tasks.Task;

import java.io.IOException;

/**
 * Encapsulates command for finding tasks using specified keyword(s).
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class FindCommand extends Command {
    /** The keyword(s) specified by the user. */
    private String keywords;

    /**
     * Creates a find command to search for tasks relating to given keyword(s).
     *
     * @param imperative the term used to identify command type
     * @param keywords the search term(s) given by the user
     */
    public FindCommand(String imperative, String keywords) {
        super(imperative);
        this.keywords = keywords;
    }

    /**
     * Executes the command by finding string matches between keywords and
     * descriptions of tasks stored in the task list.
     *
     * @param tasks the task list the task is to be added to
     * @param ui the user interface associated with this run of Duke
     * @param storage the storage handler associated with this run of Duke
     * @throws IOException when file the list is to be written to is not found
     * @return Duke's response to the user command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        // obtain array of keywords, size of array, and size of list
        String[] keywordArray = this.keywords.split(" ");
        int keywordArraySize = keywordArray.length;
        int taskListSize = tasks.size();

        // initialise a temporary container to hold matching tasks
        TaskList foundTasksContainer = new TaskList();
        boolean[] isTaskAlreadyFound = new boolean[taskListSize]; // false

        // find task if applicable and add to container
        for (int i = 0; i < taskListSize; i++) {
            for (int j = 0; j < keywordArraySize; j++) {
                // if task has already been found before, move on
                if (!isTaskAlreadyFound[i]) {
                    Task currentTask = tasks.get(i);
                    String currentTaskDescription = currentTask
                            .getTaskDescription().toUpperCase();
                    String currentKeyword = keywordArray[j].toUpperCase();

                    if (currentTaskDescription.contains(currentKeyword)) {
                        isTaskAlreadyFound[i] = true;
                        foundTasksContainer.add(currentTask);
                    }
                }
            }
        }

        if (foundTasksContainer.isEmpty()) {
            // inform user that task is not found
            return ui.showTaskNotFoundMessage();
        } else {
            // send container task list for printing
            return ui.printTasks(foundTasksContainer);
        }
    }
}
