import java.io.IOException;

public class AddEventCommand extends Command{

    String task;
    String date;
    Event eventTask;

    public AddEventCommand(String task, String date){
        this.commandType = CommandType.ADDEVENT;
        this.task = task;
        this.date = date;
    }

}
