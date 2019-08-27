import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> tasks;
    private static String userInput;
    private static String[] inputStringArr;

    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetMsg = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(logo + '\n' + greetMsg);
    }

    public static void loadData() throws FileNotFoundException {
        File f = new File("data/duke.txt");
        Scanner sc = new Scanner(f);
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] data = s.split(" \\| ");
            String command = data[0];
            if (command.equals("T")) {
                Todo t = new Todo(data[2]);
                if (Integer.parseInt(data[1]) == 1) t.setDone();
                tasks.add(t);
            } else if (command.equals("D")) {
                Deadline d = new Deadline(data[2], data[3]);
                if (Integer.parseInt(data[1]) == 1) d.setDone();
                tasks.add(d);
            } else {
                Event e = new Event(data[2], data[3]);
                if (Integer.parseInt(data[1]) == 1) e.setDone();
                tasks.add(e);
            }
        }
    }

    public static void startDuke() {
        printGreeting();
        tasks = new ArrayList<>();
        try {
            loadData();
        } catch (FileNotFoundException ex) {

        }
    }

    public static void handleBye() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
        System.exit(0);
    }

    public static void handleList() throws DukeException {
        try {
            if (inputStringArr.length > 1) {
                throw new DukeException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! The list command should not be followed by other text!\n" +
                        "    ____________________________________________________________\n");
            } else {
                System.out.println("    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    int listNum = i + 1;
                    Task t = tasks.get(i);
                    if (t.getType().equals("todo")) {
                        System.out.println("    " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t.toString());
                    } else if (t.getType().equals("event")) {
                        System.out.println("    " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t.toString() + " (at: " + t.getDate() + ")");
                    } else {
                        System.out.println("    " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t.toString() + " (by: " + t.getDate() + ")");
                    }
                }
                System.out.println("    ____________________________________________________________\n");
            }
        } catch(DukeException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void handleBadCommand() throws DukeException {
        try {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________\n");
        } catch(DukeException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void handleDone() throws DukeException {
        try {
            if (inputStringArr.length > 1) {
                int taskNum = Integer.parseInt(inputStringArr[1]);
                int totalTasks = tasks.size();
                if (taskNum < 1 || taskNum > totalTasks) {
                    throw new DukeException("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! The number provided is not within the range of the list.\n" +
                            "    ____________________________________________________________\n");
                } else {
                    Task t = tasks.get(taskNum - 1);
                    boolean isDone = t.getStatus();
                    if (isDone) {
                        throw new DukeException("    ____________________________________________________________\n" +
                                "     ☹ OOPS!!! The task has already been marked as completed.\n" +
                                "    ____________________________________________________________\n");
                    } else {
                        t.setDone();
                        System.out.println("    ____________________________________________________________\n" +
                                "     Nice! I've marked this task as done: \n" +
                                "       [" + '+' + "] " + t + '\n' +
                                "    ____________________________________________________________\n");
                        rewriteFile();
                    }
                }
            } else {
                throw new DukeException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! Please specify the completed task's number.\n" +
                        "    ____________________________________________________________\n");
            }
        } catch(DukeException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void handleTodo() throws DukeException {
        try {
            if (inputStringArr.length > 1) {
                String taskName = ((userInput.split(" ", 2))[1]).strip();
                Todo t = new Todo(taskName);
                tasks.add(t);
                System.out.println("    ____________________________________________________________\n" +
                        "     Got it. I've added this task: \n" +
                        "       [T]" + "[ ]" + ' ' + t+ '\n' +
                        "     Now you have " + tasks.size() + " tasks in the list.\n" +
                        "    ____________________________________________________________\n");
                appendToFile("T | 0 | " + taskName + '\n');
            } else {
                throw new DukeException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                        "    ____________________________________________________________\n");
            }
        } catch(DukeException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void handleDeadline() {
        String[] separateTaskDate = userInput.split("/", 2);
        String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
        String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
        DateTime dt = new DateTime(date);
        Deadline d = new Deadline(taskName, dt.toString());
        tasks.add(d);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       [D][ ] " + d + " (by: " + dt + ")\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
        appendToFile("D | 0 | " + taskName + " | " + date + '\n');
    }

    public static void handleEvent() {
        String[] separateTaskDate = userInput.split("/", 2);
        String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
        String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
        DateTime dt = new DateTime(date);
        Event e = new Event(taskName, dt.toString());
        tasks.add(e);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       [E][ ] " + e + " (at: " + dt + ")\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
        appendToFile("E | 0 | " + taskName + " | " + date + '\n');
    }

    public static void handleDelete() {
        int taskNum = Integer.parseInt(inputStringArr[1]);
        Task t = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: ");
        if (t.getType().equals("todo")) {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t);
        } else if (t.getType().equals("event")) {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + " (at: " + t.getDate() + ")");
        } else {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + " (by: " + t.getDate() + ")");
        }
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
        rewriteFile();
    }

    private static void appendToFile(String textToAppend) {
        try {
            FileWriter fw = new FileWriter("../data/duke.txt", true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }

    private static void rewriteFile() {
        try {
            FileWriter fw = new FileWriter("../data/duke.txt");
            String textToWrite = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                String type = t.getType();
                if (type.equals("todo")) {
                    String s = "T | ";
                    if (t.getStatus()) {
                        s += "1";
                    } else {
                        s += "0";
                    }
                    s += " | " + t + '\n';
                    textToWrite += s;
                } else if (type.equals("event")) {
                    String s = "E | ";
                    if (t.getStatus()) {
                        s += "1";
                    } else {
                        s += "0";
                    }
                    s += " | " + t + " | " + t.getDate() + '\n';
                    textToWrite += s;
                } else {
                    String s = "D | ";
                    if (t.getStatus()) {
                        s += "1";
                    } else {
                        s += "0";
                    }
                    s += " | " + t + " | " + t.getDate() + '\n';
                    textToWrite += s;
                }
            }
            fw.write(textToWrite);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        startDuke();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            userInput = sc.nextLine();
            inputStringArr = userInput.split(" ");
            String command = inputStringArr[0];
            if (command.equals("bye")) {
                handleBye();
            } else if (command.equals("list")) {
                handleList();
            } else if (command.equals("done")) {
                handleDone();
            } else if (command.equals("todo")) {
                handleTodo();
            } else if (command.equals("deadline")) {
                handleDeadline();
            } else if (command.equals("event")) {
                handleEvent();
            } else if (command.equals("delete")) {
                handleDelete();
            } else {
                handleBadCommand();
            }
        }
    }
}
