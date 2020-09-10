import duke.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
	TaskList list = new TaskList();
	
	@Test
	public void taskList_indexOutofBound_throwException() {
		//Duke duke = new Duke("C:\\Users\\user\\Desktop\\CS2103_Git\\duke\\data\\tasks.txt");
		//duke.run();
		assertThrows(IndexOutOfBoundsException.class, () -> list.deleteTask(10));
	}
}
