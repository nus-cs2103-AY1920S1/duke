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
    private String keywords;

    public FindCommand(String imperative, String keywords) {
        super(imperative);
        this.keywords = keywords;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] keywordArray = this.keywords.split(" ");
        int keywordArraySize = keywordArray.length;
        int taskListSize = tasks.size();

        TaskList foundTasksContainer = new TaskList();
        boolean[] isTaskAlreadyFound = new boolean[taskListSize]; // false

        // find task if applicable and add to container
        for (int i = 0; i < taskListSize; i ++) {
            for (int j = 0; j < keywordArraySize; j++) {
                if (!isTaskAlreadyFound[i]) {
                    Task currentTask = tasks.get(i);
                    String currentTaskDescription = currentTask.getTaskDescription().
                            toUpperCase();
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
            ui.showTaskNotFoundMessage();
        } else {
            // send container task list for printing
            ui.printTasks(foundTasksContainer);
        }
    }
}
