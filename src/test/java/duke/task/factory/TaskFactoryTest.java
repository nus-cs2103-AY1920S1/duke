package duke.task.factory;

import duke.task.Task;
import duke.task.creation.TaskFactory;
import duke.task.tasks.Deadline;
import duke.task.tasks.DoAfter;
import duke.task.tasks.DoWithin;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;
import error.task.TaskCreationException;
import org.junit.jupiter.api.Test;

class TaskFactoryTest {
    @Test
    void testCreateDeadline() throws TaskCreationException {
        Task task = TaskFactory.getTask("deadline", "hello everyone 02/01/2020 0210").get();

        assert task.getClass().equals(Deadline.class);
        assert task.getTaskDescription().equals("[D][\u2718] hello everyone (by: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateToDo() throws TaskCreationException {
        Task task = TaskFactory.getTask("todo",  "hello everyone").get();

        assert task.getClass().equals(ToDo.class);
        assert task.getTaskDescription().equals("[T][\u2718] hello everyone");
    }

    @Test
    void testCreateEvent() throws TaskCreationException {
        Task task = TaskFactory.getTask("event", "hello everyone 02/01/2020 0210").get();

        assert task.getClass().equals(Event.class);
        assert task.getTaskDescription()
                .equals("[E][\u2718] hello everyone (at: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateDoAfter() throws TaskCreationException {
        Task task = TaskFactory.getTask("after", "hello everyone 02/01/2020 0210").get();

        assert task.getClass().equals(DoAfter.class);
        assert task.getTaskDescription()
                .equals("[A][\u2718] hello everyone (after: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateDoWithin() throws TaskCreationException {
        Task task = TaskFactory.getTask("within",  "hello everyone 02/01/2020 0210 to 03/02/2021 0900").get();

        assert task.getClass().equals(DoWithin.class);
        assert task.getTaskDescription()
                .equals("[W][\u2718] hello everyone (from: Jan 02 2020, Thu, 02:10AM to: Feb 03 2021, Wed, 09:00AM)");
    }

    @Test
    void testCreateDoWithin2() throws TaskCreationException {
        Task task = TaskFactory.getTask("within", "hello everyone 02/01/2020 0210 03/02/2021 0900").get();

        assert task.getClass().equals(DoWithin.class);
        assert task.getTaskDescription()
                .equals("[W][\u2718] hello everyone (from: Jan 02 2020, Thu, 02:10AM to: Feb 03 2021, Wed, 09:00AM)");
    }
}