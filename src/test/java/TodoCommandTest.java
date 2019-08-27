import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void execute_void_addsTaskToTaskList () throws DukeException {
        TodoCommand c = new TodoCommand("todo test this method");
        TaskList t = new TaskList();
        c.execute(t, new Ui(), new Storage("testPath"));
        assertEquals(t.getTaskAtIndex(0).toString(), "[T][✘] test this method");
    }
}
