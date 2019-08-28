import todo.ToDoList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private static void run() {
        // Initializing IO
        Scanner sc = new Scanner(System.in);
        String input = "";

        ArrayList<String> fileInput = new ArrayList<>();

        // Try to create the file if it is not found
        // and throw IOException if file cannot be created
        try {
            File file = new File("data/duke.txt");
            System.out.println(file.getAbsolutePath());
            if (!file.isFile() && !file.createNewFile()) {
                throw new IOException("Error creating new file " +
                        file.getAbsolutePath());
            }
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()) {
                String nextLine = fileScanner.nextLine();
                System.out.println(nextLine);
                fileInput.add(nextLine);
            }

            fileScanner.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        ToDoList todo = new ToDoList(fileInput);

        printFormattedText("    Hello! I'm Duke\n" +
                "    What can I do for you?");

        boolean isRunning = true;

        while (isRunning) {
            try {
                input = sc.next().trim();
                int index;
                String remainingInput;

                switch (input) {
                    case "bye":
                        isRunning = false;
                        break;
                    case "done":
                        index = sc.nextInt();
                        printFormattedText(todo.markTaskDone(index));
                        break;
                    case "list":
                        printFormattedText(todo.displayList());
                        break;
                    case "delete":
                        index = sc.nextInt();
                        printFormattedText(todo.removeTask(index));
                        break;
                    case "todo":
                        remainingInput = sc.nextLine();
                        if (remainingInput.isBlank()) {
                            throw new DukeException("     \u2639 OOPS!!! The description of a " + input + " cannot be empty");
                        }
                        printFormattedText(todo.addTask("T", remainingInput));
                        break;
                    case "deadline":
                        remainingInput = sc.nextLine();
                        if (remainingInput.isBlank()) {
                            throw new DukeException("     \u2639 OOPS!!! The description of a " + input + " cannot be empty");
                        }
                        printFormattedText(todo.addTask("D", remainingInput));
                        break;
                    case "event":
                        remainingInput = sc.nextLine();
                        if (remainingInput.isBlank()) {
                            throw new DukeException("     \u2639 OOPS!!! The description of a " + input + " cannot be empty");
                        }
                        printFormattedText(todo.addTask("E", remainingInput));
                        break;
                    default:
                        throw new DukeException("     \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printFormattedText(e.getMessage());
            }
        }

        printFormattedText("    Bye. Hope to see you again soon!");

        String formattedList = todo.outputTasks();

        // Write the latest To Do List into the file
        try {
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            fileWriter.write(formattedList);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void printFormattedText(String text) {
        printDivider();
        System.out.println(text);
        printDivider();
    }

    private static void printDivider() {
        indent();
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    private static void indent() {
        for (int i = 0; i < 4; i++) System.out.print(" ");
    }
}
