package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    String keyword;

    public FindCommand(String keyword){
        super.type = FullCommand.FIND;
        this.keyword = keyword;
    }
    public boolean isExit(){
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        ArrayList<Task> shortlist = new ArrayList<>();
        for (Task task: tasks.getList()){
            if (task.findWord(keyword)){
                shortlist.add(task);
            }
        }
        ui.showMatches(new TaskList(shortlist));
    }
}
