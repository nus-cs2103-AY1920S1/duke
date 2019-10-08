package duke.task.factory;

import duke.task.Task;
import duke.task.creation.TaskFactory;
import duke.task.tasks.Deadline;
import duke.task.tasks.DoAfter;
import duke.task.tasks.DoWithin;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;
import error.task.TaskCreationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskFactoryTest {
    public TaskFactory taskFactory = new TaskFactory();

    @Test
    void testCreateDeadline() throws TaskCreationException {
        Task task = taskFactory.getTask("deadline", "hello everyone 02/01/2020 0210").get();

        Assertions.assertEquals(task.getClass(), Deadline.class);
        Assertions.assertEquals(task.getTaskDescription(),
                "[D][✘] hello everyone (by: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateToDo() throws TaskCreationException {
        Task task = taskFactory.getTask("todo",  "hello everyone").get();

        Assertions.assertEquals(task.getClass(), ToDo.class);
        Assertions.assertEquals(task.getTaskDescription(), "[T][✘] hello everyone");
    }

    @Test
    void testCreateEvent() throws TaskCreationException {
        Task task = taskFactory.getTask("event", "hello everyone 02/01/2020 0210").get();

        Assertions.assertEquals(task.getClass(), Event.class);
        Assertions.assertEquals(task.getTaskDescription(),
                "[E][✘] hello everyone (at: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateDoAfter() throws TaskCreationException {
        Task task = taskFactory.getTask("after", "hello everyone 02/01/2020 0210").get();

        Assertions.assertEquals(task.getClass(), DoAfter.class);
        Assertions.assertEquals(task.getTaskDescription(),
                "[A][✘] hello everyone (after: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateDoWithin() throws TaskCreationException {
        Task task = taskFactory.getTask("within",
                "hello everyone 02/01/2020 0210 to 03/02/2021 0900").get();

        Assertions.assertEquals(task.getClass(), DoWithin.class);
        Assertions.assertEquals(task.getTaskDescription(),
                "[W][✘] hello everyone (from: Jan 02 2020, Thu, 02:10AM to: Feb 03 2021, Wed, 09:00AM)");
    }

    @Test
    void testCreateDoWithin2() throws TaskCreationException {
        Task task = taskFactory.getTask("within",
                "hello everyone 02/01/2020 0210 03/02/2021 0900").get();

        Assertions.assertEquals(task.getClass(), DoWithin.class);
        Assertions.assertEquals(task.getTaskDescription(),
                "[W][✘] hello everyone (from: Jan 02 2020, Thu, 02:10AM to: Feb 03 2021, Wed, 09:00AM)");
    }
}