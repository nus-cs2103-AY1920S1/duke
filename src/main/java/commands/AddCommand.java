package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
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
        isExit = false;
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
                        + "The description of a todo cannot be empty.");
            }
            taskLst.add(new ToDo(commandArr[1], false));
        } else if (commandArr[0].equals("deadline")) {
            // Add a Deadline task
            String[] fullCommand = commandArr[1].split(" /by ");
            String description = fullCommand[0];
            String by = fullCommand[1];
            taskLst.add(new Deadline(description,
                    LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                    false));
        } else if (commandArr[0].equals("event")) { // assume that task is an event
            String[] fullCommand = commandArr[1].split(" /at ");
            String description = fullCommand[0];
            String at = fullCommand[1];
            taskLst.add(new Event(description,
                    LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                    false));
        } else {
            throw new DukeException("     \u2639 OOPS!!! I'm sorry, "
                    + "but I don't know what that means :-(");
        }
        return String.format("     Got it. I've added this task:\n       %s\n"
                        + "     Now you have %d tasks in the list.\n",
            taskLst.get(taskLst.size() - 1), taskLst.size());
    }

}
