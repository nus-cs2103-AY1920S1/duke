import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DukeCLI {
    private TaskList taskList;
    // Main function
    public void run() {
        greet();
        // Check whether file exists, else create file
        taskList = new TaskList();

        // Try to load data
        taskList.loadData();

        // User input
        Scanner sc = new Scanner(System.in);
        String input = sc.next(); // Initial Input
        // Try is inside while loop so that user can continue to enter commands despite invalid commands
        while (!input.equals("bye")) {
            try {
                switch (input) {
                    case "list":
                        taskList.printList();
                        sc.nextLine();
                        break;
                    case "done":
                        taskList.completeTask(sc.nextInt());
                        break;
                    case "delete":
                        taskList.deleteTask(sc.nextInt());
                        break;
                    case "todo":
                        taskList.createTodo(sc.nextLine().trim());
                        break;
                    case "deadline":
                        taskList.createDeadline(sc.nextLine().trim());
                        break;
                    case "event":
                        taskList.createEvent(sc.nextLine().trim());
                        break;
                    default:
                        sc.nextLine();
                        handleDefault();
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Input Mismatch Exception caught");
                sc.nextLine();
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println(String.format("Index Out of Bounds Exception: %s", e.getMessage()));
            }
            catch(DukeException e) {
                System.out.println(e.getMessage());
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
            finally {
                input = sc.next();
            }
        }
        exit();
    }

    // Helper Functions
    private void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
    }

    private void handleDefault() {
        System.out.println(new DukeException().getMessage());
    }

    private void exit() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
