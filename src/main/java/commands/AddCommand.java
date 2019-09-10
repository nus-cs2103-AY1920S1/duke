package commands;

import java.time.LocalDateTime;

import java.util.ArrayList;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

import tasks.Task;
import tasks.Event;
import tasks.Deadline;
import tasks.ToDo;

import exceptions.DukeException;

/**
 * AddCommand is a class dealing with commands that
 * add task items to the list of tasks. These items
 * can be ToDo, Event or Deadline tasks.
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand.
     * Boolean isExit is set to false because
     * program should not terminate after command is executed.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public AddCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    /**
     * Adds the corresponding Task type to the list of tasks
     * based on the user input. These Tasks can be either
     * a ToDo, Event or Deadline Task.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @throws DukeException  If there is invalid input.
     * @return String output reply from Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskLst = tasks.getTaskLst();
        if (commandArr[0].equals("todo")) {
            // Add a ToDo task
            if (commandArr.length <= 1) {
                throw new DukeException("     \u2639 OOPS!!! "
                        + "The description of a todo cannot be empty.\n");
            }
            taskLst.add(new ToDo(commandArr[1], false));
        } else if (commandArr[0].equals("deadline") || commandArr[0].equals("event")) {
            String[] fullCommand;
            if (commandArr[0].equals("deadline")) {
                fullCommand = commandArr[1].split(" /by ");
            } else { // if command equals "event"
                fullCommand = commandArr[1].split(" /at ");
            }
            if (fullCommand.length <= 1) {
                String msg = "     \u2639 Either the description of " + (commandArr[0].equals("deadline")
                        ? "a deadline " : "an event ") + "or its date-time is not provided.\n";
                throw new DukeException(msg);
            }
            String description = fullCommand[0];
            String userDateTimeString = fullCommand[1];
            LocalDateTime localDateTime = Parser.parseDateTime(userDateTimeString);
            if (localDateTime == null) {
                String dateInstruction = "     Example date formats allowed: 07101997, 07/10/1997, " +
                        "07 10 1997, 7 October 1997.\n";
                String timeInstruction = "     Example time formats allowed: 8:39AM, 0839.\n";
                String dtInstruction = "     Example date-times allowed: 07/10/1997 0839, 7 October 8:39AM.\n";
                String msg = "     \u2639 You have entered an invalid date-time format!\n"
                        + dateInstruction + timeInstruction + dtInstruction;
                throw new DukeException(msg);
            }
            if (commandArr[0].equals("deadline")) {
                taskLst.add(new Deadline(description, localDateTime, false));
            } else { // if command equals "event"
                taskLst.add(new Event(description, localDateTime, false));
            }
        } else {
            // Invalid command being supplied by the user
            throw new DukeException("     \u2639 OOPS!!! I'm sorry, "
                    + "but I don't know what that means :-(\n");
        }
        return String.format("     Got it. I've added this task:\n       %s\n"
                        + "     Now you have %d tasks in the list.\n",
            taskLst.get(taskLst.size() - 1), taskLst.size());
    }

}
