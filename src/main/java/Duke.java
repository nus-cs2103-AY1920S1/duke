import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TodoList todos = new TodoList();

        OutputHandler.sayHi();

        while (true) {
            String input = sc.nextLine();

            switch (input) {
                case "list":
                    todos.printTasks();
                    break;
                case "bye":
                    OutputHandler.sayBye();
                    return;
                default:
                    todos.addTask(input);

            }

        }

    }
}
