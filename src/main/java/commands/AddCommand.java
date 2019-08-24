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
     * @param fullCommand the line of user input.
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
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
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Scanner sc = new Scanner(fullCommand);
        sc.next();
        ArrayList<Task> taskLst = tasks.getTaskLst();
        if (fullCommand.startsWith("todo")) {
            // Add a ToDo task
            if (!sc.hasNextLine()) {
                throw new DukeException("     \u2639 OOPS!!! " +
                        "The description of a todo cannot be empty.");
            }
            taskLst.add(new ToDo(sc.nextLine().substring(1), false));
        } else if (fullCommand.startsWith("deadline")) {
            // Add a Deadline task
            String by = sc.nextLine().substring(1);
            String description = "";
            for (int i = 0; i < by.length(); i++) {
                char c = by.charAt(i);
                if (c != '/') {
                    description += c;
                } else {
                    by = by.substring(i + 4);
                    // get rid of space
                    description = description.substring(0, description.length() - 1);
                    break;
                }
            }
            taskLst.add(new Deadline(description,
                    LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                    false));
        } else if (fullCommand.startsWith("event")) { // assume that task is an event
            String at = sc.nextLine().substring(1);
            String description = "";
            for (int i = 0; i < at.length(); i++) {
                char c = at.charAt(i);
                if (c != '/') {
                    description += c;
                } else {
                    at = at.substring(i + 4);
                    // get rid of space
                    description = description.substring(0, description.length() - 1);
                    break;
                }
            }
            taskLst.add(new Event(description,
                    LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                    false));
        } else {
            throw new DukeException("     \u2639 OOPS!!! I'm sorry, " +
                    "but I don't know what that means :-(");
        }
        System.out.printf("     Got it. I've added this task:\n       %s\n" +
                        "     Now you have %d tasks in the list.\n",
        taskLst.get(taskLst.size() - 1), taskLst.size());
    }

}
