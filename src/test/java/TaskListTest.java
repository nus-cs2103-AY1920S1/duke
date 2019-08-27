import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private ArrayList<Task> list = new ArrayList<>();
    private Task one = new ToDo("read book");
    private Task two = new Deadline("return book", "June 6th");
    private Task three = new Event("project meeting", "Aug 6th 2-4pm");
    private TaskList tasks = new TaskList();


    @Test
    void get() {
        list.add(one);
        list.add(two);
        list.add(three);
        tasks = new TaskList(list);

        Task check = tasks.get(1);
        assertEquals(two, check);
    }

    @Test
    void getSize() {
        list.add(one);
        list.add(two);
        list.add(three);
        tasks = new TaskList(list);

        int size = tasks.getSize();
        assertEquals(3, size);
    }

    @Test
    void getList() {
        list.add(one);
        list.add(two);
        list.add(three);
        tasks = new TaskList(list);
        // Set string as CRLF line separators
        String check = "     1.[T][✘] read book\r\n" +
                "     2.[D][✘] return book (by: June 6th)\r\n" +
                "     3.[E][✘] project meeting (at: Aug 6th 2-4pm)\r\n";

        // Get print statement as String
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream sout = System.out;
        System.setOut(ps);

        tasks.getList();

        System.out.flush();
        System.setOut(sout);

        assertEquals(check, baos.toString()); //Difference in line separators (CRLF works)

    }

    @Test
    void doneTask() {
        list.add(one);
        list.add(two);
        list.add(three);
        tasks = new TaskList(list);
        String notDone = "[D][✘] return book (by: June 6th)";
        String done = "[D][✓] return book (by: June 6th)";

        assertEquals(notDone, two.toString()); // Before marked as done
        tasks.doneTask("done 2");
        assertEquals(done, two.toString()); // Marked as done
    }

    @Test
    void deleteTask() {
        list.add(one);
        list.add(two);
        list.add(three);
        tasks = new TaskList(list);
        String toDelete = "delete 2"; // delete 2nd on the list (index 1)

        tasks.deleteTask(toDelete);
        Task check = tasks.get(1);
        assertEquals(check, three);
    }

    @Test
    void addTask() {
        list.add(one);
        list.add(two);
        list.add(three);
        tasks = new TaskList(list);

        String toAdd = "todo go for classes"; // delete 2nd on the list (index 1)
        ToDo toAddTask = new ToDo("go for classes");

        tasks.addTask("todo", toAdd);
        Task check = tasks.get(3);
        assertEquals(check.toString(), toAddTask.toString());
    }
}