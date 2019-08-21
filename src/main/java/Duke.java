import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public ArrayList<String> tasks;

    public Duke() {
        tasks = new ArrayList<>();
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    //Lists out all the tasks in Duke
    public void list() {
        int number = 1;
        for (String task : tasks) {
            String outputString = number + ". " + task;
            System.out.println(outputString);
            number++;
        }
    }

    public void add(String task) {
        tasks.add(task);
        String outputString = "added: " + task;
        System.out.println(outputString);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")){
                duke.list();
            } else {
                duke.add(command);
            }
        }
        duke.bye();
    }
}