package duke.task.tasks;

import duke.task.Task;
import duke.task.TimeFrame;
import error.task.TaskCreationException;

import java.time.LocalDateTime;

/**
 * Task that needs to be completed by a specified time.
 */
public class Deadline extends Task {
    public Deadline(String details, LocalDateTime by) throws TaskCreationException {
        super('D', details, new TimeFrame(null, by));
    }


    @Override
    public boolean isTimeFrameCompatible(TimeFrame timeframe) {
        return timeframe.getStart() == null;
    }
}
