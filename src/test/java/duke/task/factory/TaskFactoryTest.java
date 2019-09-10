package duke.task.factory;

import duke.task.Task;
import duke.task.tasks.Deadline;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;
import error.task.TaskCreationException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class TaskFactoryTest {
    @Test
    void testCreateDeadline() throws TaskCreationException {
        TaskFactory factory = new TaskFactory();
        Task task = factory.getTask("deadline hello everyone 02/01/2020 0210").get();

        assert task.getClass().equals(Deadline.class);
    }

    @Test
    void testCreateToDo() throws TaskCreationException {
        TaskFactory factory = new TaskFactory();
        Task task = factory.getTask("todo hello everyone").get();

        assert task.getClass().equals(ToDo.class);
    }

    @Test
    void testCreateEvent() throws TaskCreationException {
        TaskFactory factory = new TaskFactory();
        Task task = factory.getTask("event hello 02/01/2020 0210").get();

        assert task.getClass().equals(Event.class);
    }
}