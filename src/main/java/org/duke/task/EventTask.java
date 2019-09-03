package org.duke.task;

import org.duke.json.JsonWriter;
import org.duke.util.DateParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;

/**
 * Represents a event task, with a start timing attached.
 */
public class EventTask extends Task {
    private final LocalDateTime timing;

    /**
     * Constructs an {@link EventTask} from a task description and timing date string.
     *
     * @param task   Description of task
     * @param timing Date string describing timing
     */
    public EventTask(String task, String timing) {
        super(task);
        this.timing = DateParser.parse(timing);
    }

    EventTask(Map<String, Object> dict) {
        super(dict);
        this.timing = DateTimeFormatter.ISO_LOCAL_DATE_TIME
                .parse((String) dict.get("timing"), LocalDateTime::from);
    }

    @Override
    protected TaskType getTaskType() {
        return TaskType.Event;
    }

    @Override
    public String toString() {
        String baseDescription = super.toString();
        return String.format("%s (at: %s)", baseDescription,
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(timing));
    }

    @Override
    public void toJson(JsonWriter.ObjectContext ctx) {
        super.toJson(ctx);
        ctx.writeField("timing", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(this.timing));
    }
}
