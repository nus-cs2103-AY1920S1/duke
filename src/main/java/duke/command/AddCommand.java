package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Represents user's 'deadline' commmand to chatbot.
 * The 'DeadlineCommand' class supports operators (i) executing the command
 * and (ii) checking if the bot has exited its conversation with the user(in superclass).
 */
public class AddCommand extends Command {

    /**
     * Whether the deadline task is done.
     */
    boolean isDone;

    /**
     * Description of deadline task.
     */
    String description;

    /**
     * Date in which the deadline task is due.
     */
    Date deadline;

    /**
     * Type of task to be added.
     */
    String type;

    /**
     * Initializes a new instance of DeadlineCommand.
     *
     * @param isDone      Whether deadline task is done.
     * @param description Description of task.
     * @param deadline    Date the task is due.
     */
    public AddCommand(String type, boolean isDone, String description, String deadline) {
        this.type = type;
        this.isDone = isDone;
        this.description = description;

        if (deadline == null) {
            this.deadline = null;
        } else {
            try {
                Date twentyFourHourFormat = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(deadline);
                String twelveHourFormat = new SimpleDateFormat("dd/MM/yyy hh:mm").format(twentyFourHourFormat);
                this.deadline = new SimpleDateFormat("dd/MM/yyy hh:mm").parse(twelveHourFormat);
            } catch (ParseException e) {
                System.out.println(e.getMessage());

            }
        }
    }

    /**
     * Adds Task object to TaskList and adds inputs to the File in Storage, and
     * prints response.
     *
     * @param taskList List of the things user needs to do.
     * @param ui       Interface that interacts with the user.
     * @param storage  Stores the user input in a file.
     * @throws DukeException IOException if there is an error writing or reading file.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        storage.appendToFile(this.type, this.deadline, this.description);

        Task newTask = null;

        switch (this.type) {
        case ("D"):

            newTask = new Deadline(this.description, this.deadline);
            break;

        case ("E"):

            newTask = new Event(this.description, this.deadline);
            break;

        default:
            newTask = new Todo(this.description);
            break;


        }


        taskList.addTask(newTask);

        int numTask = taskList.size();


        return("Got it. I've added this task: \n" + "  "
                + newTask + "Now you have "
                + numTask + " tasks in the list.");

    }


    /**
     * Returns a false to indicate program has not exited.
     *
     * @return false Program has not exited.
     */
    public boolean isExit() {
        return false;
    }
}


