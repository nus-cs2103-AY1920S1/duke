package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TimeTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewCommand implements Command {
    private TaskList tasks;

    public ViewCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the message to show the user after the command is run.
     *
     * @param words Array of words from the input line.
     * @return Message to show the user.
     */
    @Override
    public List<String> run(String[] words) {
        String dateString = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            return List.of("Please enter the date in the format d/M/yyyy e.g. 19/9/2019");
        }

        List<String> messages = new ArrayList<>();
        messages.add("Here is your schedule for " + dateString + ":");

        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof TimeTask && ((TimeTask) task).isOn(date)) {
                count++;
                messages.add(count + "." + task);
            }
        }
        return messages;
    }
}
