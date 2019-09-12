package weomucat.duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import weomucat.duke.Pair;
import weomucat.duke.exception.InvalidIndexException;
import weomucat.duke.task.listener.StubListTaskListener;
import weomucat.duke.task.listener.StubModifyTaskListener;
import weomucat.duke.task.listener.StubTaskListSizeListener;

public class TaskManagerTest {

  @Test
  public void deleteTaskShouldNotBeInvalidIndex() {
    TaskManager taskManager = new TaskManager();
    assertThrows(InvalidIndexException.class, () -> taskManager.deleteTask(0));

    assertDoesNotThrow(() -> taskManager.addTask(new StubTask("one")));
    assertThrows(InvalidIndexException.class, () -> taskManager.deleteTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskManager.deleteTask(1));

    assertDoesNotThrow(() -> taskManager.addTask(new StubTask("two")));
    assertThrows(InvalidIndexException.class, () -> taskManager.deleteTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskManager.deleteTask(2));

    assertDoesNotThrow(() -> taskManager.addTask(new StubTask("three")));
    assertThrows(InvalidIndexException.class, () -> taskManager.deleteTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskManager.deleteTask(3));
  }

  @Test
  public void doneTaskShouldNotBeInvalidIndex() {
    TaskManager taskManager = new TaskManager();
    assertThrows(InvalidIndexException.class, () -> taskManager.doneTask(0));

    assertDoesNotThrow(() -> taskManager.addTask(new StubTask("one")));
    assertThrows(InvalidIndexException.class, () -> taskManager.doneTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskManager.doneTask(1));

    assertDoesNotThrow(() -> taskManager.addTask(new StubTask("two")));
    assertThrows(InvalidIndexException.class, () -> taskManager.doneTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskManager.doneTask(2));

    assertDoesNotThrow(() -> taskManager.addTask(new StubTask("three")));
    assertThrows(InvalidIndexException.class, () -> taskManager.doneTask(-1));
    assertThrows(InvalidIndexException.class, () -> taskManager.doneTask(3));
  }

  @Test
  public void validAddUsage() {
    final StubTask taskOne = new StubTask("one");
    final StubTask taskTwo = new StubTask("two");
    final StubTask taskThree = new StubTask("three");

    TaskManager taskManager = new TaskManager();
    StubListTaskListener listTaskListener = new StubListTaskListener();
    StubModifyTaskListener modifyTaskListener = new StubModifyTaskListener();
    StubTaskListSizeListener taskListSizeListener = new StubTaskListSizeListener();

    // Initialize Listeners
    taskManager.newListTaskListener(listTaskListener);
    taskManager.newModifyTaskListener(modifyTaskListener);
    taskManager.newTaskListSizeListener(taskListSizeListener);

    /* Add Tasks */
    // Expect 1 task.
    assertDoesNotThrow(() -> taskManager.addTask(taskOne));
    assertEquals(1, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskOne, modifyTaskListener.getTask());

    // Expect 2 tasks.
    assertDoesNotThrow(() -> taskManager.addTask(taskTwo));
    assertEquals(2, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskTwo, modifyTaskListener.getTask());
    taskManager.listTasks();
    Pair<Integer, Task> pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.getKey());
    assertEquals(taskOne, pair.getValue());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(2, pair.getKey());
    assertEquals(taskTwo, pair.getValue());

    // Expect 3 tasks.
    assertDoesNotThrow(() -> taskManager.addTask(taskThree));
    assertEquals(3, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskThree, modifyTaskListener.getTask());
    taskManager.listTasks();
    pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.getKey());
    assertEquals(taskOne, pair.getValue());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(2, pair.getKey());
    assertEquals(taskTwo, pair.getValue());
    pair = listTaskListener.getTasks().get(2);
    assertEquals(3, pair.getKey());
    assertEquals(taskThree, pair.getValue());
  }

  @Test
  public void validDeleteUsage() {
    final StubTask taskOne = new StubTask("one");
    final StubTask taskTwo = new StubTask("two");
    final StubTask taskThree = new StubTask("three");

    TaskManager taskManager = new TaskManager();
    StubListTaskListener listTaskListener = new StubListTaskListener();
    StubModifyTaskListener modifyTaskListener = new StubModifyTaskListener();
    StubTaskListSizeListener taskListSizeListener = new StubTaskListSizeListener();

    // Initialize Listeners
    taskManager.newListTaskListener(listTaskListener);
    taskManager.newModifyTaskListener(modifyTaskListener);
    taskManager.newTaskListSizeListener(taskListSizeListener);

    /* Add Tasks */
    assertDoesNotThrow(() -> taskManager.addTask(taskOne));
    assertDoesNotThrow(() -> taskManager.addTask(taskTwo));
    assertDoesNotThrow(() -> taskManager.addTask(taskThree));

    /* Delete Tasks */
    // Expect 2 tasks.
    assertDoesNotThrow(() -> taskManager.deleteTask(0));
    assertEquals(2, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskOne, modifyTaskListener.getTask());
    taskManager.listTasks();
    Pair<Integer, Task> pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.getKey());
    assertEquals(taskTwo, pair.getValue());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(2, pair.getKey());
    assertEquals(taskThree, pair.getValue());

    // Expect 1 task.
    assertDoesNotThrow(() -> taskManager.deleteTask(0));
    assertEquals(1, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskTwo, modifyTaskListener.getTask());
    taskManager.listTasks();
    pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.getKey());
    assertEquals(taskThree, pair.getValue());

    // Expect 0 tasks.
    assertDoesNotThrow(() -> taskManager.deleteTask(0));
    assertEquals(0, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskThree, modifyTaskListener.getTask());
  }

  @Test
  public void validFindUsage() {
    final StubTask taskOne = new StubTask("one");
    final StubTask taskTwo = new StubTask("two");
    final StubTask taskThree = new StubTask("three");

    TaskManager taskManager = new TaskManager();
    StubListTaskListener listTaskListener = new StubListTaskListener();

    // Initialize Listeners
    taskManager.newListTaskListener(listTaskListener);

    /* Add Tasks */
    assertDoesNotThrow(() -> taskManager.addTask(taskOne));
    assertDoesNotThrow(() -> taskManager.addTask(taskTwo));
    assertDoesNotThrow(() -> taskManager.addTask(taskThree));

    /* Find Tasks */
    // Empty string should return all tasks.
    taskManager.findTask("");
    Pair<Integer, Task> pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.getKey());
    assertEquals(taskOne, pair.getValue());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(2, pair.getKey());
    assertEquals(taskTwo, pair.getValue());
    pair = listTaskListener.getTasks().get(2);
    assertEquals(3, pair.getKey());
    assertEquals(taskThree, pair.getValue());

    // Character should return tasks with that character.
    taskManager.findTask("o");
    pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.getKey());
    assertEquals(taskOne, pair.getValue());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(2, pair.getKey());
    assertEquals(taskTwo, pair.getValue());

    taskManager.findTask("t");
    pair = listTaskListener.getTasks().get(0);
    assertEquals(2, pair.getKey());
    assertEquals(taskTwo, pair.getValue());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(3, pair.getKey());
    assertEquals(taskThree, pair.getValue());

    // Should be case insensitive.
    taskManager.findTask("One");
    pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.getKey());
    assertEquals(taskOne, pair.getValue());

    taskManager.findTask("tWo");
    pair = listTaskListener.getTasks().get(0);
    assertEquals(2, pair.getKey());
    assertEquals(taskTwo, pair.getValue());

    taskManager.findTask("thREE");
    pair = listTaskListener.getTasks().get(0);
    assertEquals(3, pair.getKey());
    assertEquals(taskThree, pair.getValue());
  }
}
