import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static final ArrayList<Task> listOfInputs = new ArrayList<>(100);

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int counter = 0;
                for (Task item : listOfInputs) {
                    counter++;
                    System.out.println(counter + "." + item.printWithStatus());
                }
            } else {
                String[] task = userInput.split(" ");
                if (task[0].equals("done")) {
                    int taskNumber = Integer.parseInt(task[1]);
                    listOfInputs.get(taskNumber - 1).markedAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(listOfInputs.get(taskNumber - 1).printWithStatus());
                } else {
                    Task userTask = new Task(userInput);
                    System.out.println("added: " + userTask);
                    listOfInputs.add(userTask);
                }
            }
        }

    }
}
