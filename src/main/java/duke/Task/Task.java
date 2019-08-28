package duke.task;

public class Task{
    protected char taskType;
    protected String taskDescription;
    protected boolean isDone;

    public Task(){
        this.taskDescription = "";
        this.isDone = false;
    }

    public Task(String taskDescription, boolean isDone) throws NullPointerException{
        this.isDone = isDone;

        if(taskDescription == null) throw new NullPointerException();
        this.taskDescription = taskDescription;
    }

    public Task(char taskType, String taskDescription, boolean isDone) throws NullPointerException{
        this.isDone = isDone;
        this.taskType = taskType;

        if(taskDescription == null) throw new NullPointerException();
        this.taskDescription = taskDescription;
    }

    public String printTask(){
        return "[" + getFirstCharTask() + "][" + getIcon() + "] " + getTaskDescription();
    }

    public char getIcon(){
        return isDone? '\u2713': '\u274C';
    }

    public void setIsDone(){
        this.isDone = true;
    }

    public char getFirstCharTask(){
        return taskType;
    }

    public boolean getIsDone(){
        return isDone;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String input){
        this.taskDescription = input;
    }

}

