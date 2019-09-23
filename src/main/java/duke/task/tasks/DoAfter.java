package duke.task.tasks;

import duke.task.Task;
import duke.task.TimeFrame;
import error.task.TaskCreationException;

import java.time.LocalDateTime;

/**
 * Task that needs to be done after a specific time.
 */
public class DoAfter extends Task {
    public DoAfter(String details, LocalDateTime after) throws TaskCreationException {
        super('A', details, new TimeFrame(after, null));
    }

    @Override
    public boolean isTimeFrameCompatible(TimeFrame timeframe) {
        return timeframe.getEnd() == null;
    }
}
