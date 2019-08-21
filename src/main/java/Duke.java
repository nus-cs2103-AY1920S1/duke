import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.regex.Matcher;

public class Duke {

    public static ArrayList<Task> myList = new ArrayList<>();

    public static void printWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void printAdded(String command) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + command);
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void printDone(String command) {
        Pattern pattern = Pattern.compile("(\\d)");
        Matcher matcher = pattern.matcher(command);
        int currStep = 0;
        while (matcher.find()) {
            currStep = Integer.parseInt(matcher.group(1));
        }
        Task currTask = myList.get(currStep - 1);
        currTask.markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       [" + currTask.getStatusIcon() + "] " + currTask.description);
        System.out.println("    ____________________________________________________________");
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        int counter = 1;
        for (Task task : myList) {
            System.out.println("     " + counter + ".[" + task.getStatusIcon() + "] " + task.description);
            counter++;
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void main(String[] args) {
        printWelcome();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            if (currLine.equals("bye")) {
                printBye();
                sc.close();
                System.exit(0);
            } else if (currLine.equals("list")) {
                printList();
            } else if (currLine.matches("done \\d")) {
                printDone(currLine);
            } else {
                Task currTask = new Task(currLine);
                myList.add(currTask);
                printAdded(currLine);
            }
        }
    }
}
