import java.io.FileNotFoundException;
import java.io.IOException;

public class DoneCommand extends Command {
    int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tl, Storage st) {
        try {
            Task currTask = tl.list.get(taskNum);
            currTask.setDone();
            UI.done(currTask);

            st.writeToFile(tl.list);
        } catch (FileNotFoundException Fe) {
            System.out.println(Fe);
        } catch (IOException IOe) {
            System.out.println(IOe);
        }
    }
}
