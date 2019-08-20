import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> myList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printMessage("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                StringBuilder myBuilder = new StringBuilder();
                myBuilder.append("Here are the tasks in your list:\n");
                for (int i = 1; i <= myList.size(); i++) {
                    Task myTask = myList.get(i - 1);
                    myBuilder.append(i + "." + myTask);
                    if (i < myList.size()) {
                        myBuilder.append("\n");
                    }
                }
                printMessage(myBuilder.toString());
            } else if (input.contains("done")) {
                int myNum = Integer.valueOf(input.substring(5));
                myList.get(myNum - 1).markAsDone();
                printMessage("Nice! I've marked this task as done:\n  " + myList.get(myNum - 1));
            } else {
                printMessage("added: " + input);
                myList.add(new Task(input));
            }
        }
        in.close();
    }

    private static void printMessage(String message) {
        String[] messages = message.split("\n");
        System.out.println("    ____________________________________________________________");
        for (String line : messages) {
            System.out.println("     " + line);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }
}
