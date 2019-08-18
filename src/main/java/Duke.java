import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Main method.
     * 
     * @param args arguments passed into main
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> task = new ArrayList<>();
        int counter = 0;

        System.out.println("Hello I'm Duke\n" + "What can I do for you?");

        String textInput = sc.nextLine();
        while (!textInput.equals("bye")) {
            try {
                if (textInput.equals("list")) {
                    String isPlural = counter == 1 ? "is" : "are";
                    String taskIfPlural = counter == 1 ? "task" : "tasks";
                    System.out.println("Here " + isPlural + " the " + taskIfPlural + " in your list:");
                    for (int i = 1; i <= counter; i++) {
                        System.out.println(i + "." + task.get(i - 1));
                    }
                } else if (textInput.startsWith("done")) {
                    if (textInput.equals("done") || textInput.equals("done ")) {
                        throw new DukeException("OOPS!!! Index required.");
                    }
                    int completedIndex = Integer.parseInt(textInput.replaceFirst("done ", "")) - 1;
                    Task markAsDoneTask = task.get(completedIndex);
                    markAsDoneTask.markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markAsDoneTask);
                } else if (textInput.startsWith("todo")) {
                    if (textInput.equals("todo") || textInput.equals("todo ")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String description = textInput.replaceFirst("todo ", "");
                    task.add(new Todo(description));
                    counter = (new Duke()).printAddedTask(task, counter);
                } else if (textInput.startsWith("deadline")) {
                    if (textInput.equals("deadline") || textInput.equals("deadline ")) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String removeTaskWord = textInput.replaceFirst("deadline ", "");
                    String[] taskSplit = removeTaskWord.split(" /by ");
                    task.add(new Deadline(taskSplit[0], taskSplit[1]));
                    counter = (new Duke()).printAddedTask(task, counter);
                } else if (textInput.startsWith("event")) {
                    if (textInput.equals("event") || textInput.equals("event ")) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }

                    String removeTaskWord = textInput.replaceFirst("event ", "");
                    String[] taskSplit = removeTaskWord.split(" /at ");
                    task.add(new Event(taskSplit[0], taskSplit[1]));
                    counter = (new Duke()).printAddedTask(task, counter);
                } else if (textInput.startsWith("delete")) {
                    if (textInput.equals("delete") || textInput.equals("delete ")) {
                        throw new DukeException("OOPS!!! Index required.");
                    }

                    int deletedIndex = Integer.parseInt(textInput.replaceFirst("delete ", "")) - 1;
                    Task deletedTask = task.remove(deletedIndex);
                    counter--;

                    String taskIfPlural = counter <= 1 ? "task" : "tasks";
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deletedTask);
                    System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.err.println(e);
            }
            textInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

        // Close the scanner
        sc.close();

    }

    private int printAddedTask(ArrayList<Task> task, int counter) {
        System.out.println("Got it. I've added this task:\n" + task.get(counter));
        counter++;
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
        return counter;
    }
}
