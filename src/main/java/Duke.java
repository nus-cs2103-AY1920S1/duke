import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        TasksHolder tasksHolder = new TasksHolder();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if ( line.equals("bye") ) {
                System.out.println("  Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")){
                tasksHolder.printTasks();
            } else {
                Task currTask = new Task(line);
                tasksHolder.addTask(currTask);
            }
        }
    }
}
