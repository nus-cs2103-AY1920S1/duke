import duke.command.Command;
import duke.main.Parser;
import duke.task.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeTest {
    @Test
    void dukeTodoParseTest() {
        Parser p = new Parser();
        TaskList tasks = new TaskList();
        Command c = p.parse("todo Winner Winner Chicken Dinner");
        c.execute(tasks);
        TodoTask expectedTodo = new TodoTask("Winner Winner Chicken Dinner");
        String actualTaskInfo = tasks.getFullTaskInfo(0);
        String expectedTaskInfo = expectedTodo.toString();

        assertEquals(actualTaskInfo, expectedTaskInfo);
    }

    @Test
    void dukeDeadlineParseTest() {
        Parser p = new Parser();
        TaskList tasks = new TaskList();
        Command c = p.parse("deadline project work /by 19/02/2019 1122");
        c.execute(tasks);
        Date d = new GregorianCalendar(2019,1,19,11,22).getTime();
        Deadline expectedDeadline = new Deadline("project work", d);
        String actualTaskInfo = tasks.getFullTaskInfo(0);
        String expectedTaskInfo = expectedDeadline.toString();

        assertEquals(actualTaskInfo, expectedTaskInfo);
    }

    @Test
    void dukeEventParseTest() {
        Parser p = new Parser();
        TaskList tasks = new TaskList();
        Command c = p.parse("event world temperature raises by 3 degrees /at 01/01/2056 0000");
        c.execute(tasks);
        Date d = new GregorianCalendar(2056,0,1,0,0).getTime();
        Event expectedEvent = new Event("world temperature raises by 3 degrees", d);
        String actualTaskInfo = tasks.getFullTaskInfo(0);
        String expectedTaskInfo = expectedEvent.toString();

        assertEquals(actualTaskInfo, expectedTaskInfo);
    }

    @Test
    void dukeSetPriorityTest() {
        Parser p = new Parser();
        TaskList tasks = new TaskList();
        p.parse("event world temperature raises by 3 degrees /at 01/01/2056 0000").execute(tasks);
        p.parse("priority 1 HIGH").execute(tasks);
        Date d = new GregorianCalendar(2056,0,1,0,0).getTime();
        Event expectedEvent = new Event("world temperature raises by 3 degrees", d);
        expectedEvent.setPriority(PriorityLevel.HIGH);
        String actualTaskInfo = tasks.getFullTaskInfo(0);
        String expectedTaskInfo = expectedEvent.toString();

        assertEquals(actualTaskInfo, expectedTaskInfo);
    }

    @Test
    void dukePrioritySortTest() {
        TaskList tasks = new TaskList();
        Event event = new Event(
                "world temperature raises by 3 degrees",
                new GregorianCalendar(2056,0,1,0,0).getTime()
        );
        Deadline deadline = new Deadline(
                "project work",
                new GregorianCalendar(2019,1,19,11,22).getTime()
        );
        TodoTask todo = new TodoTask("Winner Winner Chicken Dinner");
        tasks.addTask(event);
        tasks.addTask(deadline);
        tasks.addTask(todo);
        tasks.setPriorityOfTask(2, PriorityLevel.MEDIUM);

        assertEquals(tasks.getFullTaskInfo(0), todo.toString());

        tasks.setPriorityOfTask(1, PriorityLevel.HIGH);
        assertEquals(tasks.getFullTaskInfo(0), event.toString());

        tasks.setPriorityOfTask(0, PriorityLevel.MEDIUM);
        tasks.setPriorityOfTask(2, PriorityLevel.HIGH);
        assertEquals(tasks.getFullTaskInfo(0), deadline.toString());
    }
}
