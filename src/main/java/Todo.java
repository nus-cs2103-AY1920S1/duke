public class Todo extends Task{
    public Todo(String description) throws DukeException{
        super(description);
        if(description.equals("")){
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.taskType = "T";
    }
    public Task markAsDone() throws DukeException{
        if(this.isDone){
            throw new DukeException("☹ OOPS!!! The todo is already marked as done.");
        }
        Todo completed = new Todo(this.description);
        completed.isDone = true;
        return completed;
    }
    public String getTaskStatus(){
        return ("[" + this.getTaskType() + "] " + "[" + this.getStatusIcon() + "]" + this.getTaskDescription()); 
    }
}
