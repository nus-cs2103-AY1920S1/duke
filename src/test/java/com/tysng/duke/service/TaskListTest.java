package com.tysng.duke.service;

import com.tysng.duke.domain.Task;
import com.tysng.duke.domain.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskListTest {
    @Test
    public void testGetTask() {
        Task newTask = new Todo("test");
        List<Task> tasks = List.of(newTask);
        Assertions.assertEquals(new TaskList(tasks).getTask(0), newTask);
    }

    @Test
    public void testRemoveTask() {
        Task newTask = new Todo("test");
        List<Task> tasks = new ArrayList<>();
        tasks.add(newTask);
        Assertions.assertEquals(new TaskList(tasks).removeTask(0), newTask);
    }

    @Test
    public void testGetTaskList() {
        Task newTask = new Todo("test");
        List<Task> tasks = List.of(newTask);
        Assertions.assertEquals(new TaskList(tasks).getTaskList(), tasks);
    }


}
