import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {

    // Reading and Storing data from the hard disk
    public static void readFile(String filePath) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Task.initItemsLst(new Scanner(f));
    }

    // Saving new task list to the hard disk
    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String textToAdd = "";
        for (Task task : Task.itemsLst) {
            textToAdd += task.fileString() + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) throws FileNotFoundException, DukeException, IOException  {
        Scanner scanner = new Scanner(System.in);
        System.out.println(System.getProperty("user.dir"));
        String filePath = "." + File.separator + "data" + File.separator + "duke.txt";

        // Reading and Storing data from the hard disk
        readFile(filePath);

        String hLine = "    ____________________________________________________________";

        // Introductory message
        System.out.println(hLine);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.printf("%s\n\n", hLine);

        // Runs for as long as user does not terminate with "bye" command
        while(true) {

            String command = scanner.nextLine();

            try {
                // "bye" command to terminate the program
                if (command.equals("bye")) {
                    System.out.println(hLine);
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.printf("%s\n\n", hLine);
                    break;
                } else {
                    System.out.println(hLine);
                    // "list" command to list out all saved tasks
                    if (command.equals("list")) {
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < Task.itemsLst.size(); i++) {
                            System.out.printf("     %d.%s\n",
                                    i + 1, Task.itemsLst.get(i));
                        }
                    } else { // if command is neither list nor bye
                        Scanner tempSc = new Scanner(command);
                        String s = "";
                        if (tempSc.hasNext())
                            s = tempSc.next();
                        // "done" command to mark a task as done
                        if (s.equals("done")) {
                            int doneInt = tempSc.nextInt() - 1;
                            Task.itemsLst.get(doneInt).markAsDone();
                            System.out.printf("     Nice! I've marked this task as done:\n       %s\n"
                                    , Task.itemsLst.get(doneInt));
                        } else if (s.equals("delete")) {
                        // "delete" command to remove a task from the list
                            int delInt = tempSc.nextInt() - 1;
                            Task delTask = Task.itemsLst.remove(delInt);
                            System.out.printf("     Noted. I've removed this task:\n" +
                                    "       %s\n     Now you have %d tasks in the list.\n"
                                    , delTask, Task.itemsLst.size());
                        } else {
                        // Add a task to the list. A task is one of three types:
                        // ToDo, Deadline or Event task
                            if (command.startsWith("todo")) {
                            // Add a ToDo task
                                Scanner taskSc = new Scanner(command);
                                taskSc.next();
                                if (!taskSc.hasNextLine()) {
                                    throw new DukeException("     \u2639 OOPS!!! " +
                                            "The description of a todo cannot be empty.");
                                }
                                Task.itemsLst.add(new ToDo(taskSc.nextLine().substring(1), false));
                            } else if (command.startsWith("deadline")) {
                            // Add a Deadline task
                                Scanner taskSc = new Scanner(command);
                                taskSc.next();
                                String by = taskSc.nextLine().substring(1);
                                String description = "";
                                for (int i = 0; i < by.length(); i++) {
                                    char c = by.charAt(i);
                                    if (c != '/') {
                                        description += c;
                                    } else {
                                        by = by.substring(i + 4);
                                        // get rid of space
                                        description = description.substring(0, description.length() - 1);
                                        break;
                                    }
                                }
                                Task.itemsLst.add(new Deadline(description, by, false));
                            } else if (command.startsWith("event")) {
                            // Add an Event task
                                Scanner taskSc = new Scanner(command);
                                taskSc.next();
                                String at = taskSc.nextLine().substring(1);
                                String description = "";
                                for (int i = 0; i < at.length(); i++) {
                                    char c = at.charAt(i);
                                    if (c != '/') {
                                        description += c;
                                    } else {
                                        at = at.substring(i + 4);
                                        // get rid of space
                                        description = description.substring(0, description.length() - 1);
                                        break;
                                    }
                                }
                                Task.itemsLst.add(new Event(description, at, false));
                            } else {
                                throw new DukeException("     \u2639 OOPS!!! I'm sorry, " +
                                        "but I don't know what that means :-(");
                            }
                            System.out.printf("     Got it. I've added this task:\n       %s\n" +
                                    "     Now you have %d tasks in the list.\n",
                                        Task.itemsLst.get(Task.itemsLst.size() - 1), Task.itemsLst.size());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.printf("%s\n\n", hLine);

            // Save the new task list to the hard disk
            writeToFile(filePath);

        }

    }
}
