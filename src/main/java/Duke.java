import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    protected static Scanner sc;
    protected static ArrayList<Task> taskList;
    protected static boolean byeFlag = false;
    protected static final String filePath = "data/duke.txt";

    public static void main(String[] args) throws IOException {
        taskList = new ArrayList<>();
        sc = new Scanner(System.in);

        try {
            readFileContents(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while (!byeFlag) {
            try {
                duke();
            } catch (DukeException ex) {
                System.err.println(ex);
            }
        }

        try {
            writeToFile(filePath);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void duke() throws DukeException {
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String command = getCommand(input);

            switch (command) {
                case "list":
                    list();
                    break;
                case "done":
                    done(getDescription(input, command));
                    break;
                case "todo":
                    updateAdd(todo(getDescription(input, command)));
                    break;
                case "deadline":
                    updateAdd(deadline(getDescription(input, command), getDetail(input, command)));
                    break;
                case "event":
                    updateAdd(event(getDescription(input, command), getDetail(input, command)));
                    break;
                case "delete":
                    updateRemove(delete(getDescription(input, command)));
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        byeFlag = true;
    }

    public static String getCommand(String input) {
        String command;

        if (input.contains(" ")) {
            int index = input.indexOf(" ");
            command = input.substring(0, index);
        } else {
            command = input;
        }

        return command;
    }

    public static String getDescription(String input, String command) throws DukeException {
        String action = "";

        if (input.contains(" ") && input.contains("/")) {
            int index = input.indexOf(" ") + 1;
            String removeCommand = input.substring(index);
            String[] split = removeCommand.split("/", 2);
            action = split[0];
        } else if (input.contains(" ")) {
            String[] split = input.split(" ", 2);
            action = split[1];
        }

        if (action.equals("")) {
            throw new DukeException("OOPS!!! The description of a " + command + " cannot be empty.");
        }

        return action;
    }

    public static String getDetail(String input, String command) throws DukeException {
        String detail = "";

        if (input.contains(" ") && input.contains("/")) {
            int index = input.indexOf(" ");
            String removeCommand = input.substring(index);
            String[] split = removeCommand.split("/", 2);
            index = split[1].indexOf(" ") + 1;
            detail = split[1].substring(index);
        }

        if (detail.equals("")) {
            throw new DukeException("OOPS!!! The time and date of a " + command + " cannot be empty.");
        }

        return detail;
    }

    public static void updateAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        if (taskList.size() > 1) {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " task in the list.");
        }
    }

    public static void updateRemove(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + task);

        if (taskList.size() > 1) {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " task in the list.");
        }
    }


    public static void list() {
        if (taskList.size() > 0) {
            for (int i = 0; i < taskList.size(); i++) {
                int index = i + 1;
                System.out.println(index + "." + taskList.get(i));
            }
        } else {
            System.out.println("You have no tasks in the list.");
        }
    }

    public static void done(String position) throws DukeException {
        int index = Integer.parseInt(position) - 1;
        if (index < taskList.size()) {
            Task task = taskList.get(index);
            task.setDone(true);
        } else {
            throw new DukeException("OOPS!!! The task you want to complete is not in the list!");
        }
    }

    public static Task todo(String action) {
        Task task = new Todo(action);
        taskList.add(task);

        return task;
    }

    public static Task deadline(String action, String detail) {
        Task task = new Deadline(action, detail);
        taskList.add(task);

        return task;
    }

    public static Task event(String action, String detail) {
        Task task = new Event(action, detail);
        taskList.add(task);

        return task;
    }

    public static Task delete(String position) throws DukeException {
        int index = Integer.parseInt(position) - 1;
        Task task;

        if (index < taskList.size()) {
            task = taskList.get(index);
            taskList.remove(index);
        } else {
            throw new DukeException("OOPS!!! The task you want to remove is not in the list!");
        }

        return task;
    }

    public static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList) {
            String text = task.getFileFormat();
            fw.write(text + System.lineSeparator());
        }
        fw.close();
    }

    public static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            readAsObject(s.nextLine());
        }
    }

    public static void readAsObject(String item) {
        char type = item.charAt(0);
        String[] split = item.split(" \\| ");

        switch (type) {
            case 'T':
                todo(split[2]);
                break;
            case 'D':
                deadline(split[2], split[3]);
                break;
            case 'E':
                event(split[2], split[3]);
                break;
            default:
                System.out.println("OOPS!!! There is an invalid task in the file!");
        }

        if (split[1].equals("+")) {
            int lastIndex = taskList.size() - 1;
            (taskList.get(lastIndex)).setDone(true);
        }
    }
}