package weomucat.duke.task;

import org.junit.jupiter.api.Test;
import weomucat.duke.exception.InvalidTaskIndexException;
import weomucat.duke.task.listener.StubAddTaskListener;
import weomucat.duke.task.listener.StubDeleteTaskListener;
import weomucat.duke.task.listener.StubFindTaskListener;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
	@Test
	public void deleteTaskShouldNotBeInvalidIndex() {
		TaskList taskList = new TaskList();
		assertThrows(InvalidTaskIndexException.class, () -> taskList.deleteTask(0));

		assertDoesNotThrow(() -> taskList.addTask(new StubTask("one")));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.deleteTask(-1));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.deleteTask(1));

		assertDoesNotThrow(() -> taskList.addTask(new StubTask("two")));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.deleteTask(-1));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.deleteTask(2));

		assertDoesNotThrow(() -> taskList.addTask(new StubTask("three")));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.deleteTask(-1));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.deleteTask(3));
	}

	@Test
	public void doneTaskShouldNotBeInvalidIndex() {
		TaskList taskList = new TaskList();
		assertThrows(InvalidTaskIndexException.class, () -> taskList.doneTask(0));

		assertDoesNotThrow(() -> taskList.addTask(new StubTask("one")));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.doneTask(-1));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.doneTask(1));

		assertDoesNotThrow(() -> taskList.addTask(new StubTask("two")));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.doneTask(-1));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.doneTask(2));

		assertDoesNotThrow(() -> taskList.addTask(new StubTask("three")));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.doneTask(-1));
		assertThrows(InvalidTaskIndexException.class, () -> taskList.doneTask(3));
	}

	@Test
	public void validAddUsage() {
		StubTask taskOne = new StubTask("one");
		StubTask taskTwo = new StubTask("two");
		StubTask taskThree = new StubTask("three");

		TaskList taskList = new TaskList();
		StubAddTaskListener addTaskListener = new StubAddTaskListener();

		// Initialize Listeners
		taskList.newAddTaskListener(addTaskListener);

		/* Add Tasks */
		// Expect 1 task.
		assertDoesNotThrow(() -> taskList.addTask(taskOne));
		assertEquals(1, addTaskListener.getTasks().size());

		// Validate that the tasks are correct
		assertEquals(taskOne, addTaskListener.getTask());
		assertTrue(addTaskListener.getTasks().contains(taskOne));

		// Expect 2 tasks.
		assertDoesNotThrow(() -> taskList.addTask(taskTwo));
		assertEquals(2, addTaskListener.getTasks().size());

		// Validate that the tasks are correct
		assertEquals(taskTwo, addTaskListener.getTask());
		assertTrue(addTaskListener.getTasks().contains(taskOne));
		assertTrue(addTaskListener.getTasks().contains(taskTwo));

		// Expect 3 tasks.
		assertDoesNotThrow(() -> taskList.addTask(taskThree));
		assertEquals(3, addTaskListener.getTasks().size());

		// Validate that the tasks are correct
		assertEquals(taskThree, addTaskListener.getTask());
		assertTrue(addTaskListener.getTasks().contains(taskOne));
		assertTrue(addTaskListener.getTasks().contains(taskTwo));
		assertTrue(addTaskListener.getTasks().contains(taskThree));
	}

	@Test
	public void validDeleteUsage() {
		StubTask taskOne = new StubTask("one");
		StubTask taskTwo = new StubTask("two");
		StubTask taskThree = new StubTask("three");

		TaskList taskList = new TaskList();
		StubDeleteTaskListener deleteTaskListener = new StubDeleteTaskListener();

		// Initialize Listeners
		taskList.newDeleteTaskListener(deleteTaskListener);

		/* Add Tasks */
		assertDoesNotThrow(() -> taskList.addTask(taskOne));
		assertDoesNotThrow(() -> taskList.addTask(taskTwo));
		assertDoesNotThrow(() -> taskList.addTask(taskThree));

		/* Delete Tasks */
		// Expect 2 tasks.
		assertDoesNotThrow(() -> taskList.deleteTask(0));
		assertEquals(2, deleteTaskListener.getTasks().size());

		// Validate that the tasks are correct
		assertEquals(taskOne, deleteTaskListener.getTask());
		assertTrue(deleteTaskListener.getTasks().contains(taskTwo));
		assertTrue(deleteTaskListener.getTasks().contains(taskThree));

		// Expect 1 task.
		assertDoesNotThrow(() -> taskList.deleteTask(0));
		assertEquals(1, deleteTaskListener.getTasks().size());

		// Validate that the tasks are correct
		assertEquals(taskTwo, deleteTaskListener.getTask());
		assertTrue(deleteTaskListener.getTasks().contains(taskThree));

		// Expect 0 tasks.
		assertDoesNotThrow(() -> taskList.deleteTask(0));
		assertEquals(0, deleteTaskListener.getTasks().size());

		// Validate that the tasks are correct
		assertEquals(taskThree, deleteTaskListener.getTask());
	}

	@Test
	public void validFindUsage() {
		StubTask taskOne = new StubTask("one");
		StubTask taskTwo = new StubTask("two");
		StubTask taskThree = new StubTask("three");

		TaskList taskList = new TaskList();
		StubFindTaskListener findTaskListener = new StubFindTaskListener();

		// Initialize Listeners
		taskList.newFindTaskListener(findTaskListener);

		/* Add Tasks */
		assertDoesNotThrow(() -> taskList.addTask(taskOne));
		assertDoesNotThrow(() -> taskList.addTask(taskTwo));
		assertDoesNotThrow(() -> taskList.addTask(taskThree));

		/* Find Tasks */
		// Empty string should return all tasks.
		taskList.findTask("");
		assertTrue(findTaskListener.getTasks().contains(taskOne));
		assertTrue(findTaskListener.getTasks().contains(taskTwo));
		assertTrue(findTaskListener.getTasks().contains(taskThree));

		// Character should return tasks with that character.
		taskList.findTask("o");
		assertTrue(findTaskListener.getTasks().contains(taskOne));
		assertTrue(findTaskListener.getTasks().contains(taskTwo));

		taskList.findTask("t");
		assertTrue(findTaskListener.getTasks().contains(taskTwo));
		assertTrue(findTaskListener.getTasks().contains(taskThree));

		// Should be case insensitive.
		taskList.findTask("One");
		assertTrue(findTaskListener.getTasks().contains(taskOne));

		taskList.findTask("tWo");
		assertTrue(findTaskListener.getTasks().contains(taskTwo));

		taskList.findTask("thREE");
		assertTrue(findTaskListener.getTasks().contains(taskThree));
	}
}
