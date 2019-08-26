package task;

/**
 * Represents a Task to do.
 */
public class ToDoTask extends Task {
    /**
     * Creates a ToDoTask with a given description.
     * @param description Task description.
     */
   public ToDoTask (String description) {
       super(description);
   }

   public String toFileString() {
       return "T||" + (this.isDone?"1||":"0||")  + this.description;
   }

   @Override
   public String toString() {
       return "[T]" + super.toString();
   }

}
