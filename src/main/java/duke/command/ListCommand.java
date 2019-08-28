package duke.command;

import duke.DukeException;
import duke.parser.Task;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.UiText;

public class ListCommand extends Command {
    public ListCommand(String[] msg) {
        super(msg);
    }
    /**
     * Check is the user input format correct.
     * If is correct then list all the task in the taskList.
     * @param list  TaskList
     * @param ui    UiText
     * @param storage   Storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if(super.command.length == 1) {
            ui.printlnMsg("Here are the tasks in your list:");
            for (int i = 0; i < list.getList().size(); i++) {
                Task tk = list.getList().get(i);
                System.out.println(i + 1 + "." + tk);
            }
        } else {
            throw new DukeException("\u1F65 OOPS!! I\'m sorry, but I don\'t know what that means :-(");
        }
    }
}
