import java.net.SocketOption;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> taskList = new ArrayList<>();
    //static int totalNumber = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        readList(sc);
    }

    public static void readList(Scanner sc) {
        while(sc.hasNext()) {
            try {
                String command = sc.nextLine();
                String[] words = command.split(" ", 2);
                String action = words[0];

                if (command.equals("bye")) {
                    printBye();
                    return;
                } else if (action.equals("done")) {
                    Task.doneTask(words);
                } else if (action.equals("delete")) {
                    Task.deleteTask(words);
                } else if (action.equals("todo")) {
                    ToDo.addTodo(words);
                } else if (action.equals("deadline")) {
                    Deadline.addDeadline(words);
                } else if (action.equals("event")) {
                    Event.addEvent(words);
                } else if (command.equals("list")) {
                    printList();
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException ex) {
                System.out.println("    ____________________________________________________________");
                System.err.println("     " + ex);
                System.out.println("    ____________________________________________________________");
            }

        }
    }

    public static void printNumber() {
        String string = String.format("     Now you have %d tasks in the list.", taskList.size());
        System.out.println(string);
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            System.out.println("     " + index + ". " + taskList.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

}
