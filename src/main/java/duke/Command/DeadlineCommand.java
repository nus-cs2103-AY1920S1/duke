package duke.Command;
import duke.*;

import java.io.IOException;
import java.lang.*;

public class DeadlineCommand {
    public DeadlineCommand(){
    }

    public void Deadline(TaskList tasks, Ui ui, Storage store, String msg) throws IOException {
        String new_timeFrame;
        int index = 0;
        index = msg.indexOf('/'); //iterate through the input to find the '/' char

        String timeFrame = msg.substring(index + 1);             // "by......"
        String temp = timeFrame.substring(3);                    // "Sunday ..."   OR   "11/02/2019 ..."
        String sub = msg.substring(0, index - 1);               //to get description. eg return book


        if(Character.isDigit(msg.charAt(0))) {                  //if "11/02/2019" format then convert, else do nth.
            ConvertDateTime c = new ConvertDateTime();
            new_timeFrame = c.Convert(temp);
        }
        else{
            new_timeFrame = temp;
        }

        Task t = new Task(sub, 'D', 0, new_timeFrame);
        tasks.add(t);
        store.AutoSave(tasks, tasks.get_NoOfTasks());
        ui.print_deadline(sub, new_timeFrame, tasks.get_NoOfTasks());

    }
}
