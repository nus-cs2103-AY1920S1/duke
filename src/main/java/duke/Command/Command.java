package duke.Command;
import duke.Flashcard.FlashcardList;
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

    public String execute(TaskList tasks, Ui ui, Storage store, FlashcardList flashcardList) throws IOException {
        String output = "";

        switch (this.type) {
            case 0:                           //bye
                this.isExit = true;
                ByeCommand b = new ByeCommand();
                output = b.Bye(ui);
                break;
            case 1:                           //to do
                ToDoCommand d = new ToDoCommand();
                output = d.toDo(tasks, ui, store, this.msg);
                break;
            case 2:
                ListCommand l = new ListCommand();   //list
                output = l.list(tasks, ui);
                break;
            case 3:                                 //deadline
                DeadlineCommand dead = new DeadlineCommand();
                output = dead.Deadline(tasks, ui, store, this.msg);
                break;
            case 4:                                //event
                EventCommand ev = new EventCommand();
                output = ev.Event(tasks, ui, store, this.msg);
                break;
            case 5:                                //find
                FindCommand find = new FindCommand();
                output = find.Find(tasks, ui, this.msg);
                break;
            case 6:                                //done
                DoneCommand done = new DoneCommand();
                output = done.Done(tasks, this.msg, ui);
                break;
            case 7:                                //delete
                DeleteCommand del = new DeleteCommand();
                output = del.Delete(tasks, ui, store, this.msg);
                break;
            case 8:                                  //Create_Flashcard
                CreateFlashcardCommand cfc = new CreateFlashcardCommand();
                output = cfc.CreateFlashcard(ui, flashcardList, this.msg);
                break;
            case 9:                                 //Create_Card
                CreateCardCommand ccc = new CreateCardCommand();
                output = ccc.CreateCard(ui, flashcardList, this.msg);
                break;
            case 10:                                //List_Qns
                ListQnsCommand lqc = new ListQnsCommand();
                output = lqc.ListQns(ui, flashcardList, this.msg);
                break;
            case 11:                                //List_Ans
                ListAnsCommand lac = new ListAnsCommand();
                output = lac.ListAns(ui, flashcardList, this.msg);
                break;
            default:
                FalseCommand fal = new FalseCommand();
                output = fal.False(ui);
        }
        return output;
    }

    public boolean isExit(){
          return isExit;
    }
}
