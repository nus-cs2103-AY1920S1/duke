package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Class representing user's command to Find tasks via a keyword.
 */
public class FindCommand extends Command {
    private String toFind;

    /**
     * Class constructor specifying keyword to search by.
     *
     * @param toFind Keyword to find.
     */
    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    /**
     * Returns false as is not exit command.
     *
     * @return False as is not exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the find command.
     *
     * @param taskList TaskList of Tasks to find keyword in.
     * @param ui Ui to print matching Tasks.
     * @param storage Storage to modify.
     */
    public Response execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            String[] descriptionTokens = currTask.getDescription().split(" ");
            boolean isMatching = false;
            for (int j = 0; j < descriptionTokens.length; j++) {
                if (descriptionTokens[j].strip().equalsIgnoreCase(toFind)) {
                    isMatching = true;
                    break;
                }
            }
            if (isMatching) {
                matchingTasks.addTask(currTask);
            }
        }
        if (matchingTasks.size() != 0) {
            return ui.getMatchingResponse(matchingTasks);
        } else {
            return ui.getNoMatchResponse();
        }

    }
}
