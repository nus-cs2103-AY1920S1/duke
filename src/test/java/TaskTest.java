import org.junit.jupiter.api.Test;
import SerSnapsalot.task.Task;
import SerSnapsalot.task.Event;
import SerSnapsalot.task.ToDo;
import SerSnapsalot.task.Deadline;

public class TaskTest {
	@Test
	void taskTest() {
		String description = "description";
		String time = "29/12/2018 2359";
		Task newTask = new Task(description);
		Task toDo = new ToDo(description);
		Task event = new Event(description + "/at" + time);
		Task deadline = new Deadline(description + "/by" + time);
		System.out.println("---- Test: Tasks can be initialized");
	}
}
