public class ListCommand extends Command{



    public void runCommand(TaskList taskList, Storage storage, Ui ui){

        String result = "Here are the tasks in your list:\n";
        int index = 1;
        for(int i = 1; i <= taskList.size(); i++){
            result +=   "\n" + index + ". " + taskList.getTask(i)  ;
            index++;
        }


       ui.printText(result);



    }
}