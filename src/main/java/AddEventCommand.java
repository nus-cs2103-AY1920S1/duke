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

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        this.eventTask = new Event(this.task, taskList.getSize() + 1, this.date);
        taskList.addToList(this.eventTask);
        ui.printAddEventCommand(eventTask, taskList);
    }
}
