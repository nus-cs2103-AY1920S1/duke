import java.io.FileWriter;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    List<Task> toDoList;
    final String doneMessage = "Nice! I've marked this task as done:";
    final String addedMessage = "Got it. I've added this task:";
    final String deleteMessage = "Noted. I've removed this task: ";
    final String exitMessage = "Bye. Hope to see you again soon!";
    final String emptyToDoErrorMessage = "____________________________________________________________\n"
            + "☹ OOPS!!! The description of a todo cannot be empty.\n"
            + "____________________________________________________________";
    final String illegalArgumentMessage = "____________________________________________________________\n"
        + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
        + "____________________________________________________________";

    /**
     * Lists items in To Do List.
     * @return List as a string
     */
    public String list() {
        StringJoiner result = new StringJoiner("\n");
        for (int i = 0; i < toDoList.size(); i++) {
            Task t = toDoList.get(i);
            result.add(String.format("%d.%s", i + 1, t));
        }
        return padMessage(result.toString());
    }

    /**
     * Set index of To Do List as done. (one based numbering)
     * @param i Index
     * @return Done message as a string
     */
    public String done(int i) {
        toDoList.get(i - 1).setDone(true);
        StringJoiner result = new StringJoiner("\n");
        result.add(doneMessage);
        result.add(toDoList.get(i - 1).toString());
        return padMessage(result.toString());
    }

    /**
     * Delete index of To Do List. (one based numbering)
     * @param i Index
     */
    public void delete(int i) {
        System.out.println("____________________________________________________________");
        System.out.println(deleteMessage);
        System.out.println(toDoList.get(i - 1));
        System.out.println(String.format("Now you have %d tasks in the list.", toDoList.size()));
        System.out.println("____________________________________________________________");
    }

    /**
     * Appends task to To Do List. (one based numbering)
     * @param task Task description
     */
    public void addToDo(String task) {
        toDoList.add(new ToDo(task));
        this.addedMessage();
    }

    /**
     * Appends deadline to To Do List. (one based numbering)
     * @param task Task description
     * @param date Deadline in date format
     */
    public void addDeadline(String task, Date date) {
        toDoList.add(new Deadline(task, date));
        this.addedMessage();
    }

    /**
     * Appends event to To Do List. (one based numbering)
     * @param task Task description
     * @param date Date
     */
    public void addEvent(String task, Date date) {
        toDoList.add(new Event(task, date));
        this.addedMessage();
    }

    /**
     * Saves tasks printed in list() in specified path.
     * @param filePath Path wherein text will be saved
     * @param textToAdd Text to add
     */
    public void save(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private String addedMessage() {
        StringJoiner result = new StringJoiner("\n");
        result.add(addedMessage);
        result.add(toDoList.get(toDoList.size() - 1).toString());
        result.add(String.format("Now you have %d tasks in the list.", toDoList.size()));
        return padMessage(result.toString());
    }

    private String padMessage(String message) {
        StringJoiner result = new StringJoiner("\n");
        result.add("____________________________________________________________");
        result.add(message);
        result.add("____________________________________________________________");
        return result.toString();
    }

    /**
     * Driver method.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String[] arr;
        String task, date;
        SimpleDateFormat readFormat;
        boolean exit = false;
        toDoList = new ArrayList<>();
        while (!exit) {
            try {
                String command = sc.next();
                switch (command) {
                case "todo":
                    task = sc.nextLine().trim();
                    if (!task.isEmpty()) {
                        this.addToDo(sc.nextLine().trim());
                    } else {
                        throw new IllegalArgumentException(emptyToDoErrorMessage);
                    }
                    break;
                case "deadline":
                    arr = sc.nextLine().split("/by");
                    task = arr[0].trim();
                    date = arr[1].trim();
                    readFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                    this.addDeadline(task, readFormat.parse(date));
                    break;
                case "event":
                    arr = sc.nextLine().split("/at");
                    task = arr[0].trim();
                    date = arr[1].trim();
                    readFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                    this.addEvent(task, readFormat.parse(date));
                    break;
                case "list":
                    System.out.println(this.list());
                    break;
                case "done":
                    System.out.println(this.done(sc.nextInt()));
                    break;
                case "delete":
                    this.delete(sc.nextInt());
                    break;
                case "save":
                    this.save("./Data/duke.txt", this.list());
                    break;
                case "bye":
                    exit = true;
                    break;
                default:
                    throw new IllegalArgumentException(illegalArgumentMessage);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println(exitMessage);
        System.out.println("____________________________________________________________");
        sc.close();
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Duke d = new Duke();
        d.run();
    }
}
