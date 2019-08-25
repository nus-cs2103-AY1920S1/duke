import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private String timeDate;
    private String interpretedDate;
    private String time;

    /**
     * @param task task of the Events object
     * @param timeDate time and date of the Deadline object in the following format: 2/12/2019 2-4pm
     *
     */

    public Events(String task, String timeDate){
        super(task);
        this.timeDate = timeDate;
        interpretDate();
    }

    /**
     * returns the message comprising [E], name of the task of the Events object and its date and start to end time,
     * together with the a symbol representing if an Events object is completed or not.
     * [E] represents that our object is a Events object.
     *
     */
    public String toString(){
        String box;
        String msg = "[E]";
        if (!this.isDone()){
            box = "[✗]";
        } else{
            box = "[✓]";
        }
        return msg + box + " " + this.getTask() + " (at: " + interpretedDate + " from " + time + ")";
    }

    /**
     * interprets date of Events object from timeDate attribute.
     *eg 2/12/2019 becomes 2 December 2019
     *2-4pm stays the same
     */

    public void interpretDate(){
        String[] words = timeDate.split(" ");
        String date = words[0];
        String time = words[1];

        DateTimeFormatter oldDateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
        DateTimeFormatter newDateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy");
        LocalDate oldDate = LocalDate.parse(date, oldDateFormat);
        String newDate = oldDate.format(newDateFormat);

        this.interpretedDate = newDate;
        this.time = time;

    }

    /**
     * prints a message by Duke, when Duke has added an Events object.
     *
     * @param size Number of tasks managed by Duke after we have added an Events object.
     *
     */
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
