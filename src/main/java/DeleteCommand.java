import java.util.List;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index){
        this.commandType = CommandType.DELETE;
        this.index = index;
    }

    public static DeleteCommand createDeleteIfValid(String [] tokens) throws DukeException {
        try {
            Ui.checkValidLength(tokens);
            int index = Integer.parseInt(tokens[1])-1;
            return new DeleteCommand(index);
        } catch (NumberFormatException error) {
            throw new DukeException("Must be integer", DukeExceptionType.NOTINTEGER);
        }
    }

    public void execute(List<Task> lst, Ui ui) throws DukeException {
        try {
            Task task = lst.get(index);
            lst.remove(task);
            ui.printDeletion(task, lst);
        } catch (IndexOutOfBoundsException error3) {
            ui.printOneLine(new DukeException("No such task",DukeExceptionType.MISSINGTASK).getMessage());
        }
    }


}
