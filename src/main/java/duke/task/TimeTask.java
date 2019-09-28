package duke.task;

import duke.DukeException;
import duke.Storage;
import duke.command.Command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public abstract class TimeTask extends Task {
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd HHmm");
    private LocalDateTime time;

    TimeTask(String description, String timeString) {
        super(description);
        DateTimeFormatter parseFormatter = new DateTimeFormatterBuilder()
                .appendOptional(saveFormatter)
                .appendOptional(displayFormatter)
                .toFormatter();
        time = LocalDateTime.parse(timeString, parseFormatter);
    }

    /**
     * Returns a Command which generates a Deadline given the input line.
     *
     * @param tasks The shared list of tasks.
     * @param storage Storage to save the tasks after adding the deadline.
     * @param timePreposition Preposition describing the time contained by this task.
     * @param nameWithArticle Name of the task with "a" or "an".
     * @param constructor Constructor of the task.
     * @return A Command which generates tasks with times using the given constructor.
     */
    static Command getCommand(TaskList tasks, Storage storage, String timePreposition, String nameWithArticle,
                              BiFunction<String, String, TimeTask> constructor) {
        return words -> {
            List<String> wordList = List.of(words);
            int separator = wordList.indexOf("/" + timePreposition);
            if (separator == -1) {
                throw new DukeException(String.format(
                        "To create %s, enter the command \"%s <description> /%s <time>\".",
                        nameWithArticle, words[0], timePreposition));
            } else if (separator == 1) {
                throw new DukeException("The description of " + nameWithArticle + " cannot be empty.");
            } else if (separator == words.length - 1) {
                throw new DukeException("The time of " + nameWithArticle + " cannot be empty.");
            }
            assert separator > 1;
            String description = String.join(" ", wordList.subList(1, separator));
            String time = String.join(" ", wordList.subList(separator + 1, words.length));
            try {
                return addTask(constructor.apply(description, time), tasks, storage);
            } catch (DateTimeParseException e) {
                return List.of("Please enter the date in the format d/M/yyyy HHmm e.g. 19/9/2019 1430",
                        "Could not interpret as date: " + time);
            }
        };
    }

    /**
     * Returns the time attached to this task as a string for saving.
     *
     * @return Time as a string.
     */
    String getSaveTimeString() {
        return saveFormatter.format(time);
    }

    /**
     * Returns the time attached to this task as a string to display to the user.
     *
     * @return Time as a string.
     */
    String getTimeString() {
        return displayFormatter.format(time);
    }

    /**
     * Returns a list of strings representing this task so that it can be saved.
     *
     * @return A representation of this task as a list for saving.
     */
    @Override
    public List<String> getSaveList() {
        List<String> list = new ArrayList<>(super.getSaveList());
        list.add(getSaveTimeString());
        return list;
    }

    /**
     * Returns whether this task is on the given date.
     *
     * @param date Date which this task is checked to be on.
     * @return Whether the task is on the date.
     */
    public boolean isOn(LocalDate date) {
        return time.toLocalDate().isEqual(date);
    }
}
