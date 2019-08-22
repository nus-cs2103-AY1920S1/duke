import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Greet user
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // Ask initial user input
        String userinput = scanner.nextLine();

        while (!userinput.equals("bye")) {
            // List
            if (userinput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                // Output current items in list
                for (int i = 0; i < tasks.size(); i++) {
                    int currentItemNumber = i + 1;
                    Task currentTask = tasks.get(i);
                    System.out.println(currentItemNumber + "." + currentTask);
                }
            }
            else {
                // Done
                String[] words = userinput.split(" ");
                if (words[0].equals("done")) {
                    int itemId = Integer.parseInt(words[1]);
                    Task currentTask = tasks.get(itemId - 1);
                    currentTask.setDone(true);
                    System.out.println("Nice! I've marked this task as done:\n[✓] " + currentTask.getName());
                }
                // Add
                else {
                    System.out.println("Got it. I've added this task:");
                    // to do
                    if (words[0].equals("todo")) {
                        // Remaining words
                        String remainingWords = userinput.replaceFirst("todo ", "");

                        // Add new task to list
                        Todo newTodo = new Todo(remainingWords, false);
                        tasks.add(newTodo) ;

                        System.out.println(newTodo);
                    }
                    // deadline
                    else if (words[0].equals("deadline")) {
                        // Remaining words
                        String remainingWords = userinput.replaceFirst("deadline ", "");
                        String[] remainingWords2 = remainingWords.split(" /by ", 2);

                        // Add new task to list
                        Deadline newDeadline = new Deadline(remainingWords2[0], false, remainingWords2[1]);
                        tasks.add(newDeadline);

                        System.out.println(newDeadline);
                    }
                    // event
                    else if (words[0].equals("event")) {
                        // Remaining words
                        String remainingWords = userinput.replaceFirst("event ", "");
                        String[] remainingWords2 = remainingWords.split(" /at ", 2);

                        // Add new task to list
                        Event newEvent = new Event(remainingWords2[0], false, remainingWords2[1]);
                        tasks.add(newEvent);

                        System.out.println(newEvent);
                    }
                    // default
                    else {
                        System.out.println("added: " + userinput);
                    }
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            }
            // Ask for next userinput
            userinput = scanner.nextLine();
        }

        // At this point userinput equals "bye"
        System.out.println("Bye. Hope to see you again soon!");
    }
}
