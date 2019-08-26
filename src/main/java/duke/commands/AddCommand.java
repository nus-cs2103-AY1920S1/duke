package duke.commands;
import duke.exceptions.*;
import duke.managers.*;
import duke.tasks.*;
import java.io.IOException;

public class AddCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private DateTime DT = new DateTime();
    private String[] allDetails;

    /*
    This constructor loads the entire command into the class object for further processing.
     */
    public AddCommand(String[] alldetails) {
        this.allDetails = alldetails;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;

        String processed = ""; //this string will contain everything behind the command word
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
        } else { //this is for event (coz exception commands are already handled)
            eventTask(processed);
        }
    }

    public boolean isExit() {
        return false;
    }

    /*
    This method creates a TD object and adds it into memory.
     */
    private void toDoTask(String a) throws IOException {
        ToDo newTodo = new ToDo(a);
        tasks.addTask(newTodo);
        printTask(newTodo);
        storage.save();
    }

    /*
    This method creates an Event object and adds it into memory.
    If command does not include a timing, then user is prompted to enter the command again.
     */
    private void eventTask(String b) throws DukeException, IOException {
        String[] details = b.split("/at");
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
                        printTask(newEvent);
                        storage.save();
                    }
                } catch (DateException e) {
                    throw new DukeException("Oops! " + e.getMessage() + " Please write the event timing such as 29/2/2019 1800-2000");
                }
            }
        }
    }

    /*
    This method creates a deadlineTask and adds it into memory.
    If command does not include a deadline, then user is prompted to enter the command again.
     */
    private void deadlineTask(String c) throws DukeException, IOException {
        String[] details = c.split("/by");
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
                    printTask(newDeadline);
                    storage.save();
                } catch (DateException e) {
                    throw new DukeException("Oops! " + e.getMessage() + " Please write the deadline such as 29/2/2019 1800");
                }
            }
        }
    }

    /*
    This method prints the task that was just added to the list.
    */
    private void printTask(Task task) {
        int numTasks = tasks.totalNumTasks();
        this.ui.printLine("Got it. I've added this task:");
        this.ui.printLine(task.toString());
        this.ui.printLine("Now you have " + numTasks + " tasks in the list.");
    }
}
