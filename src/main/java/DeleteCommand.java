import java.io.IOException;

public class DeleteCommand extends Command {
    int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tl, Storage st) {
        try {
            if (taskNum > tl.list.size() || taskNum <= 0) {
                throw new DeleteException();
            }
            Task task = tl.list.remove(taskNum - 1);
            int listSize =  tl.list.size();

            UI.removedTask(task, listSize);

            st.writeToFile(tl.list);
        } catch (IOException IOe) {
            System.err.println(IOe);
        } catch (DeleteException dE) {
            System.err.println(dE);
        }
    }
}
