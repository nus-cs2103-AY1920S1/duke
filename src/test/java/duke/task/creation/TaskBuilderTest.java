package duke.task.creation;

import duke.task.Task;
import duke.task.tasks.Deadline;
import duke.task.tasks.DoAfter;
import duke.task.tasks.DoWithin;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;
import error.datetime.UnknownDateTimeException;
import error.task.TaskCreationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.time.DateTime;

import java.util.ArrayList;
import java.util.List;


class TaskBuilderTest {

    @Test
    void testCreateDeadline() throws TaskCreationException, UnknownDateTimeException {
        TaskArguments arguments = new TaskArguments("hello everyone", List.of(DateTime.parse("02/01/2020 0210")));
        Task task = TaskBuilder.buildTask(TaskType.DEADLINE, arguments);

        Assertions.assertEquals(task.getClass(), Deadline.class);
        Assertions.assertEquals(task.getTaskDescription(),
                "[D][✘] hello everyone (by: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateToDo() throws TaskCreationException, UnknownDateTimeException {
        TaskArguments arguments = new TaskArguments("hello everyone", new ArrayList<>());
        Task task = TaskBuilder.buildTask(TaskType.TODO, arguments);

        Assertions.assertEquals(task.getClass(), ToDo.class);
        Assertions.assertEquals(task.getTaskDescription(), "[T][✘] hello everyone");
    }

    @Test
    void testCreateEvent() throws TaskCreationException, UnknownDateTimeException {
        TaskArguments arguments = new TaskArguments("hello everyone", List.of(DateTime.parse("02/01/2020 0210")));
        Task task = TaskBuilder.buildTask(TaskType.EVENT, arguments);

        Assertions.assertEquals(task.getClass(), Event.class);
        Assertions.assertEquals(task.getTaskDescription(), "[E][✘] hello everyone (at: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateDoAfter() throws TaskCreationException, UnknownDateTimeException {
        TaskArguments arguments = new TaskArguments("hello everyone", List.of(DateTime.parse("02/01/2020 0210")));
        Task task = TaskBuilder.buildTask(TaskType.DO_AFTER, arguments);

        Assertions.assertEquals(task.getClass(), DoAfter.class);
        Assertions.assertEquals(task.getTaskDescription(),
                "[A][✘] hello everyone (after: Jan 02 2020, Thu, 02:10AM)");
    }

    @Test
    void testCreateDoWithin() throws TaskCreationException, UnknownDateTimeException {
        TaskArguments arguments = new TaskArguments("hello everyone",
                List.of(DateTime.parse("02/01/2020 0210"), DateTime.parse("03/02/2021 0900")));
        Task task = TaskBuilder.buildTask(TaskType.DO_WITHIN, arguments);

        Assertions.assertEquals(task.getClass(), DoWithin.class);
        Assertions.assertEquals(task.getTaskDescription(),
                "[W][✘] hello everyone (from: Jan 02 2020, Thu, 02:10AM to: Feb 03 2021, Wed, 09:00AM)");
    }
}