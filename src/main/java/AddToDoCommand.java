import java.io.IOException;
import java.text.Normalizer;

public class AddToDoCommand extends Command{

    String task;
    ToDo toDoTask;

    public AddToDoCommand(String task){
        this.commandType = CommandType.ADDTODO;
        this.task = task;
    }

}
