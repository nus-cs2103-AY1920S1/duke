import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Setup Duke.
     * @param args Setup arguments
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Run Duke.
     */
    public void run() {
        sayGreeting();
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextLine();
            String command = input.split(" ")[0];
            switch (command) {
            case "list":
                printTasks();
                break;
            case "done":
                doneTask(Integer.parseInt(input.split(" ")[1]));
            case "bye":
                break;
            default:
                addTask(input);
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
        stringBuilder.append(horLine + "\n");
        for (String line : lines) {
            stringBuilder.append("\t " + line + "\n");
        }
        stringBuilder.append(horLine + "\n");
        System.out.println(stringBuilder);
    }

    private void addTask(String text) {
        this.tasks.add(new Task(text));
        printFormatted("added: " + text);
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
