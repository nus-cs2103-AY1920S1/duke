import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaskListTest {

	@Test
	void test() {
		TaskList tasklist = new TaskList();
		tasklist.addTodo("test");
		String testing = tasklist.listLatest();
		String result = "[T][" + "\u2718" + "] test";
		assertEquals(result, testing);
	}

}
