package command;

import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {

    @Test
    public void testDelete_succesfullyDelete() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("todo borrow book"));
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
        deleteCommand.executeCommand(taskList, null);
        assertEquals(taskList.getTasks().size(), 0);
    }

    @Test
    public void testDelete_EmptyEntry_returnEmptyMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TaskList taskList = new TaskList();
        DeleteCommand deleteCommand = new DeleteCommand("delete");
        String result = deleteCommand.executeCommand(taskList, null);
        assertEquals(result, "☹ OOPS!!! You cannot delete an empty entry.\n");
    }

    @Test
    public void testDelete_Outofbound_outofboundMessage() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("todo borrow book"));
        DeleteCommand deleteCommand = new DeleteCommand("delete 2");
        String result = deleteCommand.executeCommand(taskList, null);
        assertEquals(result, "☹ OOPS!!! Out of range, the task does not exist.\n");
    }

}
