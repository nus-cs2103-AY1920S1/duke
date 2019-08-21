import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        printLogo();
        printWelcome();

        while (true) {
            if (sc.hasNextLine()) {
                String command = sc.next();
                switch (command) {
                    case "bye":
                        printBye();
                        return;
                    case "list":
                        printList(taskList);
                        break;
                    case "done":
                        int itemIndex = sc.nextInt();
                        Task currTask = taskList.get(itemIndex - 1);
                        currTask.doTask();
                        printDone(currTask);
                        break;
                    default:
                        String fullCommand = command + sc.nextLine();
                        Task task = new Task(fullCommand);
                        taskList.add(task);
                        printAddition(task);
                        break;
                }
            }
        }
    }

    private static void printIndent() {
        System.out.print("\t");
    }

    private static void printIndentWSpace() {
        System.out.print("\t ");
    }

    private static void printTopLine() {
        String line = "____________________________________________________________";
        printIndent();
        System.out.println(line);
    }

    private static void printBotLine() {
        String line = "____________________________________________________________";
        printIndent();
        System.out.println(line);
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printWelcome() {
        printTopLine();
        printIndentWSpace();
        System.out.println("Hello! I'm Duke");
        printIndentWSpace();
        System.out.println("What can I do for you?");
        printBotLine();
    }

    private static void printBye() {
        printTopLine();
        printIndentWSpace();
        System.out.println("Bye. Hope to see you again soon!");
        printBotLine();
    }

    private static void printMessage(String msg) {
        printTopLine();
        printIndentWSpace();
        System.out.println(msg);
        printBotLine();
    }

    private static void printAddition(Task task) {
        printMessage(("added: " + task.description));
    }

    private static void printList(ArrayList<Task> list) {
        int count = 1;
        printTopLine();
        printIndentWSpace();
        System.out.println("Here are the tasks in your list:");
        for(Task item: list) {
            printIndentWSpace();
            System.out.println(count + "." + item);
            count++;
        }
        printBotLine();
    }

    private static void printDone(Task task) {
        printTopLine();
        printIndentWSpace();
        System.out.println("Nice! I've marked this task as done: ");
        printIndentWSpace();
        System.out.println("   " + task);
        printBotLine();
    }
}
