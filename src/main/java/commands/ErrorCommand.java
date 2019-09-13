package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;


public class ErrorCommand extends Command {
    private String msg;

    public ErrorCommand(String msg) {
        assert msg != null;
        this.msg = msg;
    }

    /**
     * This method is used to generate error message.
     *
     * @param tasks   the tasklist object to be used in this command
     * @param ui      the ui object to be used in this command
     * @param storage the storage object to be used in this command
     * @return duke's response after error
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert msg != null;
        return msg;
    }
}
