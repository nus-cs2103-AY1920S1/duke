import java.io.IOException;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index){
        this.commandType = CommandType.DELETE;
        this.index = index;
    }

    public static DeleteCommand createDeleteIfValid(String [] tokens) throws DukeException {
        try {
            Parser.checkValidLength(tokens);
            int index = Integer.parseInt(tokens[1])-1;
            return new DeleteCommand(index);
        } catch (NumberFormatException error) {
            throw new DukeException("Must be integer", DukeExceptionType.NOTINTEGER);
        }
    }

    @Override

    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        try {
            Task task = taskList.getTaskAt(index+1);
            taskList.removeFromList(task);
            ui.printDeletion(task, taskList);
        } catch (IndexOutOfBoundsException error3) {
            ui.printOneLine(new DukeException("No such task",DukeExceptionType.MISSINGTASK).getMessage());
        }
    }

}
