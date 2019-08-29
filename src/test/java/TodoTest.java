import org.junit.jupiter.api.Test;
import main.java.tasks.Todo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
	@Test
	public void toStringTest(){
		Todo todo = new Todo("work");
		assertEquals(todo.toString(), "[T][0]work");
	}
}

