package duke.task.tasks;

import duke.task.Task;
import duke.task.tasks.entities.TaskType;
import duke.task.tasks.entities.TimeFrame;

import java.time.LocalDateTime;

public class DoWithin extends Task {
    public DoWithin(String description, LocalDateTime from, LocalDateTime to, Boolean isDone, Boolean isRecurring) {
        super(TaskType.DO_WITHIN , description, new TimeFrame(from, to), isDone, isRecurring);
    }
}
