package duke.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.io.IOException;

public class Command {
int type;
String msg;
boolean isExit;

    public Command(int type, String msg){
        this.type = type;
        this.msg = msg;
        this.isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage store) throws IOException {

        switch (this.type) {
            case 0:                           //bye
                this.isExit = true;
                break;
            case 1:                           //to do
                ToDoCommand d = new ToDoCommand();
                d.toDo(tasks, ui, store, this.msg);
                break;
            case 2:
                ListCommand l = new ListCommand();   //list
                l.list(tasks, ui);
                break;
            case 3:                                 //deadline
                DeadlineCommand dead = new DeadlineCommand();
                dead.Deadline(tasks, ui, store, this.msg);
                break;
            case 4:                                //event
                EventCommand ev = new EventCommand();
                ev.Event(tasks, ui, store, this.msg);
                break;
            case 5:                                //find
                FindCommand find = new FindCommand();
                find.Find(tasks, ui, this.msg);
                break;
            case 6:                                //done
                DoneCommand done = new DoneCommand();
                done.Done(tasks, this.msg);
                break;
            case 7:                                //delete
                DeleteCommand del = new DeleteCommand();
                del.Delete(tasks, ui, store, this.msg);
                break;
            default:
                FalseCommand fal = new FalseCommand();
                fal.False(ui);
        }
    }

    public boolean isExit(){
          return isExit;
    }
}
