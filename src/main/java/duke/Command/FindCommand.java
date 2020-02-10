package duke.Command;

import duke.TaskList;
import duke.Ui;

public class FindCommand {

    public FindCommand(){

    }

    public String Find(TaskList tasks, Ui ui, String keyword){
        int num=0;                                       //position of tasks, to be printed
        String output = "";

        System.out.println("Here are the matching tasks in your list: ");

        //Iterate the ArrayList to get the tasks
        for (int i = 0; i < tasks.get_TaskList().size(); i++){
            if((tasks.get_TaskList().get(i).get_Description()).contains(keyword)) {    //if task description contains the keyword
                num++ ;
                output = ui.print_find(num, tasks.get_TaskList().get(i));                       //pass in the task to be printed
            }
        }
        return output;
    }
}
