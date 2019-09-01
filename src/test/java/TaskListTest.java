import cs2103t.duke.task.Task;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    String divider = "    -------------------------------------------------------------\r\n";
    List<Task> tasks;
    TaskList taskList;

    @BeforeEach
    public void init() {
        tasks = new ArrayList<>();
        tasks.add(new TaskStub());
        taskList = new TaskList(tasks);
    }

    @Test
    public void listTaskTest() {
        //i dont think Ui need to test cus its so simple
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(outContent, false, "UTF-8"));
            //after this all Sop will go out here?
            Ui ui = new Ui();
            String expectedOut = divider
                    + "     Here are the tasks in your list:\r\n"
                    + "     1.[T][✗] some description\r\n"
                    + divider;
            taskList.listTasks(ui);
            assertEquals(expectedOut, outContent.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); //shouldnt happen
        }
    }

    @Test
    public void addDataTest() {
        taskList.addData(new TaskStub());
        assertEquals("[T][\u2717] some description",
                taskList.getTaskList().get(taskList.getSize() - 1).toString());
    }

    @Test
    public void markDoneTest_validId() {
        try {
            Task doneTask = taskList.markDone(1);
            assertEquals("[T][\u2713] some description",
                    doneTask.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void markDoneTest_invalidId_throwsException() {
        try {
            taskList.markDone(5);
            fail();
        } catch (Exception e) {
            assertEquals("5", e.getMessage());
        }
    }

    @Test
    public void deleteTaskTest_validId() {
        try {
            Task deletedTask = taskList.deleteTask(1);
            assertEquals("[T][\u2717] some description",
                    deletedTask.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteTaskTest_InvalidId() {
        try {
            taskList.deleteTask(2);
            fail();
        } catch (Exception e) {
            assertEquals("2", e.getMessage());
        }
    }

    @Test
    public void createTask_invalidType_throwException() {
        try {
            TaskList.createTask(null, "");
            fail();
        } catch (Exception e) {
            assertEquals("wrong keyword", e.getMessage());
        }
    }
}
