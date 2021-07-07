package seedu.duke.logic.command;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.commons.exceptions.listexceptions.DuplicateTaskException;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.model.task.Deadline;
import seedu.duke.model.task.Event;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.model.task.Todo;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that adds Tasks into the TaskList.
 * Contains a method that executes commands by adding tasks into the TaskLst.
 * Stores information on the task to be created, so the task can be created when the execute method is
 * called by a client of this class.
 */
public class AddCommand extends Command {

    private String command;
    private String[] strArr;

    private String type;
    private String taskString;
    private DateAndTime dateAndTime;

    /** Creates an instance of AddCommand.
     * Stores to attributes of AddCommand relevant information required to create an AddCommand.
     * @param command String representation of a single-word command.
     * @param taskString String representation of task description.
     * @param dateAndTime DateAndTime object storing the date and times relevant to the task.
     *                    If the task does not require any date or time, null may be passed in as an argument.
     */
    public AddCommand(String command, String taskString, DateAndTime dateAndTime) {
        assert !command.equals("") : "String attribute storing command of an Add command created is empty.";
        assert !taskString.equals("") : "Task description of an Add command created is empty.";
        this.command = command;
        this.taskString = taskString;
        this.dateAndTime = dateAndTime;
    }

    /** Executes an AddCommand object using information stored in instance attributes.
     * Modifies a given TaskList by adding the task defined by its attributes to it.
     * Saves the updated TaskList to the given Storage.
     * Displays a message indicating successful addition after addition process has been completed.
     * @param tasks TaskList to which a given task is to be added by this command.
     * @param ui Ui with methods that are called to display a message upon successful addition of the task.
     * @param storage Storage that contains saved String representation of the given TaskList and is to be modified
     *                upon successful addition of the task.
     * @throws DukeException if execution of the command is unsuccessful.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = null;
            if (tasks.hasExistingTask(command, taskString, dateAndTime)) {
                throw new DuplicateTaskException("The same task already exists in the list!");
            }
            if (command.equals("todo")) {
                task = new Todo(taskString);
            } else if (command.equals("deadline")) {
                task = new Deadline(taskString, dateAndTime);
            } else if (command.equals("event")) {
                task = new Event(taskString, dateAndTime);
            }

            int sizeBefore = tasks.size();
            tasks.add(task);
            int sizeAfter = tasks.size();
            assert sizeAfter != sizeBefore : "Task was called to be added to TaskList but "
                    + "size of TaskList remained unchanged.";
            assert sizeAfter == sizeBefore + 1 : "One item was meant to be added to the TaskList "
                    + "but the difference in sizes of the lists before and after is not 1.";
            String addedMessage = task.addedMessage(tasks);
            ui.showAddedMessage(addedMessage);
            assert ui.getOutput().equals(addedMessage) : "Task was added to a TaskList "
                    + "but output does not tally with added message.";
            assert isExit() == false : "An Add command is exhibiting behaviour that instructs the program to exit.";
            storage.save(tasks, ui);
        } catch (DukeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DukeException("Execution of command unsuccessful.");
        }
    }
}
