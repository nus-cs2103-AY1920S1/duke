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
    private TaskList tasks;
    private Ui ui;
    private String[] allDetails;
    private String processedDetails = "";
    private String deadlineErrorMsg = "Please write the deadline such as 29/2/2019 1800 and resubmit the command";
    private String eventErrorMsg = "Please write the event timing such as 29/2/2019 1800-2000 and resubmit the command";

    /**
     * Loads the entire input command into the object for further processing of its details, such as
     * type of task added and other details (e.g. description and time).
     *
     * @param allDetails a String array containing all the details to create the appropriate task
     */
    public AddCommand(String[] allDetails) {
        this.allDetails = allDetails;
        for (int i = 1; i < allDetails.length; i++) {
            processedDetails += allDetails[i] + " ";
        }
    }

    /**
     * Executes the command to generate the correct task to be added into memory.
     *
     * @param tasks contains the data structure of Tasks stored in Duke
     * @param ui contains methods dealing with interaction with the user
     * @param storage contains methods to load and save information in the file
     * @exception DukeException is thrown when there is an error with the input
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        this.tasks = tasks;
        this.ui = ui;
        if (allDetails.length == 1) {
            throw new DukeException("Oops! The description of your Task cannot be empty.");
        } else {
            return generateAddedTask();
        }
    }

    /**
     * Checks for the type of task that has been added and passes along processed String of information
     * containing details about the task for the task to be created.
     *
     * @return String containing the task that has been added
     * @throws DukeException is thrown when there is an error with the input
     * @throws IOException is thrown when there is an error saving the data in the hard disk
     */
    private String generateAddedTask()  throws DukeException, IOException {
        String commandWord = allDetails[0];
        if (commandWord.equals("todo")) {
            return toDoTask();
        } else if (commandWord.equals("deadline")) {
            String[] details = processedDetails.split("/by");
            if (details.length < 2) {
                throw new DukeException(deadlineErrorMsg);
            } else {
                return deadlineTask(details);
            }
        } else {
            String[] details = processedDetails.split("/at");
            if (details.length < 2) {
                throw new DukeException(eventErrorMsg);
            } else {
                return eventTask(details);
            }
        }
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Creates a TD object and adds it into memory.
     *
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    private String toDoTask() throws IOException {
        ToDo newTodo = new ToDo(processedDetails);
        this.tasks.addTask(newTodo);
        Storage.save(tasks);
        return printAddedTask(newTodo);
    }

    /**
     * Creates a deadlineTask and adds it into memory.
     * If command does not include a deadline, then user is prompted to enter the command again.
     *
     * @exception DukeException is thrown when there is an error with the input
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    private String deadlineTask(String[] details) throws DukeException, IOException {
        String[] time = details[1].trim().split(" ");
        String formattedTime = processDeadlineTime(time);
        Deadline newDeadline = new Deadline(details[0].trim(), formattedTime);
        this.tasks.addTask(newDeadline);
        Storage.save(tasks);
        return printAddedTask(newDeadline);
    }

    /**
     * Produces a formatted String that is used in creation of a Deadline Task.
     *
     * @param deadlineTime containing the information about the date for the Deadline Task
     * @return String containing some details needed to create a Deadline task
     * @throws DukeException is thrown when there is an error with the input
     */
    private String processDeadlineTime(String[] deadlineTime) throws DukeException {
        if (deadlineTime.length < 2) {
            throw new DukeException(deadlineErrorMsg);
        } else {
            try {
                return DateTime.getDate(deadlineTime[0]) + DateTime.getTime(deadlineTime[1]);
            } catch (DateException e) {
                throw new DukeException(e.getMessage() + deadlineErrorMsg);
            }
        }
    }

    /**
     * Creates an Event object and adds it into memory.
     * If command does not include a timing, then user is prompted to enter the command again.
     *
     * @exception DukeException is thrown when there is an error with the input
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    private String eventTask(String[] details) throws DukeException, IOException {
        String[] eventTime = details[1].trim().split(" ");
        String formattedTime = processEventTime(eventTime);
        Event newEvent = new Event(details[0].trim(), formattedTime);
        this.tasks.addTask(newEvent);
        Storage.save(tasks);
        return printAddedTask(newEvent);
    }

    /**
     * Produces a formatted String that is used in creation of an Event task.
     *
     * @param eventTime containing the information about the date for the Event task
     * @return String containing some details needed to create a Event task
     * @throws DukeException is thrown when there is an error with the input
     */
    private String processEventTime(String[] eventTime) throws DukeException {
        if (eventTime.length < 2) {
            throw new DukeException(eventErrorMsg);
        } else {
            String[] hoursMin = eventTime[1].split("-");
            if (hoursMin.length < 2) {
                throw new DukeException(eventErrorMsg);
            } else {
                try {
                    return DateTime.getDate(eventTime[0]) + DateTime.getTime(hoursMin[0]) + "-"
                            + DateTime.getTime(hoursMin[1]);
                } catch (DateException e) {
                    throw new DukeException(e.getMessage() + eventErrorMsg);
                }
            }
        }
    }

    /**
     * Prints the task that was just added to the list.
     *
     * @param task the Task to be printed after it has been added
    */
    private String printAddedTask(Task task) {
        int numTasks = this.tasks.totalNumTasks();
        return this.ui.printLine("Got it. I've added this task:" + "\n" + task.toString() + "\n"
                + "Now you have " + numTasks + " tasks in the list.");
    }
}
