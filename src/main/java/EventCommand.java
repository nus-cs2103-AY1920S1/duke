
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.io.IOException;

public class EventCommand extends Command{
    boolean isDone;
    String description;
    Date time;

    public EventCommand(boolean isDone, String description, String time){
        this.isDone = isDone;
        this.description = description;
        try {
            Date twentyFourHourFormat = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(time);
            String twelveHourFormat = new SimpleDateFormat("dd/MM/yyy hh:mm").format(twentyFourHourFormat);
            this.time = new SimpleDateFormat("dd/MM/yyy hh:mm").parse(twelveHourFormat);
        } catch (ParseException e) {
            System.out.println(e.getMessage());

        }

    }

    public void runCommand(TaskList taskList, Storage storage, Ui ui){

       try {
           storage.appendToFile("E", this.time, this.description);


           Event newEvent = new Event(this.description, this.time);

           taskList.addTask(newEvent);

           int numTask = taskList.size();


           ui.printText("Got it. I've added this task: \n" + "  "
                   + newEvent + "Now you have " +
                   numTask + " tasks in the list.");
       }  catch (IOException e) {
           System.out.println("Something went wrong: " + e.getMessage());
       }

    }
}