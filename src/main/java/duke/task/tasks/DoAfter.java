package duke.task.tasks;

import duke.task.Task;
import duke.task.tasks.entities.TaskType;
import duke.task.tasks.entities.TimeFrame;

import java.time.LocalDateTime;

public class DoAfter extends Task {
    public DoAfter(String details, LocalDateTime after, Boolean isDone, Boolean isRecurring) {
        super(TaskType.DO_AFTER, details, new TimeFrame(after, null), isDone, isRecurring);
    }
}
