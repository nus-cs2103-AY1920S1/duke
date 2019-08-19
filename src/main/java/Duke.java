import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    static final String welcomeMsg = "Hello! I'm Duke\n" +
            "What can I do for you?";
    static final String exitMsg = "Bye. Hope to see you again soon!";
    static final LinkedList<Task> taskList = new LinkedList<Task>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        CmdInterface.printHBars(welcomeMsg);

        boolean isGoodbye = false;
        while (!isGoodbye) {
            String input;
            input = sc.nextLine();
            switch (input) {
                case "list":
                    listTasks();
                    break;
                case "bye":
                    exitApp();
                    isGoodbye = true;
                    break;
                default:
                    addTask(input);
            }
        }
    }

    public static void exitApp() {
        CmdInterface.printHBars(exitMsg);
    }
    public static void addTask(String taskName) {
        taskList.add(new Task(taskName));
        CmdInterface.printHBars("added: " + taskName);
    }

    public static void listTasks() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : taskList) {
            sb.append(i++ + ". " + task.getTaskName() + "\n");
        }
        CmdInterface.printHBars(sb.toString());
    }
}
