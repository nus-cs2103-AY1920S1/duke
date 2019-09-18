package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {
    @Test
    void addAndGet_validIndex_success() {
        TaskList taskList = new TaskList();
        Task task = new TaskImpl("desc");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
    }

    @Test
    void size_withTasks_success() {
        TaskList taskList = new TaskList();
        taskList.add(new TaskImpl("task1"));
        taskList.add(new TaskImpl("task2"));
        assertEquals(2, taskList.size());
    }

    @Test
    void delete_validIndex_success() {
        TaskList taskList = new TaskList();
        Task task1 = new TaskImpl("task1");
        Task task2 = new TaskImpl("task2");
        taskList.add(task1);
        taskList.add(task2);
        assertEquals(task1, taskList.remove(0));
        assertEquals(task2, taskList.get(0));
    }

    @Test
    void getAsLines_normalTasks_success() {
        ToDo todo = new ToDo("todo");
        Deadline deadline = new Deadline("deadline", "27/7/2019 1111");
        Event event = new Event("event", "28/8/2019 1234");
        TaskList taskList = new TaskList();
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);
        TaskList loadList = new TaskList(taskList.getAsLines());
        assertEquals(todo.toString(), loadList.get(0).toString());
        assertEquals(deadline.toString(), loadList.get(1).toString());
        assertEquals(event.toString(), loadList.get(2).toString());
    }
}
