package duke.Command;

import duke.*;

import java.io.IOException;

public class EventCommand {

    public EventCommand(){

    }

    public void Event(TaskList tasks, Ui ui, Storage store, String msg) throws IOException {
        String new_timeFrame;
        int index = 0;
        index = msg.indexOf('/');   //iterate through the description portion to find the '/' char

        String timeFrame = msg.substring(index + 1);
        String temp = timeFrame.substring(3);            //temp = day & date ONLY
        String sub = msg.substring(0, index - 1);        //sub = description ONLY

        if(Character.isDigit(temp.charAt(0))) {                  //if "11/02/2019" format then convert, else do nth.
            ConvertDateTime c = new ConvertDateTime();
            new_timeFrame = c.Convert(temp);
        }
        else{
            new_timeFrame = temp;
        }

        Task t = new Task(sub, 'E', 0, new_timeFrame);
        tasks.add(t);
        store.AutoSave(tasks, tasks.get_NoOfTasks());
        ui.print_event(sub, new_timeFrame,  tasks.get_NoOfTasks());
    }
}
