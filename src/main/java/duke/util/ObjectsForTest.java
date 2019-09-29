package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This is a class that holds static strings and <code>Task</code> objects for testing purpose.
 */
public class ObjectsForTest {

    private static final LocalDateTime DATE_TIME = LocalDateTime.parse("2020-05-31T11:00");
    private static final LocalTime TIME = LocalTime.parse("23:00");
    private static final String TASK_INF1 = "Sleep early";
    private static final String TASK_INF2 = "Sleep";

    public static final String FILE_PATH = System.getProperty("user.dir") + "/data.txt";

    public static final Todo TODO1 = new Todo(TASK_INF1);
    public static final Todo TODO2 = new Todo(TASK_INF2);
    public static final Deadline DEADLINE1 = new Deadline(TASK_INF1, DATE_TIME);
    public static final Deadline DEADLINE2 = new Deadline(TASK_INF2, DATE_TIME);
    public static final Event EVENT = new Event(TASK_INF2, DATE_TIME, TIME);

    public static final TaskList TASK_LIST_TODO = new TaskList(TODO1);
    public static final TaskList TASK_LIST_ALL = new TaskList(TODO1, DEADLINE2, EVENT);
}
