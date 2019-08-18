import java.util.Scanner;

public class Duke {

    /**
     * Main method.
     * 
     * @param args arguments passed into main
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] task = new Task[100];
        int counter = 0;

        System.out.println("Hello I'm Duke\n" + "What can I do for you?");

        String textInput = sc.nextLine();
        while (!textInput.equals("bye")) {
            if (textInput.equals("list")) {
                String isPlural = counter == 1 ? "is" : "are";
                String taskIfPlural = counter == 1 ? "task" : "tasks";
                System.out.println("Here " + isPlural + " the " + taskIfPlural + " in your list:");
                for (int i = 1; i <= counter; i++) {
                    System.out.println(i + "." + task[i - 1]);
                }
            } else {
                if (textInput.startsWith("done")) {
                    int completedIndex = Integer.parseInt(textInput.replaceFirst("done ", "")) - 1;
                    Task markAsDoneTask = task[completedIndex];
                    markAsDoneTask.markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markAsDoneTask);
                } else if (textInput.startsWith("todo")) {
                    String description = textInput.replaceFirst("todo ", "");
                    task[counter] = new Todo(description);

                    System.out.println("Got it. I've added this task:\n" + task[counter]);
                    counter++;
                    String taskIfPlural = counter == 1 ? "task" : "tasks";
                    System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
                } else if (textInput.startsWith("deadline")) {
                    String removeTaskWord = textInput.replaceFirst("deadline ", "");
                    String[] taskSplit = removeTaskWord.split(" /by ");
                    task[counter] = new Deadline(taskSplit[0], taskSplit[1]);

                    System.out.println("Got it. I've added this task:\n" + task[counter]);
                    counter++;
                    String taskIfPlural = counter == 1 ? "task" : "tasks";
                    System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
                } else if (textInput.startsWith("event")) {
                    String removeTaskWord = textInput.replaceFirst("event ", "");
                    String[] taskSplit = removeTaskWord.split(" /at ");
                    task[counter] = new Event(taskSplit[0], taskSplit[1]);

                    System.out.println("Got it. I've added this task:\n" + task[counter]);
                    counter++;
                    String taskIfPlural = counter == 1 ? "task" : "tasks";
                    System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
                }
            }

            textInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

        // Close the scanner
        sc.close();
    }
}
