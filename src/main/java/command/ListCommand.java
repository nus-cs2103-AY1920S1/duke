package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        String[] arr = new String[tasks.size()+1];
        arr[0] = "Here are the tasks in your list:";
        for (int i = 1; i <= tasks.size(); i++) {
            arr[i] = i + ". " + tasks.getTask(i).toString();
        }
        ui.dukeEcho(arr);
    }
}
