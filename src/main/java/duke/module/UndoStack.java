package duke.module;

import duke.command.undoable.Undoable;
import duke.exception.DukeEmptyUndoStackException;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Keeps track of the most recent 100 commands inputted by the user to possibly undo.
 */
public class UndoStack {

    private static final int MAX_STORAGE = 100;

    private Deque<Undoable> commandStack;
    private int size;

    /**
     * Initializes this {@code UndoStack}.
     */
    public UndoStack() {
        this.commandStack = new ArrayDeque<>();
        this.size = 0;
    }

    /**
     * Adds an {@link Undoable} command.
     *
     * @param command An {@code Undoable} command to be added.
     */
    public void addUndoable(Undoable command) {
        if (this.size >= UndoStack.MAX_STORAGE) {
            // If max size of Undoable commands were added, remove the oldest Undoable that was added
            this.commandStack.poll();
        }
        this.size++;
        this.commandStack.offerLast(command);
    }

    /**
     * Removes and returns the latest command inputted by the user.
     *
     * @return The latest command inputted by the user.
     * @throws DukeEmptyUndoStackException If this UndoStack is empty.
     */
    public Undoable popLatest() throws DukeEmptyUndoStackException {
        if (this.size == 0) {
            throw new DukeEmptyUndoStackException(AutoResponse.ERROR_EMPTY_UNDO_STACK);
        }
        this.size--;
        return this.commandStack.pollLast();
    }

}
