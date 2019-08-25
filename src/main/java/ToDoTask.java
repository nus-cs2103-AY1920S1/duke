public class ToDoTask extends Task {
    public ToDoTask (String description) {
        this.description = description;
        this.isDone = false;
    }

    public ToDoTask (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    
    @Override
    //Returns a copy of this task but with its completion status marked as done
    public Task getTaskMarkedAsDone() {
        return new ToDoTask(description, true);
    }

    @Override
    //Returns a copy of this task but with its completion status marked as undone
    public Task getTaskMarkedUndone() {
        return new ToDoTask(description, false);
    }

    @Override
    //Returns a string representation of the Task, including its type, completion status and description
    public String toString() {
        return String.format(TO_STRING_FORMAT, 'T', this.getStatusIcon(), this.description);
    }
}
