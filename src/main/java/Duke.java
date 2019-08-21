import java.net.SocketOption;
import java.util.Scanner;

public class Duke {

    static Task[] taskList = new Task[100];
    static int totalNumber = 0;

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
            String command = sc.nextLine();
            String[] words = command.split(" ", 2);
            String action = words[0];

            if (command.equals("bye")) {
                printBye();
                return;
            } else if (action.equals("done")) {
                int doneIndex = Integer.parseInt(words[1]);
                doneTask(doneIndex);
            } else if (action.equals("todo")) {
                addTodo(words[1]);
            } else if (action.equals("deadline")) {
                addDeadline(words[1]);
            } else if (action.equals("event")) {
                addEvent(words[1]);
            } else if (command.equals("list")) {
                printList();
            } else {
                addTask(command);
            }
        }
    }

    public static void printNumber() {
        String string = String.format("     Now you have %d tasks in the list.", totalNumber);
        System.out.println(string);
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < totalNumber; i++) {
            int index = i + 1;
            System.out.println("     " + index + ". " + taskList[i]);
        }
        System.out.println("    ____________________________________________________________");

    }

    // remove
    public static void addTask(String command) {
        taskList[totalNumber] = new Task(command);
        totalNumber++;
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + command);
        System.out.println("    ____________________________________________________________");
    }

    public static void doneTask(int num) {
        Task currentTask = taskList[num - 1];
        currentTask.markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + currentTask);
        System.out.println("    ____________________________________________________________");
    }

    public static void addTodo(String action) {
        ToDo todo = new ToDo(action);
        taskList[totalNumber] = todo;
        totalNumber++;

        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + todo);
        printNumber();
        System.out.println("    ____________________________________________________________");
    }

    public static void addDeadline(String action) {
        String[] actionAndTime = action.split("/by");
        Deadline deadline = new Deadline(actionAndTime[0], actionAndTime[1]);
        taskList[totalNumber] = deadline;
        totalNumber++;

        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + deadline);
        printNumber();
        System.out.println("    ____________________________________________________________");
    }

    public static void addEvent(String action) {
        String[] actionAndTime = action.split("/at");
        Event event = new Event(actionAndTime[0], actionAndTime[1]);
        taskList[totalNumber] = event;
        totalNumber++;

        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + event);
        printNumber();
        System.out.println("    ____________________________________________________________");
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

}
