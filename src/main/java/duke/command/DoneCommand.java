package duke.command;

import duke.component.Statistics;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents user's "done" commmand to chatbot.
 * The 'DoneCommand' class supports operators (i) executing the command
 * and (ii) checking if the bot has exited its conversation with the user.
 */
public class DoneCommand extends Command {

    /**
     * The task number to be marked done.
     */
    int taskNum;

    /**
     * Initializes a new instance of DoneCommand, with the task number to be marked done.
     *
     * @param taskNum Task number
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks respective task in taskList as done and task from text file to be 0.
     * Prints response in console.
     *
     * @param taskList List of the things user needs to do.
     * @param ui       Interface that interacts with the user.
     * @param storage  Stores the user input in a file.
     * @throws DukeException IOException if there is an error writing or reading file.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        //Getting the original status icon of task
        //To check if task is already done before 'done' command is entered
        String originalStatusIcon = taskList.getTask(taskNum).getStatusIcon();
        boolean isAlreadyCompleted = originalStatusIcon.equals("v");

        if (isAlreadyCompleted) {
            Task updatedTask = taskList.getTask(taskNum);

            return ("Nice! I've marked this task as done: \n" + updatedTask);

        }

        taskList.markTaskDone(taskNum);
        Task updatedTask = taskList.getTask(taskNum);

        //Assert post condition
        assert updatedTask.getStatusIcon().equals("v") : "Task is not marked as done";


        storage.updateText(taskNum);


        Statistics.decrementUncompleted();
        Statistics.incrementCompleted();
        storage.updateStatistics();

        return ("Nice! I've marked this task as done: \n" + updatedTask);


    }

    /**
     * Returns a false to indicate program has not exited.
     *
     * @return false Program has not exited
     */
    public boolean isExit() {
        return false;
    }
}