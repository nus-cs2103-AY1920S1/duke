import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    protected Scanner scan = new Scanner(System.in);
    protected TaskList tL = new TaskList(new ArrayList<>());
    protected Storage store = new Storage(Storage.file);

    /**
     * Constructor for Ui.
     */
    public Ui() {

    }

    /**
     * Prints the greeting at the initiation of the chat bot.
     */
    public void greeting() {
        String logo = "     ____        _        \n" +
                      "    |  _ \\ _   _| | _____ \n" +
                      "    | | | | | | | |/ / _ \\\n" +
                      "    | |_| | |_| |   <  __/\n" +
                      "    |____/ \\__,_|_|\\_\\___|\n";

        // Prints out greeting of the chat bot.
        printLine();
        printIndent();
        System.out.println("Hello! My name is \n" + logo + "\n" +
                "    What can I do for you? \n");
        printIndent();
        System.out.println("I can only do these functions for now: \n \n" +
                "    Todo \n" + "        Eg. todo __(task)__\n" +
                "    Event \n" + "        Eg. event __(task)__ /at _(dd/MM/yyyy)_(hhmm)__\n" +
                "    Deadline \n" + "        Eg. deadline __(task)__ /by _(dd/MM/yyyy)_(hhmm)__\n" +
                "    Delete \n" + "        Eg. delete __(number)__ or delete all\n" +
                "    Done \n" + "        Eg. done __(number)__\n" +
                "    List \n");
        printIndent();
        System.out.println("Ill be adding in more features soon! Please be patient! :)");
        printLine();
    }

    /**
     * Scans the input from the user and does the respective
     * command based on the input. Prints out error messages as well.
     */
    public void nextCommand() {
        while (scan.hasNext()) {
            try {
                String text = scan.nextLine().trim();
                if (text.equals("bye")) {
                    Ui.printBye();
                    break;
                } else if (text.equals("list")) {
                    printList();
                } else if (text.equals("delete all")) {
                    tL.deleteAllCommand(text);
                } else if (text.contains(" ")) {
                    String[] splittedText = text.split(" ");
                    if (splittedText[0].equals("done")) {
                        int num = text.indexOf(" ");
                        int taskNumber = Integer.parseInt(text.substring(num + 1, num + 2));
                        if (taskNumber > 0 && taskNumber <= TaskList.listOfTasks.size()) {
                            printDone(taskNumber);
                        } else {
                            throw new DukeException("☹ OOPS!!! There is no such task " +
                                    "number in your list of tasks!! Please enter a valid number!");
                        }
                    } else if (splittedText[0].equals("delete")) {
                        tL.deleteCommand(text);
                    } else {
                        if (splittedText[0].equals("todo")) {
                            tL.toDoCommand(text);
                        } else if (splittedText[0].equals("deadline") &&
                                text.contains("/") && text.contains("by")) {
                            tL.deadlineCommand(text);
                        } else if (splittedText[0].equals("event") &&
                                text.contains("/") && text.contains("at")) {
                            tL.eventCommand(text);
                        } else {
                            printLine();
                            printIndent();
                            throw new DukeException("☹ OOPSY DAISY!!! Please follow the correct format! :<");
                        }
                    }
                } else {
                    printLine();
                    printIndent();
                    switch (text) {
                        case "todo":
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. " +
                                    "It must be in proper format (i.e. todo clean table).");
                        case "deadline":
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty. " +
                                    "It must be in proper format (i.e. deadline return book /by 23 Aug).");
                        case "event":
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty. " +
                                    "It must be in proper format (i.e. event Don's birthday /at 15 Jan 3pm).");
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException | IOException e) {
                System.out.println(e);
                printLine();
            }
        }
    }

    /**
     * Prints indentation.
     * Helps to order the output, making it much neater.
     */
    public static void printIndent() {
        System.out.print("    ");
    }

    /**
     * Prints line.
     * Helps to order the output and makes it
     * much neater.
     */
    public static void printLine() {
        printIndent();
        System.out.println("_____________________" +
                "______________________________________________");
    }

    /**
     * Ends the chat bot.
     */
    private static void printBye() {
        printLine();
        printIndent();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Marks a task as done once the user has finished it.
     *
     * @param i To indicate which task number is done.
     */
    private static void printDone(int i) {
        TaskList.listOfTasks.get(i-1).markAsDone();
        printLine();
        printIndent();
        System.out.println("Nice! I've marked this task as done:");
        printIndent();
        System.out.println(TaskList.listOfTasks.get(i-1).toString());
        printLine();
    }

    /**
     * To remove a task if it is not needed anymore.
     *
     * @param i Indicates the task number that is done.
     */
    public static void printDelete(int i) {
        printIndent();
        System.out.println(TaskList.listOfTasks.get(i-1).toString());
        printLine();
    }

    /**
     * Prints the list of tasks that has been added by the user.
     *
     * @throws FileNotFoundException if there is no such file that contains the tasks.
     */
    private static void printList() throws FileNotFoundException {
        printLine();
        printIndent();
        if (TaskList.listOfTasks.isEmpty()) {
            System.out.println("There is no tasks in your list currently!!!");
        } else {
            System.out.println("Here are the tasks in your list:");
            File temp = new File(Storage.file);
            Scanner s = new Scanner(temp);
            int numbering = 1;
            while (s.hasNext()) {
                printIndent();
                System.out.println(numbering + ". " + s.nextLine());
                numbering++;
            }
        }
        printLine();
    }

    /**
     * Gets the number of task inside the file.
     *
     * @return Number of tasks.
     * @throws IOException If the named file exists
     * but is a directory rather than a regular file,
     * does not exist but cannot be created, or
     * cannot be opened for any other reason.
     */
    public static int getNumOfTasks() throws IOException {
        return Storage.countLines(Storage.file);
    }

    /**
     * When there is nothing in the file, this method
     * will print out to the user, telling them that there
     * is no previous tasks saved in the file
     */
    public void showLoadingError() {
        printLine();
        printIndent();
        System.out.println("Nothing in file!");
    }
}