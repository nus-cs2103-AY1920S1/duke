package duke.command;

public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(String taskNumber) {
        super(Type.DELETE, taskNumber);
    }
}