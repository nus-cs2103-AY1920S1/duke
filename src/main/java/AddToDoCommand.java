import java.io.IOException;
import java.text.Normalizer;

public class AddToDoCommand extends Command{

    String task;
    ToDo toDoTask;

    public AddToDoCommand(String task){
        this.commandType = CommandType.ADDTODO;
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        this.toDoTask = new ToDo(this.task, taskList.getSize() + 1);
        taskList.addToList(this.toDoTask);
        ui.printAddToDoCommand(toDoTask, taskList);
    }
}
