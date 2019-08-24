public class Deadline extends Task {
    private String deadLine;

    public Deadline(String taskName, String deadLine){
        super(taskName);
        this.deadLine = deadLine;
    }

    public String toString(){
        String box;
        String msg = "[D]";
        if (!this.isDone()){
            box = "[✗]";
        } else{
            box = "[✓]";
        }
        return msg + box + " " + this.getTask() + " (by: " + deadLine + ")";
    }

    public void printAddedDeadline (int size){
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        String end = " tasks in the list.";
        if (size==1) {
            end = " task in the list.";
        }
        System.out.println("Now you have " + size + end);
    }



}
