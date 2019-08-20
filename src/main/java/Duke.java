import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    boolean exited = false;
    List<Task> toDoList = new ArrayList<>();

    public void respond() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        while (!exited && scanner.hasNextLine()) {
            String input = scanner.nextLine();

            switch (input) {
                case "bye":
                    exited = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    int index = 1;
                    for (Task s : toDoList) {
                        System.out.println(index + ". " + s);
                        index++;
                    }
                    break;

                default:
                    if (input.contains("done")) {
                        int taskNum = Integer.parseInt(input.substring(5)) - 1;
                        Task updatedTask = toDoList.get(taskNum);
                        updatedTask.markAsDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(updatedTask);
                        break;

                    } else {
                        toDoList.add(new Task(input));
                        System.out.println("added: " + input);
                        break;
                    }
            }
        }
    }

    public static void main(String[] args) {
        Duke D1 = new Duke();
        D1.respond();
    }

}
