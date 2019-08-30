package seedu.duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected possibleTaskTypes taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = possibleTaskTypes.DEFAULT;
    }

    public String getTaskName(){
        return this.description;
    }

    public Task(String description, Boolean isDone){
        this.description = description;
        this.isDone = isDone;
        this.taskType = possibleTaskTypes.DEFAULT;
    }

    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone(){
        if (!this.isDone){
            this.isDone = true;
        }
        return;
    }

    public String toString(){
        return ( "[" + this.getStatusIcon() + "] " + this.description);
    }

    public String toSaveString(){
        int booleanNum = (this.isDone ? 1 : 0);

        return ( " | " + booleanNum + " | " + this.description );
    }

    public Boolean isDefault(){
        return this.taskType.equals(possibleTaskTypes.DEFAULT) ;
    }

    public Boolean isTodo(){
        return this.taskType.equals(possibleTaskTypes.TODO) ;
    }

    public Boolean isEvent(){
        return this.taskType.equals(possibleTaskTypes.EVENT) ;
    }

    public Boolean isDeadline(){
        return this.taskType.equals(possibleTaskTypes.DEADLINE) ;
    }

    enum possibleTaskTypes {
        DEFAULT, DEADLINE, EVENT, TODO
    }
}

