import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
	@Test
	public void emptySizdList() {
		assertEquals(new TaskList().getTaskListSize(), 0);
	}
}
