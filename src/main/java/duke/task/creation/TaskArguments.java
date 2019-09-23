package duke.task.creation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class encapsulating all the necessary information needed for task creation. It contains the details string of the
 * task and a list of DateTimes.
 */
class TaskArguments {
    private final String details;
    private final List<LocalDateTime> dateTimes;

    TaskArguments(String details, List<LocalDateTime> dateTimes) {
        this.details = details;
        this.dateTimes = dateTimes;
    }

    /**
     * Returns the details portion of the task arguments.
     * @return details string of the task arguments.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Returns the datetime arguments of the task arguments.
     * @return list of datetime arguments.
     */
    public List<LocalDateTime> getDateTimes() {
        return dateTimes;
    }
}
