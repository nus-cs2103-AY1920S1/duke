package duke.dukeinterface.tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TasklistTest {
    private ArrayList<Task> taskList = new ArrayList<>();

    @Test
    void getDescription1() {
        Task deadline = new Deadline("return books", "2nd of September 2019, 6:00pm");
        String expected = "return books";
        Assertions.assertEquals(expected, deadline.getDescription());
    }

    @Test
    void getDescription2() {
        Task event = new Event("project meeting", "Friday, 4th of July 2019, 8:00am");
        String expected = "project meeting";
        Assertions.assertEquals(expected, event.getDescription());
    }

    @Test
    void getTime1() {
        Deadline deadline = new Deadline("return books", "2nd of September 2019, 6:00pm");
        String expected = "2nd of September 2019, 6:00pm";
        Assertions.assertEquals(expected, deadline.getBy());
    }

    @Test
    void getTime2() {
        Event event = new Event("project meeting", "Friday, 4th of July 2019, 8:00am");
        String expected = "Friday, 4th of July 2019, 8:00am";
        Assertions.assertEquals(expected, event.getAt());
    }

    @Test
    void size() {
        Task todo = new ToDo("borrow books");
        Task deadline = new Deadline("return books", "2nd of September 2019, 6:00pm");
        Task event = new Event("project meeting", "Friday, 4th of July 2019, 8:00am");
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);
        int size = taskList.size();
        Assertions.assertEquals(size, taskList.size());
    }
}