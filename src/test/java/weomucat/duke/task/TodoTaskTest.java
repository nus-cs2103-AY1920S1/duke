package weomucat.duke.task;

import org.junit.jupiter.api.Test;
import weomucat.duke.exception.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoTaskTest {
	@Test
	public void descriptionShouldNotBeEmptyString() {
		assertThrows(InvalidParameterException.class, () -> new TodoTask(""), formatMessage(""));
	}

	@Test
	public void validUsage() {
		String[] descriptions = {"one", "one two", "one two three"};

		for (String description : descriptions) {
			assertDoesNotThrow(() -> new TodoTask(description), formatMessage(description));
		}
	}

	private String formatMessage(String description) {
		return String.format("Input: new TodoTask('%s')", description);
	}
}
