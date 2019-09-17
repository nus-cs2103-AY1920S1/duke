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
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand("todo");
        addCommand.executeCommand(taskList, null);
        assertEquals(outContent.toString(), "☹ OOPS!!! The description of a todo cannot be empty.\n");
    }


}
