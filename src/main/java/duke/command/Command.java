package duke.command;

import duke.TaskList;

public class Command {
    protected boolean carryOn;
    protected TaskList taskList;

    public Command() {
    }

    public String execute(TaskList taskList) {
        return "";
    }

    public boolean getCarryOn() {
        return this.carryOn;
    }

}