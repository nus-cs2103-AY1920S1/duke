import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import main.java.tasks.Task;

public class TaskTest {
	@Test
	public void toStringTest(){
		Task task = new Task("homework");
		task.complete();
		assertEquals(task.toString(), "[1]homework");
	}
}
