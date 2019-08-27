import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private List<Task> taskList;
    private TaskFileManager taskFileManager = new TaskFileManager(new File("data/tasks.txt"));

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    // Overloaded method for existing task lists
    public TaskList(List l) {
        taskList = new ArrayList<Task>(l);
    }


    public void loadData() {
        List<Task> loadedTaskList = taskFileManager.getTaskList();
        if (loadedTaskList == null)
            taskList = new ArrayList<>();
        else
            taskList = loadedTaskList;
    }

    public void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("     %d.%s",
                    i+1, taskList.get(i).toString()));
        }
        System.out.println("    ____________________________________________________________");
    }

    public void echoEntry(String input) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                String.format("       %s \n", input) +
                String.format("     Now you have %d tasks in the list. \n", taskList.size()) +
                "    ____________________________________________________________");
    }

    public void completeTask(int entryNumber) throws IndexOutOfBoundsException{
        taskList.get(entryNumber - 1).setDone();
        System.out.println(String.format("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       %s\n" +
                        "    ____________________________________________________________",
                taskList.get(entryNumber - 1).toString()));
        taskFileManager.writeListToFile(taskList);
    }

    public void deleteTask(int entryNumber) throws IndexOutOfBoundsException{
        System.out.println(String.format("    ____________________________________________________________\n" +
                        "     Noted. I've removed this task: \n" +
                        "       %s\n" +
                        "     Now you have %d tasks in the list.\n" +
                        "    ____________________________________________________________",
                taskList.remove(entryNumber - 1).toString(), taskList.size()));
        taskFileManager.writeListToFile(taskList);
    }

    public void createDeadline(String input) throws DukeException {
        String[] parsedDeadline = input.split(" \\/by ");
        if (input.isEmpty())
            throw new DukeException("The description of a deadline cannot be empty.");
        if (parsedDeadline.length == 1)
            throw new DukeException("Deadline is missing a deadline");
        Task newDeadline = new Deadline(parsedDeadline[0], parsedDeadline[1]);
        // Update Local Copy
        taskList.add(newDeadline);
        // Update Harddisk Copy
        taskFileManager.appendToFile(newDeadline.serialise());
        echoEntry(newDeadline.toString());
    }

    public void createEvent(String input) throws DukeException{
        String[] parsedEvent = input.split(" \\/at ");
        if (input.isEmpty())
            throw new DukeException("The description of an event cannot be empty.");
        if (parsedEvent.length == 1)
            throw new DukeException("Event is missing a location");
        Task newEvent = new Event(parsedEvent[0], parsedEvent[1]);
        // Update Local Copy
        taskList.add(newEvent);
        // Update Harddisk Copy
        taskFileManager.appendToFile(newEvent.serialise());
        echoEntry(newEvent.toString());
    }

    public void createTodo(String input) throws DukeException{
        if (input.isEmpty())
            throw new DukeException("The description of a todo cannot be empty.");
        Task newTodo = new Todo(input);
        // Update Local Copy
        taskList.add(newTodo);
        // Update Harddisk Copy
        taskFileManager.appendToFile(newTodo.serialise());
        echoEntry(newTodo.toString());
    }
}
