package duke.command;

import duke.exceptions.DukeException;
import duke.formats.DateTime;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Represents the actions to execute when the command 'deadline' is triggered.
 */

public class AddDeadlineCommand extends Command {

    private Deadline deadline;

    /**
     * Returns an AddDeadlineCommand object from the commandArray, an array of words
     * which make up the initial user input.
     * 
     * @param commandArray Array of Strings that form the initial user input
     * @throws DukeException if input doesn't match the format expected
     */

    public AddDeadlineCommand(String[] commandArray) throws DukeException {
        StringBuilder deadlineNameBuilder = new StringBuilder();
        int arrayIndex = 1;
        //adds all the words in the user input before the substring /by into deadlineNameBuilder
        while (!commandArray[arrayIndex].equals("/by")) {
            if (arrayIndex >= commandArray.length - 1) {
                throw new DukeException(":( OOPS!!! The '/by' sequence couldn't be found.");
            }
            deadlineNameBuilder.append(" " + commandArray[arrayIndex]);
            arrayIndex++;
        }
        deadlineNameBuilder.append(" ");
        arrayIndex++;
        // creates the due date string in the form of deadlineDueBuilder
        StringBuilder deadlineDueBuilder = new StringBuilder();
        if (arrayIndex >= commandArray.length) {
            throw new DukeException(":( OOPS!!! The deadline must be specified.");
        }
        boolean isFirstWord = true;
        while (arrayIndex < commandArray.length) {
            if (!isFirstWord) {
                deadlineDueBuilder.append(" ");
            }
            deadlineDueBuilder.append(commandArray[arrayIndex]);
            arrayIndex++;
            isFirstWord = false;
        }
        String deadlineDue = deadlineDueBuilder.toString();
        DateTime dateTime = new DateTime(deadlineDue);
        String deadlineName = deadlineNameBuilder.toString();
        this.deadline = new Deadline(deadlineName, false, dateTime.toString());
    }

    /**
     * Adds a Deadline object into the TaskList as per the command inputted.
     * 
     * @param tasks   List of Tasks
     * @param storage External storage where the list of tasks is stored
     */

    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert deadline != null : "deadline should hold an actual Deadline object.";
        tasks.addTask(deadline);
        try {
            storage.writeToFile(deadline.toFile());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return "Got it. I've added this task:\n"
                + "  " + deadline + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }
}
