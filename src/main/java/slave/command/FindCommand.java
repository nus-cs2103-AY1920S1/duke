package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.CannotBeFoundException;
import slave.exception.KappaException;

import slave.task.Task;

import java.util.ArrayList;

/**
 * Command that searches for tasks based on user input.
 */
public class FindCommand extends Command {

    private String term;

    /**
     * Constructor for FindCommand.
     *
     * @param term Term to be searched.
     */
    public FindCommand(String term) {
        this.commandType = CommandType.FIND;
        this.term = term;
    }

    /**
     * Executes by searching for task with the user input term,
     * storing into a temp arraylist and then printing it to user interface.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws KappaException Throws if searched term isn't in any of the task descriptions.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws KappaException {
        ArrayList<Task> findList = new ArrayList<>();
        for (Task task: tasks.getList()) {
            if (task.getDescription().contains(this.term)) {
                findList.add(task);
            }
        }
        if (findList.size() == 0) {
            throw new CannotBeFoundException(this.term);
        }
        return ui.printFindCommand(findList);
    }

}
