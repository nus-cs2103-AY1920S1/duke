package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DukeTest {
    @Test
    void testTask() {
        Todo t1 = Todo.parse("dig a trench from the fence to lunchtime");
        Event t2 = Event.parse("Beijing Olympics Opening Ceremony /at 8/8/2008 2000");
        Deadline t3 = Deadline.parse("my birth /by 4/12/1998 1251");
        Todo t4 = Todo.parse("nothing");

        t2.markDone();
        t4.markDone();

        assertEquals(t1.toString(), "[T][\u2718] dig a trench from the fence to lunchtime");
        assertEquals(t2.toString(), "[E][\u2713] Beijing Olympics Opening Ceremony "
                + "(at: 8 August 2008 20:00)");
        assertEquals(t3.export(), "D|0|my birth|4/12/1998 1251");
        assertEquals(t4.export(), "T|1|nothing|");

        assertThrows(IllegalArgumentException.class, () -> Deadline.parse("heat death /by eternity"));
    }

    @Test
    void testTaskList() {
        TaskList tasks = new TaskList();
        for (int i = 0; i < 5; i++) {
            tasks.add(new TaskStub());
        }
        assertEquals(tasks.size(), 5);

        tasks.markDone(4);
        tasks.delete(3);

        assertEquals(tasks.get(3).toString(), "done task");
        assertEquals(tasks.get(4).export(), "undone export");
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.get(5));
    }

    @Test
    void testCommand() {
        StorageStub storageStub = new StorageStub();
        UiStub uiStub = new UiStub();
        TaskListStub tasksStub = new TaskListStub();

        Command c = new AddCommand(new TaskStub());
        c.execute(tasksStub, uiStub, storageStub);
        assertEquals(tasksStub.size(), 1);

        c = new DeleteCommand("1");
        c.execute(tasksStub, uiStub, storageStub);
        assertEquals(tasksStub.size(), 0);
    }
}
