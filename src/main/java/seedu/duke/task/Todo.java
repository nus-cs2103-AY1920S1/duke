package seedu.duke.task;

/**
 * Todo class is a subclass of Task class.
 * Has no additional attributes.
 */
public class Todo extends Task {

    /**
     * Returns a Todo object after initializing with the String description.
     *
     * @param description Description String ot the task.
     */
    public Todo(String description){
        super(description);
        taskType = possibleTaskTypes.TODO;
    }

    /**
     * Returns a Todo object after initializing with the String description and Boolean status of the task.
     *
     * @param description Description String ot the task.
     * @param isDone isDone status of the task.
     */
    public Todo(String description, Boolean isDone){
        super(description, isDone);
    }

    /**
     * Returns a parsed String of the Todo object.
     * Eg. description = "<Task Description>", isDone = false.
     * Parsed string = "[T][✘] <Task Description>".
     *
     * @return Parsed String of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }

    /**
     * Returns a parsed String, meant for saving, of the Todo object.
     * Eg. description = "<Task Description>", isDone = true.
     * Parsed saved string = "T | 1 | <Task Description>".
     *
     * @return Parsed saved string of the Todo object.
     */
    @Override
    public String toSaveString(){
        return ("T" + super.toSaveString());
    }
}

