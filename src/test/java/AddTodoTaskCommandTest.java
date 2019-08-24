import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class AddTodoTaskCommandTest extends CommandTest {

    @Test
    void addValidTodo() {
        List<Task> expectedTasks = List.of(
                new Todo("a thing that needs to be done", false)
        );
        List<Task> actualTasks = mutableTaskListOf();

        String expectedFileContents = "T\u001F0\u001Fa thing that needs to be done";

        String expectedSysOut = "\t____________________________________________________________\n"
                + "\tGot it. I've added this task: \n"
                + "\t  [T][✘] a thing that needs to be done\n"
                + "\tNow you have 1 tasks in the list.\n"
                + "\t____________________________________________________________\n\n";

        command = new AddTodoTaskCommand("a thing that needs to be done");
        command.execute(actualTasks, ui, store);

        Assertions.assertEquals(expectedTasks, actualTasks);
        assertFileContents(expectedFileContents);
        assertStdOutContents(expectedSysOut);
        assertExit(false);
    }

    @Test
    void addTodoMissingDescription() {
        Assertions.assertThrows(EmptyTaskDescriptionException.class, () -> {
            new AddTodoTaskCommand("");
        });
    }

}