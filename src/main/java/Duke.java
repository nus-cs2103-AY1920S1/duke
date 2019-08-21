import java.util.Scanner;

public class Duke {

    private static final String DUKE_HELLO = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String DUKE_BYE = "Bye. Hope to see you again soon!";
    private static final String DUKE_LIST_TASKS = "Here are the tasks in your list:\n";
    private static final String DUKE_MARK_AS_DONE = "Nice! I've marked this task as done:\n";
    private static final String DUKE_ADD_TASK = "Got it. I've added this task:\n";
    private static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";
    protected TaskList taskList;

    protected Duke() {
        this.taskList = new TaskList();
    }

    private String addTask(Task task) {
        return String.format("%s  %s\n",
                    DUKE_ADD_TASK,
                    taskList.addTask(task).getStatus())
                + String.format(DUKE_NUMBER_OF_TASKS, taskList.getSize());
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
        Task task = null;
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
            } else if (command.startsWith("todo")) {
                task = new TodoTask(command.substring(5));
            } else if (command.startsWith("deadline")) {
                String[] cmd = command.substring(9).split(" /by ");
                task = new DeadlineTask(cmd[0], cmd[1]);
            } else if (command.startsWith("event")) {
                String[] cmd = command.substring(6).split(" /at ");
                task = new EventTask(cmd[0], cmd[1]);
            } else {
                System.err.println("Incorrect command");
                continue;
            }
            System.out.println(this.addTask(task));
        }

        // Greet the user and quit program
        System.out.println(DUKE_BYE);
    }
    
}