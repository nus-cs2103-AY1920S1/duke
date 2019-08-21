import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        TaskList list = new TaskList();
        String command = "";

        while (true) {
            command = sc.next();

            switch (command) {
                case "list":
                    list.printTasks();
                    break;

                case "done":
                    int taskNum = sc.nextInt();
                    list.tickTask(taskNum);
                    break;

                case "todo":
                    String[] input1 = sc.nextLine().split("/by");
                    list.addTask(new ToDos(input1[0]));
                    break;

                case "deadline":
                    String[] input2 = sc.nextLine().split("/by");
                    list.addTask(new Deadlines(input2[0], input2[1]));
                    break;

                case "event":
                    String[] input3 = sc.nextLine().split("/at");
                    list.addTask(new Event(input3[0], input3[1]));
                    break;

                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
            }
        }
    }
}
