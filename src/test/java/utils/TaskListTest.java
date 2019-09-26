package utils;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.text.ParseException;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {
    private Task[] taskArr;

    TaskListTest() throws ParseException, DukeException {
        this.taskArr = new Task[]{new Todo("descriptionOne"), new Deadline("descriptionTwo",
                new StringToDate("10-10-2019 18:00")), new Event("descriptionThree",
                new StringToDate("10-10-2019 18:00"))};
    }

    @Test
    void getTask_task() throws ParseException {
        TaskList tasks = new TaskList(Arrays.asList(taskArr));
        assertEquals("[T][✗] descriptionOne", tasks.getTask(0).toString());
        assertEquals("[D][✗] descriptionTwo (by: 10-10-2019 18:00)", tasks.getTask(1).toString());
        assertEquals("[E][✗] descriptionThree (at: 10-10-2019 18:00)", tasks.getTask(2).toString());
    }


}
