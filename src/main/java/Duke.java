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

        Task.displayTaskList();

        Scanner input = new Scanner(System.in);
        String command = input.nextLine();


        while(!command.equals("bye")) {
            System.out.println(blankLines);

            try {
                String type = Task.checkCommandType(command);
                switch(type) {
                    case "list":
                        if (Task.tasks.isEmpty()) {
                            System.out.println("    Congratulations! You have no tasks remaining!");
                        } else {
                            Task.printTaskList();
                        }
                        break;

                    case "done":
                        Task.markAsDone(command);
                        break;

                    case "delete":
                        Task.deleteTask(command);
                        break;

                    case "save":
                        Task.saveTaskList();
                        break;

                    default:
                        Task.addTask(command, type);
                        break;
                }
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                command = input.nextLine();
            }
        }
        System.out.println(blankLines + "\n    Bye. Hope to see you again!");
    }
}
