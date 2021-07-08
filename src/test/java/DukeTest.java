import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.logic.DukeException;
import duke.logic.Verify;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.trivia.QuestionList;
public class DukeTest{
    TaskList taskList = new TaskList();
    QuestionList qlist = new QuestionList();

    @Test
    public void testAddGet(){
        taskList.add(new Todo("eat salad", 0));
        assertEquals(taskList.get(0), new Todo("eat salad", 0));
    }
    @Test
    public void testRemove(){
        Task t = taskList.remove(0);
        assertEquals(t, new Todo("eat salad", 0));
    }
    @Test
    public void testVerify(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        assertThrows(DukeException.class, () -> Verify.checkCommandValidity("blah", taskList, formatter, qlist));
    }
}