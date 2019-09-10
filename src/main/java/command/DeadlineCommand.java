package command;

import java.io.IOException;
import java.util.ArrayList;import main.*;
import task.Deadline;
import task.Task;


public class DeadlineCommand extends Command {
    private String deadlineName;
    private String date;

    public DeadlineCommand(String deadlineName, String date) {
        this.deadlineName = deadlineName;
        this.date = date;
    }

    public void execute(TaskList tl, Storage st) throws IOException {
            Task deadline = new Deadline(deadlineName, date);

            tl.addTaskList(deadline);
            UI.newTask(tl.getTaskList());

            st.writeToFile(tl.getTaskList());
    }
}
