package command;

import org.junit.jupiter.api.Test;
import task.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    public void testAddTodo_successfullyAdd() {
        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand("todo borrow book");
        addCommand.executeCommand(taskList, null);
        assertEquals("[T][✘] borrow book", taskList.getTask(0).toString());
    }

    @Test
    public void testAddTodoEmpty_EmptyMessage() {
        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand("todo");
        String result = addCommand.executeCommand(taskList, null);
        assertEquals(result, "☹ OOPS!!! The description of a todo cannot be empty.\n");
    }

}
