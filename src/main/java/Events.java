public class Events extends Task {
    private String timeDate;

    public Events(String task, String timeDate){
        super(task);
        this.timeDate = timeDate;
    }
    public String toString(){
        String box;
        String msg = "[E]";
        if (!this.isDone()) {
            box = "[✗]";
        } else {
            box = "[✓]";
        }
        return msg + box + " " + this.getTask() + " (at: " + timeDate + ")";
    }

    public void printAddedEvent (int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        String end = " tasks in the list.";
        if (size == 1){
            end = " task in the list.";
        }
        System.out.println("Now you have " + size + end);
    }
}
