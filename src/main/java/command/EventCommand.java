package command;

import main.Storage;
import main.TaskList;
import main.UI;
import task.Event;
import task.Task;

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
        ArrayList<Task> list = tl.getTaskList();

        Task event = new Event(eventName, date);

        tl.addTaskList(event);
        UI.newTask(list);

        st.writeToFile(list);
    }
}
