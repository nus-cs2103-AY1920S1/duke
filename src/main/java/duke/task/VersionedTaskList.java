package duke.task;

import duke.exception.DukeException;

import static duke.ui.Message.MESSAGE_REDO_FAILED;
import static duke.ui.Message.MESSAGE_UNDO_FAILED;

import java.util.ArrayList;
import java.util.List;

/**
 * Task list that keeps track of its own history.
 */
public class VersionedTaskList extends TaskList {
    private List<TaskList> states;
    private int currentStatePointer;

    /**
     * Constructs an empty versioned list of tasks.
     */
    public VersionedTaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Constructs a versioned task list containing the elements of the specified ArrayList of Tasks,
     * in the order they are returned by the list's iterator.
     *
     * @param initialState the ArrayList of Tasks whose tasks are to be placed into this task list in the
     *                     initial state.
     */
    public VersionedTaskList(ArrayList<Task> initialState) {
        super(initialState);

        this.states = new ArrayList<>();
        this.states.add(new TaskList(initialState));
        currentStatePointer = 0;
    }

    @Override
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    @Override
    public boolean canRedo() {
        return currentStatePointer < states.size() - 1;
    }

    /**
     * Keeps a deep copy of the current state of the task list.
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        states.add(new TaskList(getTasks()));
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        states.subList(currentStatePointer + 1, states.size()).clear();
    }

    /**
     * Undo the previous command and restore the task list to the previous state.
     *
     * @throws DukeException if no commands to undo
     */
    public void undo() throws DukeException {
        if (!canUndo()) {
            throw new DukeException(MESSAGE_UNDO_FAILED);
        }
        this.currentStatePointer--;
        setTasks(states.get(currentStatePointer).getTasks());

    }

    /**
     * Redo the previous undo command.
     *
     * @throws DukeException if no commands to redo
     */
    public void redo() throws DukeException {
        if (!canRedo()) {
            throw new DukeException(MESSAGE_REDO_FAILED);
        }
        this.currentStatePointer++;
        setTasks(states.get(currentStatePointer).getTasks());
    }
}
