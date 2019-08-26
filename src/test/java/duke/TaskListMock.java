package duke;

import duke.task.Task;
import duke.task.TaskEnum;
import duke.task.TaskMock;

import java.util.ArrayList;

public class TaskListMock extends TaskList {
    public TaskListMock(ArrayList<Task> tasks){
        super(new ArrayList<Task>());
    }

    @Override
    public Task add(String description, TaskEnum type){
        return new TaskMock("");
    }

    @Override
    public Task add(String description, String date, TaskEnum type){
        return new TaskMock("");
    }

    @Override
    public Task delete(int taskNo){
        return new TaskMock("");
    }

    @Override
    public Task done(int taskNo){
        return new TaskMock("");
    }

    @Override
    public String list(){
        return "Here are the tasks in your list:\n1.[✓] description";
    }
}
