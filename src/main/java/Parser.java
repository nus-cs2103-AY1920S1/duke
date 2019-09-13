import java.io.IOException;
import java.util.ArrayList;

public class Parser{

    public EventHandler handler = new EventHandler();

    public void Parse(String input, int no_of_tasks, ArrayList<Task> taskList, Storage storage) throws IOException {
        String[] arr = input.split(" ", 2);

        if(arr[0].equals("bye"))
            handler.exitCommand();

        if(arr[0].equals("todo")) {
            no_of_tasks++;
            handler.toDoCommand(arr[1], no_of_tasks, taskList, storage);
        }

        if(arr[0].equals("list"))
            handler.listCommand(no_of_tasks, taskList);

        if(arr[0].equals("deadline"))
            handler.deadlineCommand(arr[1]);
        if(arr[0].equals("event"))
            handler.eventCommand(arr[1]);

        if(arr[0].equals("find"))
            handler.findCommand(arr[1]);
        if(arr[0].equals("done"))
            handler.doneCommand(arr[1]);
        if(arr[0].equals("delete"))
            handler.deleteCommand();
    }

}
