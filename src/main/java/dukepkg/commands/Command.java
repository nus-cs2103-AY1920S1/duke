package dukepkg.commands;
import dukepkg.*;
import dukepkg.exceptions.FormatException;

public abstract class Command {


    public static Command getModifyExistingTaskCommand(String[] arr) {
        int index = Integer.parseInt(arr[1]) - 1;
        if(arr[0].equals("done")) {
            return new DoneCommand(index);
        } else {
            return new DeleteCommand(index);
        }
    }

    public static Command getAddTaskCommand(String[] arr) throws FormatException {
        Task t = new Todo(arr[1]);
        if (!arr[0].equals("todo")) {
            if(arr[0].equals("deadline")) {
                Parser.validateDeadlineFormat(arr);
                t = Parser.standardizeDeadlineTime(arr);
                return new DeadlineCommand(t);
            } else {
                Parser.validateEventFormat(arr);
                t = Parser.standardizeEventTime(arr);
                return new EventCommand(t);
            }
        }
        return new TodoCommand(t);
    }

    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws FormatException;

    public boolean isExit() {
        return false;
    }
}
