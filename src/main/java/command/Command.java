package command;

import task.TaskList;

/**
 *
 */

public abstract class Command {
    TaskList reference;


    /**
     *
     */

    public String executeCommand(TaskList reference) {
        this.reference = reference;
        return this.formatOutput();
    }

    /**
     *
     */

    public abstract String formatOutput();


}
