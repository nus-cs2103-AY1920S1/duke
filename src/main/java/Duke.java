import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    static List<Task> listOfInputs = new ArrayList<>(100);

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
                    System.out.println(counter + "." + item.toString());
                }
            } else {
                String[] task = userInput.split(" ");
                String instruction = task[0];
                if(instruction.equals("done")) {
                    int taskNumber = Integer.parseInt(task[1]);
                    listOfInputs.get(taskNumber - 1).markedAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(listOfInputs.get(taskNumber - 1));
                } else if(instruction.equals("todo") || instruction.equals("deadline") || instruction.equals("event")){
                    System.out.println("Got it. I've added this task:");
                    switch(instruction) {
                        case "todo": {
                            Task todo = new Todo(userInput.substring(5));
                            listOfInputs.add(todo);
                            System.out.println(todo);
                            break;
                        }
                        case "deadline": {
                            Task deadline = new Deadline(userInput.substring(9, userInput.indexOf("/by")), userInput.substring(userInput.indexOf("/by") + 4));
                            listOfInputs.add(deadline);
                            System.out.println(deadline);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    System.out.println("Now you have " + listOfInputs.size() + " tasks in the list.");
                } else {
                    // ignore
                }
            }
        }

    }
}
