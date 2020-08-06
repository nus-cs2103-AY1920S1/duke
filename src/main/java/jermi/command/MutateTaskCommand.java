package jermi.command;

import jermi.exception.InvalidIndexException;
import jermi.exception.JermiException;

/**
 * A representation of the command that mutates the state of the task.
 */
abstract class MutateTaskCommand extends Command {
    /** Index of the task to be mutated. */
    protected int index;

    /**
     * Public constructor for class.
     *
     * @param index Index of the task to be mutated.
     * @throws JermiException {@link InvalidIndexException}.
     */
    MutateTaskCommand(String index) throws JermiException {
        super();

        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Indicates if the program should exit.
     *
     * @return {@code false}.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
