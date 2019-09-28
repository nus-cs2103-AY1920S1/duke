package duke.command;

import duke.DukeRuntimeException;
import duke.task.TaskList;
import duke.task.TimeTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ViewCommand extends ListCommand {
    /**
     * Generates a new view schedule command with a header and criteria of whether the task is on the given date.
     *
     * @param tasks List of tasks.
     */
    public ViewCommand(TaskList tasks) {
        super(tasks,
            words -> "Here is your schedule for " + extractArgument(words) + ":",
            words -> {
                try {
                    LocalDate date = LocalDate.parse(extractArgument(words), DateTimeFormatter.ofPattern("d/M/yyyy"));
                    return task -> task instanceof TimeTask && ((TimeTask) task).isOn(date);
                } catch (DateTimeParseException e) {
                    throw new DukeRuntimeException("Please enter the date in the format d/M/yyyy e.g. 19/9/2019");
                }
            });
    }
}
