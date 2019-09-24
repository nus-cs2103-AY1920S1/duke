package main;

import task.Task;
import task.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @org.junit.jupiter.api.Test
    void getTaskList() {
        assertEquals(new ArrayList<Task>(), new TaskList().getTaskList());
    }

    @org.junit.jupiter.api.Test
    void updateTaskList() {
        ArrayList<Task> arrList = new ArrayList<>();
        arrList.add(new Todo("name"));

        TaskList taskList = new TaskList();
        taskList.updateTaskList(arrList);


        assertEquals(arrList, taskList.getTaskList());
    }

    @org.junit.jupiter.api.Test
    void addTaskList() {
        ArrayList<Task> arrList = new ArrayList<>();
        Todo todo = new Todo("name");

        TaskList taskList = new TaskList(arrList);
        taskList.addTaskList(todo);


        arrList.add(new Todo("name"));
        assertEquals(arrList, taskList.getTaskList());
    }
}