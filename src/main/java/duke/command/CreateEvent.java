package duke.command;

import duke.task.Event;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class CreateEvent {

    /** Task added successfully message. */
    private static String task_added_message = "Got it. I've added this task:\n";

    /** Stores the DateTimeFormatter object used to specify the format of date/time objects when printed. */
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    static String createEvent(ArrayList<Task> taskList, String[] params, Storage storage) throws DukeException {
        if (Parser.joinStrings(params).isEmpty()) {
            throw new DukeException("The description and due date of an event cannot be empty.");
        }
        String[] details = Parser.splitByIdentifier(params, "/at");
        if (details[0].isEmpty() && details[1].isEmpty()) {
            throw new DukeException("You need to specify a due date, denoted by /at");
        } else if (details[0].isEmpty() && !details[1].isEmpty()) {
            throw new DukeException("The description or date of an event cannot be empty.");
        }
        Task current;
        try {
            current = new Event(details[0], LocalDateTime.parse(details[1], formatter));
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
