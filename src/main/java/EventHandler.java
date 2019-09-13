import java.io.IOException;
import java.util.ArrayList;

public class EventHandler {

    private Ui ui = new Ui();

    public void toDoCommand(String msg, int no_of_tasks, ArrayList<Task> taskList, Storage storage) throws IOException {
    if(msg.isEmpty())
        ui.cannotBeEmpty();
    else {
        ui.print_toDo(msg, no_of_tasks);
        Task t = new Task(msg, 'T', 0, "");
        taskList.add(t);
        storage.AutoSave(taskList, no_of_tasks);
    }
    }

    public void listCommand(int no_of_task, ArrayList<Task> taskList){
        ui.print_list(1, 0, taskList);

        for (int i = 0; i < no_of_task; i++) {
            if((taskList.get(i).timeframe).equals(""))
                ui.print_list(2, i, taskList);
            else
                ui.print_list(3, i, taskList);
        }
    }

    public void eventCommand(String msg) {

    }
    public void deadlineCommand(String msg){

    }

    public void doneCommand(String msg){

    }
    public void deleteCommand(){

    }
    public void findCommand(String msg){

    }
    public void exitCommand(){
        ui.print_bye();
        //close command line
    }

}
