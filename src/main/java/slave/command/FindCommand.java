package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;
import slave.exception.CannotBeFoundException;
import slave.exception.DukeException;
import slave.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    String term;

    public FindCommand(String term) {
        this.commandType = CommandType.FIND;
        this.term = term;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ArrayList<Task> findList = new ArrayList<>();
        for (Task task: taskList.getList()){
            if (task.getDescription().contains(this.term)){
                findList.add(task);
            }
        }
        if ( findList.size() == 0 ) {
            throw new CannotBeFoundException(this.term);
        }
        ui.printFindCommand(findList);
    }

}
