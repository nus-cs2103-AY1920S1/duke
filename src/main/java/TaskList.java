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
            String[] strArray = command.split("\\s+");
            if(strArray[0].equals("done")) {
                markTaskDone(Integer.parseInt(strArray[1]));
            } else {
                addCommand(command);
            }
        }
    }

    /**
     * Adds new command into list
     */
    public void addCommand(String command) {
        this.list.add(new Task(command));
        System.out.println("added: " + command);
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
