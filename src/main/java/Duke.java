import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();

        OutputUtilities.sayHi();

        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s");
            String part1 = parts[0];
            switch (part1) {
                case "list":
                    tasks.printTasks();
                    break;
                case "done":
                    int id = Integer.parseInt(parts[1]);
                    tasks.markTaskAsCompleted(id);
                    break;
                case "bye":
                    OutputUtilities.sayBye();
                    return;
                default:
                    tasks.addTask(input);

            }

        }

    }
}
