/**
 * This is an abstract class for command inputs.
 * @author Choong Yong Xin
 */

import java.io.IOException;

abstract class Command {
    String commandDesc;

    Command(String commandDesc) {
        this.commandDesc = commandDesc;
    }

    abstract boolean isExit();

    abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
