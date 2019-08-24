import org.junit.jupiter.api.Test;

import java.util.List;

class ListCommandTest extends CommandTest {
    @Test
    void listTasks() {
        List<Task> tasks = List.of(new Todo("a thing that really needs to be done", false));
        command = new ListCommand();
        command.execute(tasks, ui, store);

        assertFileContents(null);
        assertStdOutContents("\t____________________________________________________________\n"
                + "\tHere are the tasks in your list:\n"
                + "\t1.[T][✘] a thing that really needs to be done\n"
                + "\t____________________________________________________________\n");
        assertExit(false);
    }

    @Test
    void listEmptyTaskList() {
        List<Task> emptyTaskList = List.of();
        command = new ListCommand();
        command.execute(emptyTaskList, ui, store);

        assertFileContents(null);
        assertStdOutContents("\t____________________________________________________________\n"
                + "\tHere are the tasks in your list:\n"
                + "\t____________________________________________________________\n");
        assertExit(false);
    }

}