import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    public static ArrayList<Task> myList = new ArrayList<>();

    public static void printWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printClose();
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        printClose();
    }

    public static void printClose() {
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void handleTask(String command) {
        String[] extractCommand = command.split("\\s+", 2);
        if (extractCommand[0].equals("todo")) {
            Task currTask = new Todo(extractCommand[1]);
            myList.add(currTask);
            printTask(currTask);
        } else if (extractCommand[0].equals("deadline")) {
            String[] currArray = extractCommand[1].split(" /by ", 2);
            Task currTask = new Deadline(currArray[0], currArray[1]);
            myList.add(currTask);
            printTask(currTask);
        } else {
            String[] currArray = extractCommand[1].split(" /at ", 2);
            Task currTask = new Event(currArray[0], currArray[1]);
            myList.add(currTask);
            printTask(currTask);
        }
    }

    public static void printTask(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        printListSize();
        printClose();     
    }

    public static void printListSize() {
        if (myList.size() == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            System.out.println("     Now you have " + myList.size() + " tasks in the list.");
        }
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        int counter = 1;
        for (Task task : myList) {
            System.out.println("     " + counter + "." + task);
            counter++;
        }
        printClose();
    }

    public static void printDone(String command) {
        String[] currArray = command.split("\\s+", 2);
        int currStep = Integer.parseInt(currArray[1]);
        Task currTask = myList.get(currStep - 1);
        currTask.markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + currTask);
        printClose();
    }

    public static void main(String[] args) {
        printWelcome();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            String[] currArray = currLine.split("\\s+");
            if (currLine.equals("bye")) {
                printBye();
                sc.close();
                System.exit(0);
            } else if (currLine.equals("list")) {
                printList();
            } else if (currArray[0].equals("done")) {
                printDone(currLine);
            } else {
                handleTask(currLine);
            }
        }
    }
}
