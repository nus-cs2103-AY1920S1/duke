import static org.junit.jupiter.api.Assertions.assertEquals;

import dose.task.DeadlineTask;
import dose.task.EventTask;
import dose.task.Task;
import dose.task.TodoTask;
import dose.util.exception.DoseException;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testTodoToString() {
        Task task = new TodoTask("practice math");
        assertEquals("[T] [N] practice math",
            task.toString());
    }

    @Test
    public void testDeadlineToString() throws DoseException {
        Task task = new DeadlineTask("this project", "30/9/2019 2300");
        assertEquals("[D] [N] this project (by: Mon Sep 30 23:00:00 SGT 2019)",
            task.toString());
    }

    @Test
    public void testEventToString() throws DoseException {
        Task task = new EventTask("play guitar with friends", "1/10/2019 1900");
        assertEquals("[E] [N] play guitar with friends (at: Tue Oct 01 19:00:00 SGT 2019)",
            task.toString());
    }

}
