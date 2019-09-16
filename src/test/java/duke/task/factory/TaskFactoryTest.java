package duke.task.factory;

import duke.task.Task;
import duke.task.tasks.Deadline;
import duke.task.tasks.DoAfter;
import duke.task.tasks.DoWithin;
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
        assert task.getDescription().equals("[D][\u2718] hello everyone (by: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateToDo() throws TaskCreationException {
        TaskFactory factory = new TaskFactory();
        Task task = factory.getTask("todo hello everyone").get();

        assert task.getClass().equals(ToDo.class);
        assert task.getDescription().equals("[T][\u2718] hello everyone");
    }

    @Test
    void testCreateEvent() throws TaskCreationException {
        TaskFactory factory = new TaskFactory();
        Task task = factory.getTask("event hello everyone 02/01/2020 0210").get();

        assert task.getClass().equals(Event.class);
        assert task.getDescription()
                .equals("[E][\u2718] hello everyone (at: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateDoAfter() throws TaskCreationException {
        TaskFactory factory = new TaskFactory();
        Task task = factory.getTask("after hello everyone 02/01/2020 0210").get();

        assert task.getClass().equals(DoAfter.class);
        assert task.getDescription()
                .equals("[A][\u2718] hello everyone (after: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateDoWithin() throws TaskCreationException {
        TaskFactory factory = new TaskFactory();
        Task task = factory.getTask("within hello everyone 02/01/2020 0210 to 03/02/2021 0900").get();

        assert task.getClass().equals(DoWithin.class);
        assert task.getDescription()
                .equals("[W][\u2718] hello everyone (from: Jan 02 2020, Thu, 02:10AM to: Feb 03 2021, Wed, 09:00AM)");
    }

    @Test
    void testCreateDoWithin2() throws TaskCreationException {
        TaskFactory factory = new TaskFactory();
        Task task = factory.getTask("within hello everyone 02/01/2020 0210 03/02/2021 0900").get();

        assert task.getClass().equals(DoWithin.class);
        assert task.getDescription()
                .equals("[W][\u2718] hello everyone (from: Jan 02 2020, Thu, 02:10AM to: Feb 03 2021, Wed, 09:00AM)");
    }
}