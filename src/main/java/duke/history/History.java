package duke.history;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * History class to implement undo and redo features.
 */
public class History {
    private Stack<String> historyStorageStack;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for History object.
     * @param storage current storage instance.
     * @param tasks current tasks instance.
     */
    public History(Storage storage, TaskList tasks) {
        historyStorageStack = new Stack<>();
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Add a new state into the history stack.
     */
    public void addHistoryState() {
        try {
            String prev = storage.readFile();
            historyStorageStack.push(prev);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Undo previous command by setting current state to state saved on the top of the history stack.
     * @throws DukeException exception thrown if stack is empty (no previous commands) or IO error occurs.
     */
    public void undo() throws DukeException {
        try {
            String prev = historyStorageStack.pop();
            storage.save(prev);
            tasks.setList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmptyStackException e) {
            throw new DukeException("There is no previous command!");
        }
    }
}
