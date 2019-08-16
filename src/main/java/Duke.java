import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(welcomeMessage);
        Scanner reader = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String currentLine = reader.nextLine();
            if (currentLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (currentLine.equals("list")) {
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task: list) {
                    System.out.println("" + count + ". " + task);
                    count++;
                }
            } else {
                if (currentLine.startsWith("done")) {
                    int index = Integer.parseInt(currentLine.split(" ")[1]);
                    list.get(index - 1).setDone();
                } else {
                    Task current = new Task(currentLine);
                    list.add(current);
                    System.out.println("added: " + currentLine);
                }
            }
        }
    }
}