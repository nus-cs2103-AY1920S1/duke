package duke.Command;

import duke.TaskList;
import duke.Ui;

public class ListCommand {

    public ListCommand(){

    }

    public void list(TaskList tasks, Ui ui){
        ui.print_list(1, 0, tasks.get_TaskList());

        for (int i = 0; i < tasks.get_NoOfTasks(); i++) {
            if((tasks.get_TaskList().get(i).get_TimeFrame()).equals(""))
                ui.print_list(2, i, tasks.get_TaskList());
            else
                ui.print_list(3, i, tasks.get_TaskList());
        }
    }
}
