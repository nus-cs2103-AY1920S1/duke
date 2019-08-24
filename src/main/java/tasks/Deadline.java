package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a class that represents tasks that have deadlines.
 * A Deadline task, aside from its description and state, stores a LocalDateTime
 * representing the time by which the task has to be completed.
 */
public class Deadline extends Task {

    /** LocalDateTime storing the time by which task has to be completed */
    protected LocalDateTime by;

    /**
     * Deadline constructor that takes a description, a state isDone
     * as well as a LocalDateTime that describes by when the task is to be completed.
     *
     * @param description String containing the description of the task.
     * @param by LocalDateTime detailing when the task is to be completed by.
     * @param isDone boolean storing the task's state of completion.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMMM yyyy, ha")) + ")";
    }

    /**
     * Returns a string that is of the appropriate format
     * to be saved to the file. This formatting ensures that
     * the task information can be read accurately again in the future.
     * Deadline objects are to be stored in this format:
     * D | isDone | description d MMMM yyyy ha
     *
     * @return String format of the task to be saved to the file.
     */
    @Override
    public String fileString() {
        return "D" + super.fileString() + " | " + by.format(DateTimeFormatter.ofPattern("d MMMM yyyy, ha"));
    }
}