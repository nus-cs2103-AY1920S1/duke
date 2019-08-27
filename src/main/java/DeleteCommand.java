import java.io.IOException;
import java.text.Normalizer;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index){
        this.commandType = CommandType.DELETE;
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            taskList.getTaskByIndex(this.index);
        } catch (IndexOutOfBoundsException error) {
            throw new TaskNotFoundException("Task " + this.index );
        }
        Task toRemove = taskList.getTaskByIndex(this.index);
        taskList.removeFromList(this.index);
        ui.printDeleteCommand(toRemove, taskList);
    }
}
