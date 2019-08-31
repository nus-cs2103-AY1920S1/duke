import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
        out("Hello! I'm Duke");
    }

    public String read() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public static void out(String output) {
        System.out.println(output);
    }

    /**
     * Prints out list of tasks
     * @param taskList List of tasks
     */
    public static void list(TaskList taskList) {
        if (taskList.size() == 0) {
            out("List is empty!");
        } else {
            out("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                out((i + 1) + "." + taskList.get(i));
            }
        }
    }
}
