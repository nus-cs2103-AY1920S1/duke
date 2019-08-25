import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String deadLine;

    private String interpretedDate;
    private String interpretedTime;

    /**
     * @param taskName task of the Deadline object
     * @param deadLine deadline of the Deadline object in the following format: 2/12/2019 1800
     *
     */


    public Deadline(String taskName, String deadLine){
        super(taskName);
        this.deadLine = deadLine;

        interpretDeadLine();

    }

    /**
     * returns the message comprising [D], name of the task of the deadline object and its deadline,
     * together with the a symbol representing if a deadline object is completed or not.
     * [D] represents that our object is a deadline object.
     *
     */
    public String toString(){
        String box;
        String msg = "[D]";
        if (!this.isDone()){
            box = "[✗]";
        } else{
            box = "[✓]";
        }

        return msg + box + " " + this.getTask() + " (by: " + this.interpretedDate + " at " + this.interpretedTime + ")";

    }

    /**
     * interprets deadline of Deadline object.
     *eg 2/12/2019 becomes 2 December 2019
     *and 1830 becomes 0630 pm
     */

    public void interpretDeadLine(){
        String[] words = deadLine.split(" ");
        String date = words[0];
        String time = words[1];

        DateTimeFormatter oldDateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
        DateTimeFormatter newDateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy");
        LocalDate oldDate = LocalDate.parse(date, oldDateFormat);
        String newDate = oldDate.format(newDateFormat);

        DateTimeFormatter oldTimeFormat = DateTimeFormatter.ofPattern("HHmm");
        DateTimeFormatter newTimeFormat = DateTimeFormatter.ofPattern("hhmm a");
        LocalTime timeObj = LocalTime.parse(time, oldTimeFormat);
        String newTime = timeObj.format(newTimeFormat);
        this.interpretedDate = newDate;
        this.interpretedTime = newTime;

    }


    /**
     * prints a message by Duke, when Duke has added a Deadline object.
     *
     * @param size Number of tasks managed by Duke after we have added a Deadline object.
     *
     */

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
