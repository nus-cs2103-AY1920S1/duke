import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Duke {
    /**
     * Prints DUKE.
     */
    private static void print(String str) {
        System.out.println(str);
    }

    /**
     * Prints a greeting and echoes user input.
     *
     * @param args takes in arguments.
     */
    public static void main(String[] args) {

        print("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        Task[] arr = new Task[100];
        int index = 0;

        while (sc.hasNextLine()) {
            String str = sc.nextLine();

            if (str.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            } else if (str.equals("list")) {
                print("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    print((i + 1) + ". " + arr[i]);
                }
            } else if (str.startsWith("done")) {
                //str = "done 1" / "done 5" but str is 1-indexed
                String[] instruction = str.split(" ");
                int whichTask = Integer.parseInt(instruction[1]) - 1;
                arr[whichTask].markAsDone();
                print("Nice! I've marked this task as done:");
                print(arr[whichTask].toString());
            } else {
                arr[index++] = new Task(str);
                print("added: " + str);

            }

        }
    }

    /**
     * Inner class to Duke. A Task class representing a task with a isDone component.
     */
    static class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        private String getStatusIcon() {
            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        }

        public void markAsDone() {
            isDone = true;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "]" + " " + description;
        }
    }
}
