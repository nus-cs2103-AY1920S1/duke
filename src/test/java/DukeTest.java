import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void addDeadlineTaskCommandTest() throws DukeException {
        String fullCommand = "deadline return book /by 2/12/2019 1800";
        Task actualTask = new AddTaskCommand(fullCommand).currTask;
        Task expectedTask = new Deadline("return book ", "2/12/2019 1800");
        assertEquals(actualTask.getTaskDetails(), expectedTask.getTaskDetails());
    }

    public void addEventTaskCommandTest() throws DukeException {
        String fullCommand = "event project meeting /at Mon 2-4pm";
        Task actualTask = new AddTaskCommand(fullCommand).currTask;
        Task expectedTask = new Event("project meeting ", "Mon 2-4pm");
        assertEquals(actualTask.getTaskDetails(), expectedTask.getTaskDetails());
    }

    public void addTodoTaskCommandTest() throws DukeException {
        String fullCommand = "todo borrow book";
        Task actualTask = new AddTaskCommand(fullCommand).currTask;
        Task expectedTask = new Todo("borrow book");
        assertEquals(actualTask.getTaskDetails(), expectedTask.getTaskDetails());
    }
}
