import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static ArrayList<Task> listOfTasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (sc.hasNext()) {
            String input = sc.next();

            if (input.equals("bye")) {
                break;
            }

            switch (input) {
            case "list":
                handleList();
                break;
            case "done":
                handleDone(sc.nextInt());
                break;
            default:
                handleInput(input);
                break;
            }
        }

        sc.close();

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleList() {
        int index = 1;
        for (Task task : listOfTasks) {
            System.out.printf("%d.%s\n", index, task);
            index++;
        }
    }

    private static void handleInput(String input) {
        listOfTasks.add(new Task(input));
        System.out.println(input);
    }

    private static void handleDone(int input) {
        listOfTasks.get(input - 1).markAsDone();
    }
}
