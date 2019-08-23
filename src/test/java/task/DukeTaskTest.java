package task;

import duke.task.DukeTaskDeadline;
import duke.task.DukeTaskEvent;
import duke.task.DukeTaskToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTaskTest {
    private final String DUKE_TASK_TODO_INCOMPLETE = "[T][\u2718] Conduct Testing on DukeTaskTodo";
    private final String DUKE_TASK_TODO_COMPLETE = "[T][\u2713] Conduct Testing on DukeTaskTodo";
    private final String DUKE_TASK_DEADLINE_INCOMPLETE = "[D][\u2718] Conduct Testing on DukeTaskDeadline (by: 10/2/2019 2100)";
    private final String DUKE_TASK_DEADLINE_COMPLETE = "[D][\u2713] Conduct Testing on DukeTaskDeadline (by: 10/2/2019 2100)";
    private final String DUKE_TASK_EVENT_INCOMPLETE = "[E][\u2718] Conduct Testing on DukeTaskEvent (at: i3 Auditorium)";
    private final String DUKE_TASK_EVENT_COMPLETE = "[E][\u2713] Conduct Testing on DukeTaskEvent (at: i3 Auditorium)";

    @Test
    public void testTodo() {
        DukeTaskToDo todo = new DukeTaskToDo("Conduct Testing on DukeTaskTodo");
        assertEquals(todo.toString(), DUKE_TASK_TODO_INCOMPLETE);

        todo.setTaskComplete();
        assertEquals(todo.toString(), DUKE_TASK_TODO_COMPLETE);
    }

    @Test
    public void testDeadline() {
        DukeTaskDeadline deadline = new DukeTaskDeadline("Conduct Testing on DukeTaskDeadline", "10/2/2019 2100");
        assertEquals(deadline.toString(), DUKE_TASK_DEADLINE_INCOMPLETE);

        deadline.setTaskComplete();
        assertEquals(deadline.toString(), DUKE_TASK_DEADLINE_COMPLETE);
    }

    @Test
    public void testEvent() {
        DukeTaskEvent event = new DukeTaskEvent("Conduct Testing on DukeTaskEvent", "i3 Auditorium");
        assertEquals(event.toString(), DUKE_TASK_EVENT_INCOMPLETE);

        event.setTaskComplete();
        assertEquals(event.toString(), DUKE_TASK_EVENT_COMPLETE);
    }

}
