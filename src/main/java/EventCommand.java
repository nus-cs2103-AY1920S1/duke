import java.io.IOException;
import java.util.ArrayList;

public class EventCommand extends Command {
    private String eventName;
    private String date;

    public EventCommand(String eventName, String date) {
        this.eventName = eventName;
        this.date = date;
    }

    public void execute(TaskList tl, Storage st) throws IOException {
            ArrayList<Task> list = tl.list;

            Task event = new Event(eventName, date);

            tl.addTaskList(event);
            UI.newTask(tl.list);

            st.writeToFile(tl.list);
    }
}
