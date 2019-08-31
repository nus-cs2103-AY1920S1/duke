import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    public void run (TaskList tasks, Storage storage) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Parser parser = new Parser(storage, tasks);
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                String command = sc.next();
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else {
                    parser.read(command, sc);
                }
            }
        } catch(Exception ex) {
            System.out.println("File not Found");
        }
    }
    public void showLoadingError() {
        System.out.println("File not available");
    }
}
