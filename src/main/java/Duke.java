import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Setup Duke.
     * @param args Setup arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Run Duke.
     */
    private void run() {
        sayGreeting();
        String input;
        Scanner sc = new Scanner(System.in);

        do {
            input = sc.nextLine();
            Scanner line = new Scanner(input);
            String command = line.next();
            switch (command) {
            case "todo":
            case "deadline":
            case "event":
                addTask(input);
                break;
            case "list":
                printTasks();
                break;
            case "done":
                doneTask(line.nextInt());
                break;
            case "bye":
                break;
            default:
                printFormatted("I do not understand.");
            }
        } while (!input.equals("bye"));

        sayBye();
    }

    private void sayGreeting() {
        printFormatted("Hello! I'm Duke\nWhat can I do for you?");
    }

    private void sayBye() {
        printFormatted("Bye. Hope to see you again soon!");
    }

    private void printFormatted(String output) {
        String horLine = "\t____________________________________________________________";
        String[] lines = output.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s\n", horLine));
        for (String line : lines) {
            stringBuilder.append(String.format("\t %s\n", line));
        }
        stringBuilder.append(String.format("%s\n", horLine));
        System.out.println(stringBuilder);
    }

    private void addTask(String text) {
        String[] input = text.split(" ", 2);
        Task task;
        switch(input[0]) {
        case "event": {
            String[] desc = input[1].split(" /at ");
            task = new Event(desc[0], desc[1]);
            break;
        }
        case "deadline": {
            String[] desc = input[1].split(" /by ");
            task = new Deadline(desc[0], desc[1]);
            break;
        }
        default:
            task = new Todo(input[1]);
        }
        this.tasks.add(task);
        printFormatted(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                                     task.toString(), this.tasks.size(), this.tasks.size() > 1 ? "tasks" : "task"));
    }

    private void printTasks() {
        StringBuilder lines = new StringBuilder();
        int counter = 0;
        lines.append("Here are the tasks in your list:\n");
        for (Task task : this.tasks) {
            counter++;
            lines.append(String.format("%d. %s\n", counter, task.toString()));
        }
        printFormatted(lines.toString());
    }

    private void doneTask(int id) {
        Task task = this.tasks.get(id - 1);
        task.markAsDone();
        printFormatted(String.format("Nice! I've marked this task as done:\n  %s", task));
    }
}
