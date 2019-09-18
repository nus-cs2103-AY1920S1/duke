package task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void newInstance_sameTaskList() {
        TaskList taskList = TaskList.newInstance();
        assertEquals(taskList, TaskList.newInstance());
    }

    @Test
    public void addNewTodoTask_doneTodo_doneTodoTask() {
        Task newTodo = TaskList.newInstance().addNewTodoTask("Todo1", true);
        assertEquals(Task.DONE, newTodo.getStatus());
    }

    @Test
    public void addNewTodoEvent_undoneEvent_undoneEventTask() {
        Task newTodo = TaskList.newInstance().addNewEventTask("Event1", "Event1Info", false);
        assertEquals(Task.NOT_DONE, newTodo.getStatus());
    }

    @Test
    public void clear_emptyList() {
        TaskList taskList = TaskList.newInstance();
        taskList.addNewTodoTask("Todo1", false);
        taskList.addNewTodoTask("Todo2", false);
        taskList.addNewTodoTask("Todo3", false);
        taskList.clear();
        assertEquals(new ArrayList<Task>(), taskList.getTasks());
    }

}
