package duke.module;

import duke.command.undoable.Undoable;
import duke.exception.DukeEmptyCommandStackException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Keeps track of the most recent 100 commands inputted by the user to possibly undo.
 */
public class CommandStack {

    private static final int MAX_STORAGE = 100;

    private Deque<Undoable> undoStack;
    private Stack<Undoable> redoStack;
    private int undoStackSize;
    private int redoStackSize;
    private boolean wasRedoPopped;

    /**
     * Initializes this {@code CommandStack}.
     */
    public CommandStack() {
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new Stack<>();
        this.undoStackSize = 0;
        this.redoStackSize = 0;
        this.wasRedoPopped = false;
    }

    /**
     * Adds an {@link Undoable} command to undo.
     *
     * @param command An {@code Undoable} command to be added.
     */
    public void addUndo(Undoable command) {
        if (this.undoStackSize >= CommandStack.MAX_STORAGE) {
            // If max size of Undoable commands were added, remove the oldest Undoable that was added
            this.undoStack.poll();
        }
        this.undoStackSize++;
        this.undoStack.offerLast(command);

        // If a new command was added, clear redo stack
        if (!this.wasRedoPopped) {
            this.redoStack.clear();
            this.redoStackSize = 0;
        }
        this.wasRedoPopped = false;
    }

    /**
     * Adds an {@link Undoable} command to redo.
     *
     * @param command An {@code Undoable} command to be added.
     */
    public void addRedo(Undoable command) {
        this.redoStackSize++;
        this.redoStack.push(command);
    }

    /**
     * Removes and returns the latest command inputted by the user.
     *
     * @return The latest command inputted by the user.
     * @throws DukeEmptyCommandStackException If this CommandStack is empty.
     */
    public Undoable popUndo() throws DukeEmptyCommandStackException {
        if (this.undoStackSize == 0) {
            throw new DukeEmptyCommandStackException(AutoResponse.ERROR_EMPTY_UNDO_STACK);
        }
        this.undoStackSize--;
        return this.undoStack.pollLast();
    }

    /**
     * Removes and returns the latest undone command.
     *
     * @return The latest undone command.
     * @throws DukeEmptyCommandStackException If this CommandStack is empty.
     */
    public Undoable popRedo() throws DukeEmptyCommandStackException {
        if (this.redoStackSize == 0) {
            throw new DukeEmptyCommandStackException(AutoResponse.ERROR_EMPTY_REDO_STACK);
        }
        this.wasRedoPopped = true;
        this.redoStackSize--;
        return this.redoStack.pop();
    }

}
