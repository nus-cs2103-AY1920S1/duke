package weomucat.duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import weomucat.duke.exception.InvalidIndexException;
import weomucat.duke.task.listener.StubListTaskListener;
import weomucat.duke.task.listener.StubModifyTaskListener;
import weomucat.duke.task.listener.StubTaskListSizeListener;

public class TaskListTest {

  @Test
  public void deleteTaskShouldNotBeInvalidIndex() {
    TaskList taskList = new TaskList();
    assertThrows(InvalidIndexException.class, () -> taskList.deleteTask(0));

    assertDoesNotThrow(() -> taskList.addTask(new StubTask("one")));
    assertThrows(InvalidIndexException.class, () -> taskList.deleteTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskList.deleteTask(1));

    assertDoesNotThrow(() -> taskList.addTask(new StubTask("two")));
    assertThrows(InvalidIndexException.class, () -> taskList.deleteTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskList.deleteTask(2));

    assertDoesNotThrow(() -> taskList.addTask(new StubTask("three")));
    assertThrows(InvalidIndexException.class, () -> taskList.deleteTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskList.deleteTask(3));
  }

  @Test
  public void doneTaskShouldNotBeInvalidIndex() {
    TaskList taskList = new TaskList();
    assertThrows(InvalidIndexException.class, () -> taskList.doneTask(0));

    assertDoesNotThrow(() -> taskList.addTask(new StubTask("one")));
    assertThrows(InvalidIndexException.class, () -> taskList.doneTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskList.doneTask(1));

    assertDoesNotThrow(() -> taskList.addTask(new StubTask("two")));
    assertThrows(InvalidIndexException.class, () -> taskList.doneTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskList.doneTask(2));

    assertDoesNotThrow(() -> taskList.addTask(new StubTask("three")));
    assertThrows(InvalidIndexException.class, () -> taskList.doneTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskList.doneTask(3));
  }

  @Test
  public void validAddUsage() {
    final StubTask taskOne = new StubTask("one");
    final StubTask taskTwo = new StubTask("two");
    final StubTask taskThree = new StubTask("three");

    TaskList taskList = new TaskList();
    StubListTaskListener listTaskListener = new StubListTaskListener();
    StubModifyTaskListener modifyTaskListener = new StubModifyTaskListener();
    StubTaskListSizeListener taskListSizeListener = new StubTaskListSizeListener();

    // Initialize Listeners
    taskList.newListTaskListener(listTaskListener);
    taskList.newModifyTaskListener(modifyTaskListener);
    taskList.newTaskListSizeListener(taskListSizeListener);

    /* Add Tasks */
    // Expect 1 task.
    assertDoesNotThrow(() -> taskList.addTask(taskOne));
    assertEquals(1, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskOne, modifyTaskListener.getTask());

    // Expect 2 tasks.
    assertDoesNotThrow(() -> taskList.addTask(taskTwo));
    assertEquals(2, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskTwo, modifyTaskListener.getTask());
    taskList.listTask();
    assertTrue(listTaskListener.getTasks().contains(taskOne));
    assertTrue(listTaskListener.getTasks().contains(taskTwo));

    // Expect 3 tasks.
    assertDoesNotThrow(() -> taskList.addTask(taskThree));
    assertEquals(3, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskThree, modifyTaskListener.getTask());
    taskList.listTask();
    assertTrue(listTaskListener.getTasks().contains(taskOne));
    assertTrue(listTaskListener.getTasks().contains(taskTwo));
    assertTrue(listTaskListener.getTasks().contains(taskThree));
  }

  @Test
  public void validDeleteUsage() {
    final StubTask taskOne = new StubTask("one");
    final StubTask taskTwo = new StubTask("two");
    final StubTask taskThree = new StubTask("three");

    TaskList taskList = new TaskList();
    StubListTaskListener listTaskListener = new StubListTaskListener();
    StubModifyTaskListener modifyTaskListener = new StubModifyTaskListener();
    StubTaskListSizeListener taskListSizeListener = new StubTaskListSizeListener();

    // Initialize Listeners
    taskList.newListTaskListener(listTaskListener);
    taskList.newModifyTaskListener(modifyTaskListener);
    taskList.newTaskListSizeListener(taskListSizeListener);

    /* Add Tasks */
    assertDoesNotThrow(() -> taskList.addTask(taskOne));
    assertDoesNotThrow(() -> taskList.addTask(taskTwo));
    assertDoesNotThrow(() -> taskList.addTask(taskThree));

    /* Delete Tasks */
    // Expect 2 tasks.
    assertDoesNotThrow(() -> taskList.deleteTask(0));
    assertEquals(2, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskOne, modifyTaskListener.getTask());
    taskList.listTask();
    assertTrue(listTaskListener.getTasks().contains(taskTwo));
    assertTrue(listTaskListener.getTasks().contains(taskThree));

    // Expect 1 task.
    assertDoesNotThrow(() -> taskList.deleteTask(0));
    assertEquals(1, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskTwo, modifyTaskListener.getTask());
    taskList.listTask();
    assertTrue(listTaskListener.getTasks().contains(taskThree));

    // Expect 0 tasks.
    assertDoesNotThrow(() -> taskList.deleteTask(0));
    assertEquals(0, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskThree, modifyTaskListener.getTask());
  }

  @Test
  public void validFindUsage() {
    final StubTask taskOne = new StubTask("one");
    final StubTask taskTwo = new StubTask("two");
    final StubTask taskThree = new StubTask("three");

    TaskList taskList = new TaskList();
    StubListTaskListener listTaskListener = new StubListTaskListener();

    // Initialize Listeners
    taskList.newListTaskListener(listTaskListener);

    /* Add Tasks */
    assertDoesNotThrow(() -> taskList.addTask(taskOne));
    assertDoesNotThrow(() -> taskList.addTask(taskTwo));
    assertDoesNotThrow(() -> taskList.addTask(taskThree));

    /* Find Tasks */
    // Empty string should return all tasks.
    taskList.findTask("");
    assertTrue(listTaskListener.getTasks().contains(taskOne));
    assertTrue(listTaskListener.getTasks().contains(taskTwo));
    assertTrue(listTaskListener.getTasks().contains(taskThree));

    // Character should return tasks with that character.
    taskList.findTask("o");
    assertTrue(listTaskListener.getTasks().contains(taskOne));
    assertTrue(listTaskListener.getTasks().contains(taskTwo));

    taskList.findTask("t");
    assertTrue(listTaskListener.getTasks().contains(taskTwo));
    assertTrue(listTaskListener.getTasks().contains(taskThree));

    // Should be case insensitive.
    taskList.findTask("One");
    assertTrue(listTaskListener.getTasks().contains(taskOne));

    taskList.findTask("tWo");
    assertTrue(listTaskListener.getTasks().contains(taskTwo));

    taskList.findTask("thREE");
    assertTrue(listTaskListener.getTasks().contains(taskThree));
  }
}
