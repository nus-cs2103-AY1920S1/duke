package duke.task.tasks;

import duke.task.Task;
import duke.task.TimeFrame;
import error.task.TaskCreationException;

import java.time.LocalDateTime;

/**
 * Task that needs to be done within a specific time period.
 */
public class DoWithin extends Task {
    public DoWithin(String description, LocalDateTime from, LocalDateTime to) throws TaskCreationException {
        super('W', description, new TimeFrame(from, to));
    }

    @Override
    public boolean isTimeFrameCompatible(TimeFrame timeframe) {
        return timeframe.getStart() != null && timeframe.getEnd() != null;
    }
}
