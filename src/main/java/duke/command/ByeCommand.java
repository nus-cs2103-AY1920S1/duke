package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The command used to exit the Duke Program.
 */
public class ByeCommand extends BasicCommand {
    public ByeCommand(String line) throws DukeException {
        super(line);
        validate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate() throws DukeException {
        String[] inputs = getInputs();

        // Throw a DukeException if there are wrong number of arguments for the command
        checkIfCorrectNumberOfArguments(inputs,0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] execute(TaskList tasks, Storage storage) {
        assert tasks != null : "tasks cannot be null";
        assert storage != null : "storage cannot be null";

        new Thread(() -> {
            try {
                Thread.sleep(500);
                System.exit(0);
            } catch (InterruptedException e) {
                System.exit(0);
            }
        }).start();

        // Return a message before closing Duke
        return new String[] { "Bye. Hope to see you again soon!" };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
