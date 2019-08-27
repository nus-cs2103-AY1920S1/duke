package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.CannotBeFoundException;
import slave.exception.DukeException;

import slave.task.Task;

import java.util.ArrayList;

/**
 * Command that searches for tasks based on user input
 */
public class FindCommand extends Command {

    private String term;

    /**
     * Constructor for FindCommand
     * @param term term to be searched
     */
    public FindCommand(String term) {
        this.commandType = CommandType.FIND;
        this.term = term;
    }

    /**
     * executes by searching for task with the user input term,
     * storing into a temp arraylist and then printing it to user interface
     * @param taskList list containing current tasks
     * @param ui user interface
     * @throws DukeException throws if searched term isn't in any of the task descriptions
     */
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
