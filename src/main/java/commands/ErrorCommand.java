package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;


public class ErrorCommand extends Command {
    private String msg;

    public ErrorCommand(String msg) {
        this.msg = msg;
    }

    /**
     * This method is used to generate error message.
     *
     * @return duke response after error
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return msg;
    }
}
