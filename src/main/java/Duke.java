import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *  CS2103 Week 2 iP Deliverable, To-do List
 *  @author Ahmed Bahajjaj
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        ArrayList<Task> tasks = new ArrayList<>();
        String filePath = "data/duke.txt";
        File file = new File(filePath);
        try {
            Scanner sf = new Scanner(file);
            while (sf.hasNext()) {
                String[] code = sf.nextLine().split("\\|");
                tasks.add(create(code));
            }
            sf.close();
            list(tasks);
        } catch (FileNotFoundException ex) {
            System.out.println("You do not have any outstanding tasks.");
        }
        Scanner sc = new Scanner(System.in);
        boolean hi = true;
        System.out.println("What can I do for you?");
        while(hi) {
            try{
                String input = sc.nextLine();
                String task = input.split(" ")[0];
                int value;
                switch (task) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    hi = false;
                    break;
                case "list":
                    list(tasks);
                    break;
                case "done":
                    value = index(input, tasks.size());
                    tasks.get(value - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(value - 1));
                    break;
                case "delete":
                    value = index(input, tasks.size());
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(value - 1));
                    tasks.remove(value - 1);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                default:
                    tasks.add(create(task, input));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("OOPS!!! The details of an Event/Deadline cannot be empty! :(");
            } catch (UnsupportedOperationException ex) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means! :(");
            } catch (NumberFormatException ex) {
                System.out.println("Please only complete/delete tasks on the list! D:");
            }
        }
        sc.close();
        try {
            if (tasks.isEmpty() && file.exists()) {
                Files.delete(Paths.get(filePath));
            } else if (!tasks.isEmpty()) {
                String taskList = "";
                for (Task item : tasks) {
                    taskList += item.store() + System.lineSeparator();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(taskList);
                fileWriter.close();
            }
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }

    /**
     * Returns the index of the list to be managed
     * @param input User command
     * @param size Size of task list
     * @return User value specified
     * @throws NumberFormatException If number out of range or invalid input
     */
    private static int index(String input, int size) throws NumberFormatException {
        int value = Integer.parseInt(input.substring(input.length() - 1));
        if (value < 0 || value > size) {
            throw new NumberFormatException();
        }
        return value;
    }

    /**
     * Prints out list of tasks
     * @param tasks List of tasks
     */
    private static void list(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("List is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Processes Input Event/Deadline to Description & Details
     * @param input Task String (without command)
     * @return Array. Index 0 = Description. Index 1  = Details
     * @throws ArrayIndexOutOfBoundsException Command missing details
     */
    private static String[] process(String input) throws ArrayIndexOutOfBoundsException {
        String[] desc = input.split("/");
        String[] temp = desc[1].split(" ");
        desc[1] = desc[1].replace(temp[0], "");
        //desc[1] = " (" + temp[0] + ": " + desc[1] + ")";
        return desc;
    }

    /**
     * Creates Task from input command and description
     * @param task User input separated by spaces
     * @param input User input unprocessed
     * @return Task
     * @throws UnsupportedOperationException Command not understood
     */
    private static Task create(String task, String input) throws UnsupportedOperationException {
        input = input.replace(task, "");
        switch (task) {
        case "todo":
            return new Todo(input);
        case "deadline":
            String[] dead = process(input);
            return new Deadline(dead[0], dead[1]);
        case "event":
            String[] event = process(input);
            return new Event(event[0], event[1]);
        default:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates Task from stored task data
     * @param code Stored task data
     * @return Task
     */
    private static Task create(String[] code) {
        boolean done = "1".equals(code[1]);
        switch (code[0]) {
        case "T":
            return new Todo(code[2], done);
        case "D":
            return new Deadline(code[2], code[3], done);
        case "E":
            return new Event(code[2], code[3], done);
        default:
            return null;
        }
    }
}
