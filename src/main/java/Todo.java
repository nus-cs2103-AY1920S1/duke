public class Todo extends Task{
    public Todo(String description){
        super(description);
        this.taskType = "T";
    }
    public Task markAsDone(){
        Todo completed = new Todo(this.description);
        completed.isDone = true;
        return completed;
    }
    public String printTaskStatus(){
        return ("[" + this.getTaskType() + "] " + "[" + this.getStatusIcon() + "] " + this.getTaskDescription()); 
    }
}
