import java.util.Stack;

public class UndoCommand extends Command {
    Stack<UndoableCommand> commandHistory;

    UndoCommand(Stack<UndoableCommand> commandHistory) {
        this.commandHistory = commandHistory;
    }

    @Override
    String execute(TaskList tasks, Storage storage) throws DukeException {
        if (commandHistory.isEmpty()) {
            return "There are no more commands to undo!";
        }
        return commandHistory.pop().undo(tasks, storage);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
