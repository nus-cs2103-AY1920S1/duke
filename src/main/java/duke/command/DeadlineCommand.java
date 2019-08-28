package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a command to add a new Deadline to the list.
 */
public class DeadlineCommand extends Command {
    private Deadline newTask;

    /**
     * Class constructor that initializes the description, date and timing of new
     * Deadline to be added.
     *
     * @param description Description of deadline.
     * @param date Date of Deadline to be completed by.
     * @param timing Timing of Deadline to be completed by.
     */
    public DeadlineCommand(String description, String date, String timing){
        this.newTask = new Deadline(description, date, timing);
    }

    /**
     * Executes the command to add the new Deadline.
     *
     * @param taskList List of Tasks to be modified by command.
     * @param ui Ui object to be called by the command.
     * @param storage Storage object to be called by the command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printGotIt(newTask);
        taskList.addTask(newTask);
        ui.printNumTasks();
        storage.setChangedTrue();
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return False as not exit command.
     */
    public boolean isExit(){
        return false;
    }
}
