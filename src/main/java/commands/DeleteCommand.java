package commands;

import java.util.ArrayList;
import java.util.Scanner;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import tasks.Task;

/**
 * DeleteCommand is a class that removes the specified
 * task item from the list of tasks. These items
 * can be ToDo, Event or Deadline tasks.
 */
public class DeleteCommand extends Command {

    /**
     *  Constructor for DeleteCommand.
     *  Boolean isExit is set to false because
     *  program should not terminate after command is executed.
     *
     * @param fullCommand the line of user input.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    /**
     * Removes the specified task from the list of tasks.
     * The number of the task to be removed is specified
     * in the user input.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Scanner sc = new Scanner(fullCommand);
        sc.next();
        ArrayList<Task> taskLst = tasks.getTaskLst();
        int delTaskIndex = sc.nextInt() - 1;
        Task deletedTask = taskLst.remove(delTaskIndex);
        System.out.printf("     Noted. I've removed this task:\n" +
                        "       %s\n     Now you have %d tasks in the list.\n"
                , deletedTask, taskLst.size());
    }

}
