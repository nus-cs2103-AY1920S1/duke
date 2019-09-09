package duke.module;

import duke.command.Undoable;
import duke.exception.DukeEmptyUndoStackException;

import java.util.ArrayDeque;
import java.util.Deque;

// TODO : add java docs
public class UndoStack {

    private static final int MAX_STORAGE = 100;

    private Deque<Undoable> commandStack;
    private int size;

    public UndoStack() {
        this.commandStack = new ArrayDeque<>();
        this.size = 0;
    }

    public void addUndoable(Undoable command) {
        if (this.size >= UndoStack.MAX_STORAGE) {
            // If max size of Undoable commands were added, remove the oldest Undoable that was added
            this.commandStack.poll();
        }
        this.size++;
        this.commandStack.offerLast(command);
    }

    public Undoable popLatest() throws DukeEmptyUndoStackException {
        if (this.size == 0) {
            throw new DukeEmptyUndoStackException(AutoResponse.ERROR_EMPTY_UNDO_STACK);
        }
        this.size--;
        return this.commandStack.pollLast();
    }

}
