public class ToDo extends Task {
   public ToDo (String description) {
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
