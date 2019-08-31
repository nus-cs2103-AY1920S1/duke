package duke.command;

import duke.Ui;
import duke.exceptions.DukeException;
import duke.formats.DateTime;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Represents the actions to execute when the command 'deadline' is
 * triggered.
 */

public class AddDeadlineCommand extends Command{

    private Deadline deadline;

    /**
     * Returns an AddDeadlineCommand object from the commandArray,
     * an array of words which make up the initial user input
     * @param commandArray Array of Strings that form the initial user input
     * @throws DukeException if input doesn't match the format expected
     */
    public AddDeadlineCommand(String [] commandArray) throws DukeException {
        String deadlineName = "";
        int i = 1;
        while(!commandArray[i].equals("/by")){
            if(i >= commandArray.length - 1){
                throw new DukeException("☹ OOPS!!! The '/by' sequence couldn't be found.");
            }
            deadlineName += " " + commandArray[i];
            i++;
        }
        deadlineName += " ";
        i++;
        String deadlineDue = "";
        if(i >= commandArray.length){
            throw new DukeException("☹ OOPS!!! The deadline must be specified.");
        }
        boolean isFirstWord = true;
        while(i < commandArray.length){
            if(!isFirstWord){
                deadlineDue += " ";
            }
            deadlineDue += commandArray[i];
            i++;
            isFirstWord = false;
        }
        DateTime dateTime = new DateTime(deadlineDue);
        this.deadline = new Deadline(deadlineName, false, dateTime.toString());
    }

    /**
     * Adds a Deadline object into the TaskList as per the command inputted
     * @param tasks List of Tasks
     * @param ui User Interface displaying the tasks in the TaskList
     * @param storage External storage where the list of tasks is stored
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(deadline);
        ui.showAddTask(deadline, tasks.getSize());
        try{
            storage.writeToFile(deadline.toFile());
        } catch (IOException e){
            ui.showIOException(e);
        }
    }
}
