package trackr.command;

import trackr.exception.TrackrException;
import trackr.history.HistoryTracker;
import trackr.storage.Storage;
import trackr.tasklist.TaskList;

public class UndoCommand extends Command {

    /**
     * Undo the previous task and prints out a message informing the user which command has been undone.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks the input history by the user
     * @return String Informs user which command has been undone
     * @throws TrackrException When there are no tasks that can be undone
     */
    @Override
    public String execute(TaskList tasks, Storage storage, HistoryTracker history) throws TrackrException {
        if (!history.isEmpty()) {
            String previousInput = history.retrievePreviousInput();
            if (canBeUndone(previousInput)) {
                updateTasklistAndStorage(tasks, storage, history);
                return "Got it. I've undone the previous command:\n" + previousInput;
            } else {
                String result = "";
                // remove command that cannot be undone from stack
                history.retrieveHistory();
                // search for next available command that can be undone
                while (!history.isEmpty()) {
                    String prevInput = history.retrievePreviousInput();
                    if (canBeUndone(prevInput)) {
                        tasks.overrideTasks(history.retrieveHistory().getTasks());
                        storage.rewriteFile(tasks);
                        result = "Got it. I've undone the previous command:\n" + prevInput;
                        break;
                    } else {
                        history.retrieveHistory();
                        result = ":( OOPS!!! There are no commands to be undone.";
                    }
                }
                return result;
            }
        } else {
            throw new TrackrException(":( OOPS!!! There are no commands to be undone.");
        }
    }

    /**
     * Checks whether the user command can be undone.
     * @param input User input
     * @return boolean True if the input can be undone, false otherwise
     */
    public boolean canBeUndone(String input) {
        String command = getCommand(input);
        switch (command) {
        case "complete":
        case "c":
        case "todo":
        case "t":
        case "deadline":
        case "d":
        case "event":
        case "e":
        case "remove":
        case "r":
        case "update":
        case "u":
            return true;
        default:
            return false;
        }
    }

    /**
     * Extracts command from user input string.
     * @param input User input
     * @return String Command from user input
     */
    private static String getCommand(String input) {
        String[] inputStringArr = input.split(" ");
        return inputStringArr[0];
    }

    /**
     * Updates the list of tasks and storage data after command is undone.
     * @param tasks List of tasks
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     * @param history Tracks input history
     */
    private static void updateTasklistAndStorage(TaskList tasks, Storage storage, HistoryTracker history) {
        tasks.overrideTasks(history.retrieveHistory().getTasks());
        storage.rewriteFile(tasks);
    }
}
