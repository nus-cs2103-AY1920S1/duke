import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printStoredArray(ArrayList<String> taskList) {
        int counter = 1;
        for (String task : taskList) {
            System.out.println(counter + ". " + task);
            counter++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>(100);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printStoredArray(taskList);
            } else {
                System.out.println("added: " + input);
                taskList.add(input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
