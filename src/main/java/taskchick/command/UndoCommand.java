package taskchick.command;

import taskchick.exception.TaskChickException;
import taskchick.storage.Storage;
import taskchick.tasklist.TaskList;

import java.io.IOException;
import java.util.Stack;

/**
 * Command to undo a previous change to the task list.
 */
public class UndoCommand extends Command {

    private static Stack<String> listVersions = new Stack<>();

    /**
     * Saves a version of the task list into the stack.
     *
     * @param listVersion List version to be saved.
     */
    public static void saveVersion(String listVersion) {
        listVersions.push(listVersion);
    }

    /**
     * Removes the most recently saved version of the list when a list version is already saved before a
     * command is executed, but the command is not valid and so there is not change to the task list.
     */
    public static void removeRecentSavedVersion() {
        listVersions.pop();
    }

    /**
     * Executes the undoing of a command.
     *
     * @param tasks Task list to be undone.
     * @param storage Storage to be updated with the older task list.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (listVersions.isEmpty()) {
            return "You have nothing to undo!";
        }
        try {
            StringBuilder sb = new StringBuilder(tasks.undoCommand(storage, listVersions.pop()));
            storage.store(tasks);
            return sb.toString();
        } catch (IOException e) {
            throw new TaskChickException("OOPS!!! " + e.getMessage());
        }
    }
}
