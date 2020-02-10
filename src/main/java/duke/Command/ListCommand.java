package duke.Command;

import duke.TaskList;
import duke.Ui;

public class ListCommand {

    public ListCommand(){

    }

    public String list(TaskList tasks, Ui ui){
        StringBuilder builder = new StringBuilder(ui.print_list(1, 0, tasks.get_TaskList()) + "\n");
        //String output = ui.print_list(1, 0, tasks.get_TaskList());

        for (int i = 0; i < tasks.get_NoOfTasks(); i++) {
            if((tasks.get_TaskList().get(i).get_TimeFrame()).equals(""))
                builder.append(ui.print_list(2, i, tasks.get_TaskList()) + "\n");
            else
                builder.append(ui.print_list(3, i, tasks.get_TaskList()) + "\n");
        }
        return builder.toString();
    }
}
