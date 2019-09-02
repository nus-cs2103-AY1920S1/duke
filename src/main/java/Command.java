import java.io.IOException;

import java.util.ArrayList;

/**
 * Contains information on a user input
 * Represents a command. A <code>Command</code>
 * corresponds to a main command word (e.g. <code>list</code>, <code>deadline</code> etc.)
 * and details required for it to be execute (e.g. description of event, index of task to be
 * deleted from list, details of subcommands like <code>/by</code>)
 */

public class Command {
    protected String command; // list, done, bye, todo, deadline, event
    protected boolean isFinished; // Whether command terminates program or not

    public Command(String commandWord) {
        this.command = commandWord;
        this.isFinished = false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
    }

    /**
     * All commands return true except for <code>exit</code> which terminates program.
     * @return Whether program should still continue after command executed
     */
    public boolean toContinueProgram() {
        return !isFinished;
    }

    public void print() {
        System.out.println("Command: " + command);
        System.out.println("Program continues after execution: " + isFinished);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        // Not even the same class
        if (!(o instanceof Command)) {
            return false;
        }
        Command c = (Command) o;
        return c.command == command
                && c.isFinished == isFinished;
    }

}
