import java.io.IOException;


public class DeleteCommand extends Command{

    int taskNum;

    public DeleteCommand(int taskNum){
        this.taskNum = taskNum;
    }
    public void runCommand(TaskList taskList, Storage storage, Ui ui) {
       try { storage.deleteText(taskNum);

        Task removedTask = taskList.deleteTask(taskNum);
        ui.printText("Noted. I've removed this task:\n" + removedTask +
                "Now you have " + taskList.size() + " tasks in the list.");

    } catch (IOException e){
           System.out.println(e.getMessage());
       }
       }
}