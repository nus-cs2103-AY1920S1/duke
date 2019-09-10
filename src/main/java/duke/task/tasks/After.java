package duke.task.tasks;

import duke.task.Task;
import duke.task.tasks.entities.TaskType;
import duke.task.tasks.entities.TimeFrame;
import error.task.TaskCreationException;

import java.time.LocalDateTime;

public class After extends Task {
    public After(String details, LocalDateTime after, boolean isDone, boolean isRecurring) {
        super(TaskType.AFTER, details, new TimeFrame(after, null), isDone, isRecurring);
    }
}
