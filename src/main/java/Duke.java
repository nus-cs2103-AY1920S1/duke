import java.util.Scanner;
import java.util.Optional;

public class Duke {
    private static String divider = "    " + "-".repeat(61);

    private static void listData() {
        String[] taskStrings = new String[Task.totalNumOfTasks];
        for (Task t : Task.taskList) {
            taskStrings[t.getId() - 1] = t.toString();
        }
        dukeRespond(taskStrings);
    }
    private static void addData(String data) {
        Optional<Task> newTask = Task.createTask(data, false);
        if (newTask.isEmpty()) {
            dukeRespond("Ohnoes you didn't key in a valid command!",
                    "Please try again friend \\o/");
        } else {
            dukeRespond("added: " + data);
        }
    }

    public static void dukeRespond(String... inputs) {
        System.out.println(divider);
        for (String str : inputs) {
            System.out.println("     " + str);
        }
        System.out.println(divider);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeRespond("Hello! I'm Duke", "What can I do for you?");

        //start listening for user input
        Scanner sc = new Scanner(System.in);
        String userCmd = sc.nextLine();

        while (!userCmd.equals("bye")) {

            if (userCmd.equals("list")) {
                listData();
            } else {
                addData(userCmd);
            }

            userCmd = sc.nextLine();
        }
        dukeRespond("Bye. Hope to see you again soon!");

        //clear resources
        sc.close();
    }
}
