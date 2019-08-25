package task;

public class ToDoTask extends Task {
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
