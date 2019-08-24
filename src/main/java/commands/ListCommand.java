package commands;

import java.util.ArrayList;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import tasks.Task;

/**
 * ListCommand is a class that displays all task items
 * that are currently in the list of tasks to the user.
 * These items can be ToDo, Event or Deadline tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     * Boolean isExit is set to false because
     * program should not terminate after command is executed.
     *
     * @param fullCommand the line of user input.
     */
    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    /**
     * Displays all tasks that are currently on the
     * list of tasks to the user.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskLst = tasks.getTaskLst();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskLst.size(); i++) {
            System.out.printf("     %d.%s\n",
                    i + 1, taskLst.get(i));
        }
    }

}
