import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> actions = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        greetUser();
        drawLine();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                sayBye();
                input.close();
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.contains("done")) {
                String listActionIndex = (userInput.split(" "))[1];
                int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
                markAsDone(arrayActionIndex);

            }else {
                addToList(userInput);
            }
        }
    }

    public static void markAsDone(int index) {
        Task action = actions.get(index);
        action.setDone();
        drawLine();
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.println("\t\t " + action);
        drawLine();
    }
    public static void sayBye() {
        drawLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        drawLine();
    }

    public static void printList() {
        drawLine();
        System.out.println("\t Here are the tasks in your list: ");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println("\t " + (i + 1) + ". " + actions.get(i));
        }
        drawLine();
    }
    public static void addToList(String input) {
        drawLine();
        actions.add(new Task(input));
        System.out.println("\t added: " + input);
        drawLine();
    }

    public static void drawLine() {
        System.out.println("\t---------------------------------------");
    }

    public static void greetUser() {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
    }
}
