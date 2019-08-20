import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    public static void listTasks(List<String> taskList) {
        int count = 1;
        for (String task : taskList) {
            System.out.println(count++ + ". " + task);
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        List<String> taskList = new ArrayList<String>();
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            if (command.equals("list")) {
                listTasks(taskList);
                continue;
            }
            taskList.add(command);
            System.out.println("added: " + command);
        }
        System.out.println("Bye. Hope to see you again soon!");

        sc.close();
    }
    
}
