package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.DukeInvalidArgumentException;

public class EventTask extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public EventTask(String description, String timing) throws DukeInvalidArgumentException {
        super(description, timing);
        this.taskType = TaskType.event;
        initDates(timing);
    }

    @Override
    public String getStatusText() {
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(),
                this.description,
                this.getFormattedTiming());
    }

    private String getFormattedTiming() {
        return TaskUtil.getDisplayTime(startDate)
                + " to "
                + TaskUtil.getDisplayTime(endDate);
    }

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
    }
}