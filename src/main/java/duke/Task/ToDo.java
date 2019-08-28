package duke.task;

public class ToDo extends Task{

    public ToDo(char taskType, String taskDescription, boolean isDone){
        super(taskType, taskDescription, isDone);
    }

    public ToDo(String taskDescription, boolean isDone){
        super(taskDescription, isDone);
    }

}
