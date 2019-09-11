import java.util.List;

public class DoneCommand extends Command{

    int index;

    public DoneCommand(int index){
        this.commandType = CommandType.DONE;
        this.index = index;
    }

    public static DoneCommand createDoneIfValid(String [] tokens) throws DukeException {
        try {
            Ui.checkValidLength(tokens);
            int index = Integer.parseInt(tokens[1])-1;
            return new DoneCommand(index);
        } catch (NumberFormatException error) {
            throw new DukeException("Must be integer", DukeExceptionType.NOTINTEGER);
        }
    }


    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            Task task = taskList.getTaskAt(index+1);
            boolean isDoneBefore = task.setDone();
            if (isDoneBefore) {
                throw new IllegalArgumentException("Task has already been done");
            }
            List<String> inst = List.of("Nice! I've marked this task as done: ",
                    "  "+task.toString());
            ui.printInput(inst);
        } catch (IndexOutOfBoundsException error3) {
            ui.printOneLine(new DukeException("No such task", DukeExceptionType.MISSINGTASK).getMessage());
        } catch (IllegalArgumentException error2) {
            ui.printOneLine(new DukeException(error2.getMessage(), DukeExceptionType.GENERALMISTAKE).getMessage());
        }
    }

}
