package duke.command;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    int toComplete;
    public DoneCommand(int toComplete){
        this.toComplete = toComplete;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage){
        try {
            if (toComplete >= taskList.size() || toComplete < 0) {
                throw new DukeException("OOPS! duke.task.Task " + (toComplete + 1) + " doesn't exist!");
            }
            Task curr = taskList.get(toComplete);
            taskList.get(toComplete).completeTask();
            ui.printNice(curr);
            storage.setChangedTrue();
        } catch (DukeException de){
            ui.printTaskError(de.getMessage());
        }
    }

    public boolean isExit(){
        return false;
    }
}
