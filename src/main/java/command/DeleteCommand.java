package command;
import task.Task;
import task.TaskList;

/**
 *
 */

public class DeleteCommand extends Command {
    int deletedIndex;
    Task removed;

    /**
     *
     */

    public DeleteCommand(int number) {
        deletedIndex = number;
    }

    /**
     *
     */

    @Override
    public String executeCommand(TaskList reference) {
        this.reference = reference;
        removed = reference.deleteTask(deletedIndex);
        return this.formatOutput();
    }

    /**
     *
     */

    public String formatOutput() {
        return TextFormatter.deleteFormat(removed,reference.getSize());
    }


}
