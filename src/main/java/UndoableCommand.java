public abstract class UndoableCommand extends Command {
    abstract String undo(TaskList tasks, Storage storage) throws DukeException;
}
