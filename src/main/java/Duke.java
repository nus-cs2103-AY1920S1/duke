import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws DukeException, ClassNotFoundException, IOException {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("If you need help, type \'help\' anytime");
        System.out.println("What can I do for you?");
        System.out.println();

        TaskManager taskManager = new TaskManager();
        taskManager.initializeTasks();
    }
}
