package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * DoneCommand is a class that marks the specified task
 * item in the list of tasks as done.
 * These items can be ToDo, Event or Deadline tasks.
 */
public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand.
     * Boolean isExit is set to false because
     * program should not terminate after command is executed.
     *
     * @param fullCommand the line of user input.
     */
    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    /**
     * Marks the specified task as completed.
     * The number of the task to be marked is specified
     * by the user via the user input.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Scanner sc = new Scanner(fullCommand);
        sc.next();
        ArrayList<Task> taskLst = tasks.getTaskLst();
        int taskDoneIndex = sc.nextInt() - 1;
        taskLst.get(taskDoneIndex).setDone();
        System.out.printf("     Nice! I've marked this task as done:\n       %s\n"
                , taskLst.get(taskDoneIndex));
    }

}
