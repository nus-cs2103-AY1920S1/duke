package duke.task.tasks;

import duke.task.Task;
import duke.task.TimeFrame;
import error.task.TaskCreationException;

import java.time.LocalDateTime;

/**
 * Task that needs to be done at a specified time.
 */
public class Event extends Task {
    public Event(String details, LocalDateTime at) throws TaskCreationException {
        super('E', details, new TimeFrame(at, at));
    }


    @Override
    public boolean isTimeFrameCompatible(TimeFrame timeframe) {
        return timeframe.getStart().equals(timeframe.getEnd());
    }
}
