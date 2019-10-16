package duke.command;

import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

/**
 * Represents a ExitCommand. Upon execution will exit the chatbot.
 *
 */
public class ExitCommand extends Command {

    /**
     * Does nothing because the logic will terminate.
     *
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        System.out.println("Good bye!");
        return "Good bye!";
    }


    /**
     * Checks if this is the exit command.
     *
     * @return true as it is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Get the command string
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Compare another object with this object to see if the other object is ExitCommand.
     *
     * @param o The other object that is going to be compared to this.
     * @return true or false based on the execution of the method.
     */
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof ExitCommand)) {
            return false;
        } else {
            return true;
        }
    }
}
