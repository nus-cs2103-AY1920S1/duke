package duke.util;

import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Keeps a history of commands executed during the current session.
 */
public class History {

    private List<String> commandHistory;
    private List<TaskList> taskListHistory;
    private int currHistoryIndex;

    /**
     * Initializes the task list history and sets the history index.
     * @param taskList The first entry in the task list history.
     */
    public History(TaskList taskList) {
        commandHistory = new ArrayList<String>();
        taskListHistory = new ArrayList<TaskList>();
        taskListHistory.add(taskList);
        currHistoryIndex = 0;
    }

    /**
     * Adds a new task list to the history of task lists for this session.
     * @param updatedList The new task list to add.
     */
    public void add(String command, TaskList updatedList) {
        if (currHistoryIndex < (taskListHistory.size() - 1)) {
            taskListHistory = taskListHistory.subList(0, currHistoryIndex + 1);
            commandHistory = commandHistory.subList(0, currHistoryIndex);
        }

        commandHistory.add(command);
        taskListHistory.add(updatedList.copy());
        currHistoryIndex++;
    }

    /**
     * Reverts the current task list to the task list before the previous command.
     * @param taskList The TaskList to revert.
     * @return The undone command as a String.
     * @throws DukeException If undoing fails.
     */
    public String undo(TaskList taskList) throws DukeException {
        if (currHistoryIndex == 0) {
            throw new DukeException("No previous action to undo.");
        }

        taskList.clear();

        currHistoryIndex--;
        TaskList olderTaskList = taskListHistory.get(currHistoryIndex);
        for (int i = 1; i <= olderTaskList.size(); i++) {
            taskList.add(olderTaskList.get(i));
        }

        return commandHistory.get(currHistoryIndex);
    }

    /**
     * Advances the current task list to the task list after the next newer command.
     * @param taskList The TaskList to advance.
     * @return The redone command as a String.
     * @throws DukeException If redoing fails.
     */
    public String redo(TaskList taskList) throws DukeException {
        if (currHistoryIndex == (taskListHistory.size() - 1)) {
            throw new DukeException("No newer action to redo to.");
        }

        taskList.clear();

        currHistoryIndex++;
        TaskList newerTaskList = taskListHistory.get(currHistoryIndex);
        for (int i = 1; i <= newerTaskList.size(); i++) {
            taskList.add(newerTaskList.get(i));
        }

        return commandHistory.get(currHistoryIndex - 1);
    }

    /**
     * Returns the history of commands in this session, with a marker to indicate current position.
     * @return The history of commands as a String.
     */
    @Override
    public String toString() {
        if (commandHistory.isEmpty()) {
            return "--none--";
        }

        StringBuilder historyString = new StringBuilder();
        boolean isAtCurrPosition = false;

        for (int i = 0; i < commandHistory.size(); i++) {
            // mark current position in history
            if (!isAtCurrPosition && i == currHistoryIndex) {
                historyString.append("-- you are here --").append("\n");
                isAtCurrPosition = true;
            }
            historyString.append(commandHistory.get(i)).append("\n");
        }

        // mark current position in history if still unmarked
        if (!isAtCurrPosition) {
            historyString.append("-- you are here --").append("\n");
        }

        return historyString.toString();
    }
}
