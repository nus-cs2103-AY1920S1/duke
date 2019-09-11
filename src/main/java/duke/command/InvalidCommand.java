package duke.command;

import duke.task.TaskList;

public class InvalidCommand extends Command implements UnloadableCommand {
    private String reasonForInvalid;
    public InvalidCommand(String reasonForInvalid) {
        this.reasonForInvalid = reasonForInvalid;
    }

    @Override
    public String execute(TaskList tasks) {
        return this.reasonForInvalid;
    }
}
