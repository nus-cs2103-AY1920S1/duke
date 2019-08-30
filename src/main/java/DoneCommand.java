
import java.io.IOException;

public class DoneCommand extends Command{
   int taskNum;

    public DoneCommand(int taskNum){
        this.taskNum = taskNum;
    }

    public void runCommand(TaskList taskList, Storage storage, Ui ui){
         try {
             taskList.markTaskDone(taskNum);
             Task updatedTask = taskList.getTask(taskNum);
             storage.updateText(taskNum);
             ui.printText("Nice! I've marked this task as done: \n" + updatedTask);
             storage.updateText(taskNum);
         } catch (IOException e){
             System.out.println(e.getMessage());
         }


    }
}