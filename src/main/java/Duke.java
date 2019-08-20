import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static class Task {
        protected boolean isDone = false;
        protected String taskName;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        public String getStatusIcon() {
            return (isDone ? "\u2713" : " \u2718");
        }

        public void complete() {
            this.isDone = true;
        }

        public String toString() {
            return this.getStatusIcon() + " " + this.taskName;
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scanner  = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i));
                }
            } else if (input.contains("done")) {
                String[] inputs = input.split(" ");
                int number =  Integer.parseInt(inputs[1]) - 1;
                tasks.get(number).complete();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(number));
            }
            else {
                Task task = new Task(input);
                System.out.println("added: " + input);
                tasks.add(task);
            }
        }
    }
}
