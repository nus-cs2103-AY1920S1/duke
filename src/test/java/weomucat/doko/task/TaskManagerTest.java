package weomucat.doko.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weomucat.doko.Pair;
import weomucat.doko.exception.InvalidIndexException;
import weomucat.doko.task.listener.ListTaskListener;
import weomucat.doko.task.listener.ModifyTaskListener;
import weomucat.doko.task.listener.StubListTaskListener;
import weomucat.doko.task.listener.StubModifyTaskListener;
import weomucat.doko.task.listener.StubTaskListSizeListener;
import weomucat.doko.task.listener.TaskListSizeListener;

class TaskManagerTest {

  @Test
  void deleteTaskShouldNotBeInvalidIndex() {
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
  void doneTaskShouldNotBeInvalidIndex() {
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
  void validAddUsage() {
    final StubTask taskOne = new StubTask("one");
    final StubTask taskTwo = new StubTask("two");
    final StubTask taskThree = new StubTask("three");

    TaskManager taskManager = new TaskManager();
    StubListTaskListener listTaskListener = new StubListTaskListener();
    StubModifyTaskListener modifyTaskListener = new StubModifyTaskListener();
    StubTaskListSizeListener taskListSizeListener = new StubTaskListSizeListener();

    // Initialize Listeners
    taskManager.addListener(ListTaskListener.class, listTaskListener);
    taskManager.addListener(ModifyTaskListener.class, modifyTaskListener);
    taskManager.addListener(TaskListSizeListener.class, taskListSizeListener);

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
    taskManager.listOngoingTasks();
    Pair<Integer, Task> pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.key());
    assertEquals(taskOne, pair.value());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(2, pair.key());
    assertEquals(taskTwo, pair.value());

    // Expect 3 tasks.
    assertDoesNotThrow(() -> taskManager.addTask(taskThree));
    assertEquals(3, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskThree, modifyTaskListener.getTask());
    taskManager.listOngoingTasks();
    pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.key());
    assertEquals(taskOne, pair.value());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(2, pair.key());
    assertEquals(taskTwo, pair.value());
    pair = listTaskListener.getTasks().get(2);
    assertEquals(3, pair.key());
    assertEquals(taskThree, pair.value());
  }

  @Test
  void validDeleteUsage() {
    final StubTask taskOne = new StubTask("one");
    final StubTask taskTwo = new StubTask("two");
    final StubTask taskThree = new StubTask("three");

    TaskManager taskManager = new TaskManager();
    StubListTaskListener listTaskListener = new StubListTaskListener();
    StubModifyTaskListener modifyTaskListener = new StubModifyTaskListener();
    StubTaskListSizeListener taskListSizeListener = new StubTaskListSizeListener();

    // Initialize Listeners
    taskManager.addListener(ListTaskListener.class, listTaskListener);
    taskManager.addListener(ModifyTaskListener.class, modifyTaskListener);
    taskManager.addListener(TaskListSizeListener.class, taskListSizeListener);

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
    taskManager.listOngoingTasks();
    Pair<Integer, Task> pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.key());
    assertEquals(taskTwo, pair.value());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(2, pair.key());
    assertEquals(taskThree, pair.value());

    // Expect 1 task.
    assertDoesNotThrow(() -> taskManager.deleteTask(0));
    assertEquals(1, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskTwo, modifyTaskListener.getTask());
    taskManager.listOngoingTasks();
    pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.key());
    assertEquals(taskThree, pair.value());

    // Expect 0 tasks.
    assertDoesNotThrow(() -> taskManager.deleteTask(0));
    assertEquals(0, taskListSizeListener.getSize());

    // Validate that the tasks are correct
    assertEquals(taskThree, modifyTaskListener.getTask());
  }

  @Test
  void validFindUsage() {
    final StubTask taskOne = new StubTask("one");
    final StubTask taskTwo = new StubTask("two");
    final StubTask taskThree = new StubTask("three");

    TaskManager taskManager = new TaskManager();
    StubListTaskListener listTaskListener = new StubListTaskListener();

    // Initialize Listeners
    taskManager.addListener(ListTaskListener.class, listTaskListener);

    /* Add Tasks */
    assertDoesNotThrow(() -> taskManager.addTask(taskOne));
    assertDoesNotThrow(() -> taskManager.addTask(taskTwo));
    assertDoesNotThrow(() -> taskManager.addTask(taskThree));

    /* Find Tasks */
    // Empty string should return all tasks.
    taskManager.findTask("");
    Pair<Integer, Task> pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.key());
    assertEquals(taskOne, pair.value());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(2, pair.key());
    assertEquals(taskTwo, pair.value());
    pair = listTaskListener.getTasks().get(2);
    assertEquals(3, pair.key());
    assertEquals(taskThree, pair.value());

    // Character should return tasks with that character.
    taskManager.findTask("o");
    pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.key());
    assertEquals(taskOne, pair.value());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(2, pair.key());
    assertEquals(taskTwo, pair.value());

    taskManager.findTask("t");
    pair = listTaskListener.getTasks().get(0);
    assertEquals(2, pair.key());
    assertEquals(taskTwo, pair.value());
    pair = listTaskListener.getTasks().get(1);
    assertEquals(3, pair.key());
    assertEquals(taskThree, pair.value());

    // Should be case insensitive.
    taskManager.findTask("One");
    pair = listTaskListener.getTasks().get(0);
    assertEquals(1, pair.key());
    assertEquals(taskOne, pair.value());

    taskManager.findTask("tWo");
    pair = listTaskListener.getTasks().get(0);
    assertEquals(2, pair.key());
    assertEquals(taskTwo, pair.value());

    taskManager.findTask("thREE");
    pair = listTaskListener.getTasks().get(0);
    assertEquals(3, pair.key());
    assertEquals(taskThree, pair.value());
  }
}
