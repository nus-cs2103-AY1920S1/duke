import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Pattern;

public class Duke {
    public static void printAddedTask(Task task, int taskListSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.print("Now you have " + taskListSize);
        if (taskListSize == 1) System.out.println(" task in the list.");
        else System.out.println(" tasks in the list.");
    }

    private static void writeToFile(String filePath, LinkedList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String toWrite = "";
        for (int i = 0; i < tasks.size(); i++) {
            toWrite += tasks.get(i).toSave();
            if (i != tasks.size() - 1) toWrite += "\n";
        }
        fw.write(toWrite);
        fw.close();
    }

    public static void main(String[] args) throws DukeException {
        String fileName = "data/duke.txt";
        File file = new File(fileName);
        file.getParentFile().mkdirs();

        Scanner sc = new Scanner(System.in);
        LinkedList<Task> tasks = new LinkedList<>();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("Couldn't create file " + e.getMessage());
            }
        } else {
            try {
                Scanner readData = new Scanner(file);
                while (readData.hasNext()) {
                    String nextTask = readData.nextLine();
                    String[] details = nextTask.split(Pattern.quote(" | "));
                    String type = details[0];
                    Task task;

                    if (type.equals("T")) {
                        task = new Todo(details[2]);
                    } else if (type.equals("D")) {
                        task = new Deadline(details[2], details[3]);
                    } else {
                        task = new Event(details[2], details[3]);
                    }

                    if (details[1].equals("1")) task.markAsDone();
                    tasks.add(task);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File doesn't exist: " + e.getMessage());
            }
        }

        boolean hasChanged = true;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            try {
                String[] command = userInput.split(" ", 2);
                switch (command[0]) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        hasChanged = false;
                        break;
                    case "done":
                        int i = Integer.parseInt(command[1]) - 1;
                        if (i+1 > tasks.size() || i+1 < 0) throw new DukeException("\u2639 OOPS!!! This task does not exist.");
                        Task t = tasks.remove(i);
                        t.markAsDone();
                        tasks.add(i, t);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(t);
                        break;
                    case "delete":
                        int j = Integer.parseInt(command[1]) - 1;
                        if (j+1 > tasks.size() || j+1 < 0) throw new DukeException("\u2639 OOPS!!! This task does not exist.");
                        Task tsk = tasks.remove(j);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(tsk);
                        System.out.print("Now you have " + tasks.size());
                        if (tasks.size() == 1) System.out.println(" task in the list.");
                        else System.out.println(" tasks in the list.");
                        break;
                    case "todo":
                        if (command.length < 2)
                            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                        Task todo = new Todo(command[1]);
                        tasks.add(todo);
                        printAddedTask(todo, tasks.size());
                        break;
                    case "deadline":
                        if (command.length < 2)
                            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                        String[] detailsD = command[1].split(" /by ");
                        if (detailsD.length < 2)
                            throw new DukeException("\u2639 OOPS!!! Deadline missing date and time.");
                        Task deadline = new Deadline(detailsD[0], detailsD[1]);
                        tasks.add(deadline);
                        printAddedTask(deadline, tasks.size());
                        break;
                    case "event":
                        if (command.length < 2)
                            throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
                        String[] detailsE = command[1].split(" /at ");
                        if (detailsE.length < 2)
                            throw new DukeException("\u2639 OOPS!!! Event missing start and end time.");
                        Task event = new Event(detailsE[0], detailsE[1]);
                        tasks.add(event);
                        printAddedTask(event, tasks.size());
                        break;
                    default:
                        hasChanged = false;
                        throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (hasChanged) {
                    try {
                        writeToFile(file.getAbsolutePath(), tasks);
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }
                userInput = sc.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
