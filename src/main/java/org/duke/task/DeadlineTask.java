package org.duke.task;

import org.duke.json.JsonWriter;
import org.duke.util.DateParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;


/**
 * Represents a task with a deadline attached.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a {@link DeadlineTask} from a given description and deadline.
     *
     * @param task     Description
     * @param deadline Deadline date string
     */
    public DeadlineTask(String task, String deadline) {
        super(task);
        this.deadline = DateParser.parse(deadline);
    }

    DeadlineTask(Map<String, Object> dict) {
        super(dict);
        this.deadline = DateTimeFormatter.ISO_LOCAL_DATE_TIME
                .parse((String) dict.get("deadline"), LocalDateTime::from);
    }

    @Override
    protected TaskType getTaskType() {
        return TaskType.Deadline;
    }

    @Override
    public String toString() {
        String baseDescription = super.toString();
        return String.format("%s (by: %s)", baseDescription,
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(deadline));
    }

    @Override
    protected void toJson(JsonWriter.ObjectContext ctx) {
        super.toJson(ctx);
        ctx.writeField("deadline", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(this.deadline));
    }
}
