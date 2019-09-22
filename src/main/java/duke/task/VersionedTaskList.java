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

    public VersionedTaskList() {
        this(new ArrayList<Task>());
    }

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

    public void commit() {
        removeStatesAfterCurrentPointer();
        states.add(new TaskList(getTasks()));
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        states.subList(currentStatePointer + 1, states.size()).clear();
    }

    public void undo() throws DukeException {
        if (!canUndo()) {
            throw new DukeException(MESSAGE_UNDO_FAILED);
        }
        this.currentStatePointer--;
        setTasks(states.get(currentStatePointer).getTasks());

    }

    public void redo() throws DukeException {
        if (!canRedo()) {
            throw new DukeException(MESSAGE_REDO_FAILED);
        }
        this.currentStatePointer++;
        setTasks(states.get(currentStatePointer).getTasks());
    }
}
