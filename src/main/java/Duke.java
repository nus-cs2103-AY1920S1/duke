import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String blankLines = "____________________________________________________________";
        System.out.println("    Hello from\n" + logo);
        System.out.println(blankLines);
        System.out.println("    Hello, I'm Duke!\n    What can I do for you?");
        System.out.println(blankLines);

        ArrayList<Task> myList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();


        while(!command.equals("bye")) {
            System.out.println(blankLines);
            switch(command) {
                case "list":
                    if (Task.tasks.isEmpty()) {
                        System.out.println("    List is empty");
                    } else {
                        Task.printTaskList();
                    }
                    command = input.nextLine();
                    break;

                default:

                    if (command.contains("done")) {
                        //Mark tasks as done
                        Task.markAsDone(command);
                    } else {
                        //Add tasks
                        Task.addTask(command);
                    }
                    command = input.nextLine();
                    break;
            }
        }
        System.out.println(blankLines + "\n    Bye. Hope to see you again!");
    }
}
