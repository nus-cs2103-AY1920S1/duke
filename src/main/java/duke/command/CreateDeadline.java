package duke.command;

import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class CreateDeadline {

    /** Task added successfully message. */
    private static String task_added_message = "Got it. I've added this task:\n";

    /** Stores the DateTimeFormatter object used to specify the format of date/time objects when printed. */
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    static String createDeadline(ArrayList<Task> taskList, String[] params, Storage storage) throws DukeException {
        if (Parser.joinStrings(params).isEmpty()) {
            throw new DukeException("The description and due date of a deadline cannot be empty.");
        }
        String[] details = Parser.splitByIdentifier(params, "/by");
        if (details[0].isEmpty() && details[1].isEmpty()) {
            throw new DukeException("You need to specify a due date, denoted by /by");
        } else if (details[0].isEmpty() && !details[1].isEmpty()) {
            throw new DukeException("The description or date of a deadline cannot be empty.");
        }
        Task current;
        try {
            current = new Deadline(details[0], LocalDateTime.parse(details[1], formatter));
        } catch (DateTimeParseException e) {
            throw new DukeException("You need to specify a due date in the format dd/MM/yyyy HHmm");
        }
        taskList.add(current);
        String s = task_added_message + current + TotalNoOfTasks.totalNoOfTasks(taskList);
        boolean isSaved = Save.save(storage, taskList);
        assert isSaved == true : "Error: Not saved to disk.";
        return s;
    }
}
