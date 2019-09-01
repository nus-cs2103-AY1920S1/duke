package duke.command;

import duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;


public class FindCommand extends Command {

    String toFind;

    public FindCommand(String[] commandArray){
        String toFind = "";
        boolean isFirst = true;
        for(int i = 1; i < commandArray.length; i++){
            if(!isFirst){
                toFind += commandArray[i];
                isFirst = false;
                break;
            }
            toFind += " " + commandArray[i];
            this.toFind = toFind;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matchingTasks = tasks.search(toFind);
        ui.showTasks(matchingTasks);
    }
}
