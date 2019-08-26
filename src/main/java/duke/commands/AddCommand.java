/**
 * This class represents a specific command of adding a task to Duke.
 */
package duke.commands;
import duke.exceptions.DukeException;
import duke.exceptions.DateException;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
import duke.managers.DateTime;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.ToDo;
import duke.tasks.Event;
import java.io.IOException;

public class AddCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private DateTime DT = new DateTime();
    private String[] allDetails;

    /**
     * This constructor loads the entire input command into the object for further processing of its details, such as type
     * of task added and other details (e.g. description and time).
     */
    public AddCommand(String[] allDetails) {
        this.allDetails = allDetails;
    }

    /**
     * This method checks for the type of task that has been added and passes along processed String of information
     * containing details about the task for the task to be created.
     * @param tasks contains the data structure of Tasks stored in Duke
     * @param ui contains methods dealing with interaction with the user
     * @param storage contains methods to load and save information in the file
     * @exception DukeException is thrown when there is an error with the input
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        String processed = "";
        for (int i = 1; i < allDetails.length; i++) {
            processed += allDetails[i] + " ";
        }
        String commandWord = allDetails[0];
        if (allDetails.length == 1) {
            throw new DukeException("Oops! The description of your Task cannot be empty.");
        } else if (commandWord.equals("todo")) {
            toDoTask(processed);
        } else if (commandWord.equals("deadline")) {
            deadlineTask(processed);
        } else {
            eventTask(processed);
        }
    }

    public boolean isExit() {
        return false;
    }

    /**
     * This method creates a ToDo object and adds it into memory.
     * @param a a String of information containing details for the To Do task
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    private void toDoTask(String a) throws IOException {
        ToDo newTodo = new ToDo(a);
        tasks.addTask(newTodo);
        printAddedTask(newTodo);
        storage.save();
    }

    /**
     * This method creates a deadlineTask and adds it into memory.
     * If command does not include a deadline, then user is prompted to enter the command again.
     * @param b a String of information containing details for the Deadline task
     * @exception DukeException is thrown when there is an error with the input
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    private void deadlineTask(String b) throws DukeException, IOException {
        String[] details = b.split("/by");
        if (details.length < 2) {
            throw new DukeException("Oops! Please include the deadline and resubmit that command.");
        } else {
            String[] time = details[1].trim().split(" ");
            if (time.length < 2) {
                throw new DukeException("Oops! Please write the deadline such as 29/2/2019 1800");
            } else {
                try {
                    String formattedTime = DT.getDate(time[0]) + DT.getTime(time[1]);
                    Deadline newDeadline = new Deadline(details[0].trim(), formattedTime);
                    tasks.addTask(newDeadline);
                    printAddedTask(newDeadline);
                    storage.save();
                } catch (DateException e) {
                    throw new DukeException("Oops! " + e.getMessage() + " Please write the deadline such as 29/2/2019 1800");
                }
            }
        }
    }

    /**
     * This method creates an Event object and adds it into memory.
     * If command does not include a timing, then user is prompted to enter the command again.
     * @param c a String of information containing details for the Event task
     * @exception DukeException is thrown when there is an error with the input
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    private void eventTask(String c) throws DukeException, IOException {
        String[] details = c.split("/at");
        if (details.length < 2) {
            throw new DukeException("Oops! Please include the event timing and resubmit that command.");
        } else {
            String[] eventTime = details[1].trim().split(" ");
            if (eventTime.length < 2) {
                throw new DukeException("Oops! Please write the event timing such as 29/2/2019 1800-2000");
            } else {
                try {
                    String[] hoursMin = eventTime[1].split("-");
                    if (hoursMin.length < 2) {
                        throw new DukeException("Oops! Please write the event timing such as 29/2/2019 1800-2000");
                    } else {
                        String hM = DT.getTime(hoursMin[0]) + "-" + DT.getTime(hoursMin[1]);
                        String formattedTime = DT.getDate(eventTime[0]) + hM;
                        Event newEvent = new Event(details[0].trim(), formattedTime);
                        tasks.addTask(newEvent);
                        printAddedTask(newEvent);
                        storage.save();
                    }
                } catch (DateException e) {
                    throw new DukeException("Oops! " + e.getMessage() + " Please write the event timing such as 29/2/2019 1800-2000");
                }
            }
        }
    }

    /**
     * This method prints the task that was just added to the list.
     * @param task the Task to be printed after it has been added
    */
    private void printAddedTask(Task task) {
        int numTasks = tasks.totalNumTasks();
        this.ui.printLine("Got it. I've added this task:");
        this.ui.printLine(task.toString());
        this.ui.printLine("Now you have " + numTasks + " tasks in the list.");
    }
}
