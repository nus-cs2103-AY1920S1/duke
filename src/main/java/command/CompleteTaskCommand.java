package duke.command;

public class CompleteTaskCommand extends Command {
    public CompleteTaskCommand(String taskNumber) {
        super(Type.COMPLETE, taskNumber);
    }
}