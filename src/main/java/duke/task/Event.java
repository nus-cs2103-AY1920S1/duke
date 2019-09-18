package duke.task;

import duke.DukeException;
import duke.Storage;
import duke.command.Command;

import java.time.format.DateTimeParseException;
import java.util.List;

public class Event extends TimeTask {
    Event(String description, String time) {
        super(description, time);
    }

    /**
     * Returns a Command which generates an Event given the input line.
     *
     * @param tasks The shared list of tasks.
     * @param storage Storage to save the tasks after adding the event.
     * @return A Command which generates events.
     */
    public static Command getCommand(TaskList tasks, Storage storage) {
        return words -> {
            List<String> wordList = List.of(words);
            int separator = wordList.indexOf("/at");
            if (separator == -1) {
                throw new DukeException("An event must have a time.");
            } else if (separator == 1) {
                throw new DukeException("The description of an event cannot be empty.");
            } else if (separator == words.length - 1) {
                throw new DukeException("The time of an event cannot be empty.");
            }
            assert separator > 1;
            String description = String.join(" ", wordList.subList(1, separator));
            String time = String.join(" ", wordList.subList(separator + 1, words.length));
            try {
                Task task = new Event(description, time);
                tasks.add(task);
                storage.store(tasks.getAsLines());
                return List.of("Got it. I've added this task:", "  " + task,
                        "Now you have " + tasks.size() + " tasks in the list.");
            } catch (DateTimeParseException e) {
                return List.of(TimeTask.DATE_FORMAT_HINT, "Tried to interpret as date: " + time);
            }
        };
    }

    /**
     * Returns the type of this task.
     *
     * @return Type of this task as a string.
     */
    @Override
    String getTaskType() {
        return "E";
    }

    /**
     * Returns this task as a string to display to the user.
     *
     * @return This task as a string.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + getTimeString() + ")";
    }
}
