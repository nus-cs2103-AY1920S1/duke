import java.io.IOException;

public class AddToDoCommand extends Command{

    String [] tokens;

    public AddToDoCommand(String [] tokens) {
        this.tokens = tokens;
        this.commandType = CommandType.ADDTODO;
    }


    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        ToDo task = ToDo.createToDo(tokens);
        taskList.addToList(task);
        ui.printInput(task, taskList);
    }
    
}
