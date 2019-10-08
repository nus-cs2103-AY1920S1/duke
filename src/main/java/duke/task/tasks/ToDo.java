package duke.task.tasks;

import duke.task.Task;
import duke.task.TimeFrame;
import error.task.TaskCreationException;

/**
 * Task that has no time constraints.
 */
public class ToDo extends Task {
    public ToDo(String details) throws TaskCreationException {
        super('T', details, new TimeFrame(null, null));
    }


    @Override
    public boolean isTimeFrameCompatible(TimeFrame timeframe) {
        return timeframe.getStart() == null && timeframe.getEnd() == null;
    }
}
