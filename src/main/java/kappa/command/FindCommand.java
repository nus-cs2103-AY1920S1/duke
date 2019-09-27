package kappa.command;

import kappa.elements.TaskList;
import kappa.elements.Ui;

import kappa.exception.CannotBeFoundException;
import kappa.exception.KappaException;

import kappa.task.Task;

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
     * storing into a temp ArrayList and then printing it to user interface.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws KappaException Throws if searched term isn't in any of the task descriptions.
     * @return String containing Kappa's formatted dialog.
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
