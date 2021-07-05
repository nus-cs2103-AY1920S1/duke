package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.person.PersonList;

public class TaskTest {
    @Test
    public void descriptionListConstructorTest() {
        Task task = new Task("des", null);
        String description = task.getDescription();
        Boolean isDone = task.getStatus();
        PersonList pl = task.getPersonList();
        assertEquals("des", description);
        assertEquals(false, isDone);
        assertEquals(null, pl);
    }

    @Test
    public void changeStatusTest() {
        Task task = new Task();
        task.changeStatus();
        Boolean isDone = task.getStatus();
        assertEquals(true, isDone);
    }

    @Test
    public void toStringTest() {
        Task task = new Task("des", null);
        String str = task.toString();
        assertEquals("[-]des", str);
    }

}
