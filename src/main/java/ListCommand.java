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
    String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.displayTaskList();
    }
}
