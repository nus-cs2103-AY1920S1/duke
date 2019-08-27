package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.command.DukeInvalidArgumentException;

/**
 * Abstraction of a task representing an event occuring at some time period.
 */
public class EventTask extends Task {
    /** The starting date-time of the time period. */
    private LocalDateTime startDate;
    /** The ending date-time of the time period. */
    private LocalDateTime endDate;

    /**
     * Constructor of the event task.
     * Calls the generic task constructor and initializes its task type.
     * Also calls initDates for validating and setting its dates.
     *
     * @param description The task description string.
     * @param timing The input timing string.
     * @throws DukeInvalidArgumentException If any of the inputs are of invalid format.
     */
    public EventTask(String description, String timing) throws DukeInvalidArgumentException {
        super(description, timing);
        this.taskType = TaskType.event;
        initDates(timing);
    }

    /**
     * Method retrieving task type specific status strings.
     *
     * @return The task type specific status string.
     */
    @Override
    public String getStatusText() {
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(),
                this.description,
                this.getFormattedTiming());
    }

    /**
     * Private function for retrieving the formatted timing for display.
     *
     * @return The formatted timing string.
     */
    private String getFormattedTiming() {
        return TaskUtil.getDisplayTime(startDate)
                + " to "
                + TaskUtil.getDisplayTime(endDate);
    }

    /**
     * Private function for validating and setting the event's time period.
     * Checks for the 'to' delimiter to denote the event's start and end date-times.
     * Also then validates the two string timings according to the formats defined
     * in TaskUtil, then sets the LocalDateTime instance properties.
     *
     * @param timing The input timing string.
     * @throws DukeInvalidArgumentException If the input timing format is invalid or null.
     */
    private void initDates(String timing) throws DukeInvalidArgumentException {
        if (timing == null) {
            throw new DukeInvalidArgumentException(
                    "Null reference provided to task constructor",
                    "\u2639 OOPS!!! The timing for this task cannot be empty!");
        }

        String[] splitTimings = timing.split(" to ");
        if (splitTimings.length != 2) {
            throw new DukeInvalidArgumentException(
                    "Missing to delimiter in event task arguments",
                    "\u2639 OOPS!!! The format of the timing is invalid!\n"
                            + " Enter your time with \"dd/MM/yyyy HHmm to HHmm\"\n"
                            + "  or \"dd/MM/yyyy HHmm to dd/MM/yyyy HHmm\"");
        }

        this.startDate = TaskUtil.getDateFromString(splitTimings[0]);

        if (splitTimings[1].length() <= 4) {
            LocalTime endTime = TaskUtil.getTimeFromString(splitTimings[1]);
            this.endDate = this.startDate
                    .plusHours(endTime.getHour() - this.startDate.getHour())
                    .plusMinutes(endTime.getMinute() - this.startDate.getMinute());
        } else {
            this.endDate = TaskUtil.getDateFromString(splitTimings[1]);
        }

        if (this.endDate.isBefore(this.startDate)) {
            throw new DukeInvalidArgumentException(
                    "Invalid date format inputted by user",
                    " \u2639 OOPS!!! The second timing is not after the first one!");
        }
    }
}