import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = initializeTaskList();
        while (true) {
            try {
                String input = sc.nextLine();
                String[] splitString = input.split(" ");
                String taskType = splitString[0];
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i).toString());
                    }
                } else if (splitString[0].equals("done")) {
                    int taskNum = Integer.valueOf(splitString[1]) - 1;
                    tasks.get(taskNum).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    [" + tasks.get(taskNum).getStatusIcon() + "] " +
                            tasks.get(taskNum).getDescription());
                } else if (input.equals("bye")) {
                    System.out.println("Bye! Hope to see you again!");
                    return;
                } else if (taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event")){
                    addNewTask(taskType, input, tasks);
                } else if (taskType.equals("delete")) {
                    int index = Integer.valueOf(splitString[1]) - 1;
                    deleteTask(index, tasks);
                } else {
                    throw new InvalidCommandException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static ArrayList<Task> initializeTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String content = Files.readString(FileSystems.getDefault().getPath("data", "duke.txt"));
            String[] storedTasks = content.split("\n");
            // Adding tasks stored in the text file
            for (String s: storedTasks) {
                if (s.charAt(0) == 'T') {
                    Todo todo = new Todo(s.substring(8));
                    if (s.charAt(4) != '0') {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                } else if (s.charAt(0) == 'E') {
                    String[] tempSplit = s.substring(8).split("\\u007C ");
                    Event event = new Event(tempSplit[0].trim(), tempSplit[1].trim());
                    if (s.charAt(4) != '0') {
                        event.markAsDone();
                    }
                    tasks.add(event);
                } else if (s.charAt(0) == 'D') {
                    String[] tempSplit = s.substring(8).split("\\u007C ");
                    Deadline deadline = new Deadline(tempSplit[0].trim(), tempSplit[1].trim());
                    if (s.charAt(4) != '0') {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return tasks;
        }
    }

    static Task addNewTask(String taskType, String input, ArrayList<Task> tasks) throws DukeException {
        Task t;
        switch (taskType) {
            case "todo":
                String[] todoSplit = input.split("todo ");
                if (todoSplit.length == 1 || todoSplit[1].isEmpty()) {
                    throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                }
                Todo todo = new Todo(todoSplit[1].trim());
                tasks.add(todo);
                System.out.println("Got it. I've added this task:\n  " + todo);
                t = todo;
                break;
            case "deadline": {
                String[] deadlineSplit = input.split("/by ");
                String[] temp = deadlineSplit[0].split("deadline ");
                if (temp.length == 1 || temp[1].isEmpty()) {
                    throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                }
                Deadline deadline = new Deadline(temp[1].trim(), deadlineSplit[1].trim());
                tasks.add(deadline);
                System.out.println("Got it. I've added this task:\n " + deadline);
                t = deadline;
                break;
            }
            case "event": {
                String[] deadlineSplit = input.split("/at ");
                String[] temp = deadlineSplit[0].split("event ");
                if (temp.length == 1 || temp[1].isEmpty()) {
                    throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a event cannot be empty.");
                }
                Event event = new Event(temp[1].trim(), deadlineSplit[1].trim());
                tasks.add(event);
                System.out.println("Got it. I've added this task:\n " + event);
                t = event;
                break;
            }
            default: {
                t = null;
                System.out.println("If you see this something is wrong >:D");
            }
        }
        if (tasks.size() == 1) {
            System.out.println("Now you have " + tasks.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
        updateFile(tasks);
        return t;
    }

    static void deleteTask(int index, ArrayList<Task> tasks) {
        Task removed = tasks.remove(index);
        System.out.println("Noted. I've removed this task:\n  " + removed);
        if (tasks.size() == 1) {
            System.out.println("Now you have " + tasks.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
        updateFile(tasks);
    }

    static void updateFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(new File("data/duke.txt"));
            for (int i = 0; i < tasks.size(); i++) {
                String type = tasks.get(i) instanceof Event ? "E"
                        : tasks.get(i) instanceof Todo ? "T"
                        : tasks.get(i) instanceof Deadline ? "D"
                        : "INVALID CLASS";
                String isDone = tasks.get(i).isDone() ? "1" : "0";
                if (tasks.get(i) instanceof Todo) {
                    fw.append(type + " | " + isDone + " | " + tasks.get(i).getDescription());

                } else if (tasks.get(i) instanceof Event) {
                    fw.append(type + " | " + isDone + " | " + tasks.get(i).getDescription() + " | "
                            + ((Event) tasks.get(i)).getTimeFrame());
                } else if (tasks.get(i) instanceof  Deadline) {
                    fw.append(type + " | " + isDone + " | " + tasks.get(i).getDescription() + " | "
                            + ((Deadline) tasks.get(i)).getDeadline());
                }
                if (i != tasks.size() - 1) {
                    fw.append("\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}