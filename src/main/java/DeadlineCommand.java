import java.io.IOException;
import java.util.ArrayList;

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
            UI.newTask(tl.list);

            st.writeToFile(tl.list);
    }
}
