import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Performs required action based on command
     */
    public void runCommand(String command) {
        if(command.equals("list")) {
            this.printList();
        } else {
            String[] strArray = command.split("\\s+", 2);
            if(strArray[0].equals("done")) {
                markTaskDone(Integer.parseInt(strArray[1]));
            } else {
                System.out.println("Got it. I've added this task: ");
                addCommand(strArray);
                System.out.println("Now you have " + this.list.size() + " tasks in the list.");
            }
        }
    }

    /**
     * Adds new command into list
     */
    public void addCommand(String[] strArray) {
        if(strArray[0].equals("todo")) {
            this.list.add(new ToDoTask(strArray[1]));
        } else {
            String[] taskArray = strArray[1].split("/", 2);
            String taskName = taskArray[0].trim();
            String date = taskArray[1].split("\\s+", 2)[1];
            if(strArray[0].equals("deadline")) {
              this.list.add(new DeadlineTask(taskName, date));
            } else {
                this.list.add(new EventTask(taskName, date));
            }
        }
    }

    /**
     * Marks specified command as done based on idx of command
     * @param idx - Index of command in list
     */
    public void markTaskDone(int idx) {
        this.list.get(idx-1).markDone();
    }

    /**
     * Prints out contents of list according to order of insertion
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < this.list.size(); i++) {
            System.out.println((i+1) + "." + this.list.get(i));
        }
    }
}
