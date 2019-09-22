package duke.task.creation;

import java.time.LocalDateTime;
import java.util.List;

class TaskArguments {
    private final String details;
    private final List<LocalDateTime> dateTimes;

    TaskArguments(String details, List<LocalDateTime> dateTimes) {
        this.details = details;
        this.dateTimes = dateTimes;
    }

    public String getDetails() {
        return details;
    }

    public List<LocalDateTime> getDateTimes() {
        return dateTimes;
    }
}
