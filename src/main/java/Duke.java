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
        Task[] task = new Task[100];
        int counter = 0;

        System.out.println("Hello I'm Duke\n" + "What can I do for you?");

        String textInput = sc.nextLine();
        while (!textInput.equals("bye")) {
            if (textInput.equals("list")) {
                for (int i = 1; i <= counter; i++) {
                    System.out.println(i + ".[" + task[i - 1].getStatusIcon() + "] " + task[i - 1].getDescription());
                }
            } else {
                String[] textInputSplit = textInput.split(" ");
                if (textInputSplit[0].equals("done")) {
                    int completedIndex = Integer.parseInt(textInputSplit[1]) - 1;
                    Task markAsDoneTask = task[completedIndex];
                    markAsDoneTask.markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + markAsDoneTask.getStatusIcon() + "] " + markAsDoneTask.getDescription());
                } else {
                    System.out.println("added: " + textInput);
                    task[counter] = new Task(textInput);
                    counter++;
                }
            }

            textInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

        // Close the scanner
        sc.close();
    }
}
