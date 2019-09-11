import java.util.List;

public class AddToDoCommand extends Command{

    String [] tokens;

    public AddToDoCommand(String [] tokens) {
        this.tokens = tokens;
        this.commandType = CommandType.ADDTODO;
    }



    public void execute(List<Task> lst, Ui ui) throws DukeException {
        ToDo task = ToDo.createToDo(tokens);
        lst.add(task);
        ui.printInput(task, lst);
    }


}
