package task;

import exception.DukeException;

public class Todo extends Task{
<<<<<<< HEAD:src/main/java/task/Todo.java
    public Todo(String description) throws DukeException {
=======
    public Todo(String description) throws DukeException{
>>>>>>> c15043ac81860360c58b39a9b432b0229f5d4d30:src/main/java/Todo.java
        super(description);
    }

    @Override
    public String toString(){
        String output = "[T][" + super.getStatusIcon() + "]" + " " + super.description;
        return output;
    }
}
