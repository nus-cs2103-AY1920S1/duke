package duke.Command;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Command {
int type;
String msg;
boolean isExit = false;

    public Command(int type, String msg){
        this.type = type;
        this.msg = msg;
    }

    public void execute(TaskList tasks, Ui ui, Storage store) throws IOException {
       if(this.type == 0)                       //bye
           this.isExit = true;
       if(this.type == 1) {                     //to do
           ToDoCommand d = new ToDoCommand();
           d.toDo(tasks, ui, store, this.msg);
       }
        if(this.type == 2) {                      //list
            ListCommand d = new ListCommand();
            d.list(tasks, ui);
        }

        if(this.type == 3) {                      //deadline
            DeadlineCommand d = new DeadlineCommand();
            d.Deadline(tasks, ui, store, this.msg);
        }
        if(this.type == 4)
            new EventCommand();                 //event
        if(this.type == 5)                      //find
            new FindCommand();

        if(this.type == 6) {                    //done
            DoneCommand d = new DoneCommand();
            d.Done(tasks, this.msg);
        }
        if(this.type == 7)                      //delete
            new DeleteCommand();
    }

    public boolean isExit(){
          return isExit;
    }
}
