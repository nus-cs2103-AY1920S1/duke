package duck.util;

import duck.task.Deadline;
import duck.task.Event;
import duck.task.Task;
import duck.task.Todo;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class ObjectsForTest {
    public static final String todoTaskInfo = "T|false|Sleep early";
    public static final String deadlineTaskInfo = "D|false|Sleep|2020-05-31T11:00";
    public static final String eventTaskInfo = "E|false|Sleep|2020-05-31T11:00|23:00";

    public static final String todoCommand = "todo Sleep early";
    public static final String deadlineCommand = "deadline Sleep /by 31/5/2020 11:00";
    public static final String eventCommand = "event Sleep /at 31/5/2020 11:00-23:00";

    public static final String timeDateStr = "31/5/2020 11:00";
    public static final String timeSTr = "23:00";

    public static final Todo todo = new Todo("Sleep early");
    public static final Deadline deadline = new Deadline("Sleep",
            Parser.parseDateTime("31/5/2020 11:00"));
    public static final Event event = new Event("Sleep", Parser.parseDateTime("31/5/2020 11:00"),
            LocalTime.parse("23:00"));
    public static final Task[] tasks = {todo, deadline, event};
}
