import java.util.Scanner;

public class Duke {

    private static final String DUKE_HELLO = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String DUKE_BYE = "Bye. Hope to see you again soon!";
    private static final String DUKE_LIST_TASKS = "Here are the tasks in your list:\n";
    private static final String DUKE_MARK_AS_DONE = "Nice! I've marked this task as done:\n";
    protected TaskList taskList;
    
    protected Duke() {
        this.taskList = new TaskList();
    }

    protected void run() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        // Greet the user
        System.out.println(DUKE_HELLO);

        // Handle user input
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            if (command.equals("list")) {
                // List all the commands entered by user
                System.out.print(DUKE_LIST_TASKS + taskList.getTaskList());
                continue;
            } else if (command.startsWith("done")) {
                // Mark Task at index specified as done
                int index = Integer.parseInt(command.split(" ")[1]);
                taskList.markAsDoneTaskAt(index);
                System.out.println(DUKE_MARK_AS_DONE + taskList.getTaskAt(index).getStatus());
                continue;
            }
            System.out.println("added: " + taskList.addTask(new Task(command)));
        }

        // Greet the user and quit program
        System.out.println(DUKE_BYE);
    }
    
}