/**
 * This is a class for list command.
 * @author Choong Yong Xin
 */

public class ListCommand extends Command {

    ListCommand(String commandDesc) {
        super(commandDesc);
    }

    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayTaskList();
    }
}
