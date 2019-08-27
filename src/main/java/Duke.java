import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet user
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("This is your current list of tasks");
        // Get data from duke.txt
        ArrayList<Task> tasks = getDataFromFile();
        if (tasks.size() == 0) {
            System.out.println("You do not have any stored tasks");
        }
        else {
            for (int i = 0; i < tasks.size(); i++) {
                int currentItemNumber = i + 1;
                Task currentTask = tasks.get(i);
                System.out.println(currentItemNumber + "." + currentTask);
            }
        }
        // Ask initial user input
        String userinput = scanner.nextLine();

        while (!userinput.equals("bye")) {
            // List
            if (userinput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                // Output current items in list
                for (int i = 0; i < tasks.size(); i++) {
                    int currentItemNumber = i + 1;
                    Task currentTask = tasks.get(i);
                    System.out.println(currentItemNumber + "." + currentTask);
                }
            }
            else {
                String[] words = userinput.split(" ");

                // Done
                if (words[0].equals("done")) {
                    int itemId = Integer.parseInt(words[1]);
                    Task currentTask = tasks.get(itemId - 1);
                    currentTask.setDone(true);
                    System.out.println("Nice! I've marked this task as done:\n[1] " + currentTask.getName());
                }
                // Delete
                else if (words[0].equals("delete")) {
                    int itemId = Integer.parseInt(words[1]);
                    Task currentTask = tasks.get(itemId - 1);
                    tasks.remove(currentTask);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println(currentTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                // Add
                else {
                    try {
                        // to do
                        if (words[0].equals("todo")) {
                            // Remaining words
                            String remainingWords = userinput.replaceFirst("todo", "").trim();
                            if (remainingWords.equals("")) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }

                            // Add new task to list
                            Todo newTodo = new Todo(remainingWords, false);
                            tasks.add(newTodo);
                        }
                        // deadline
                        else if (words[0].equals("deadline")) {
                            // Remaining words
                            String remainingWords = userinput.replaceFirst("deadline ", "");
                            String[] remainingWords2 = remainingWords.split(" /by ", 2);

                            // Add new task to list
                            Deadline newDeadline = new Deadline(remainingWords2[0], false, remainingWords2[1]);
                            tasks.add(newDeadline);
                        }
                        // event
                        else if (words[0].equals("event")) {
                            // Remaining words
                            String remainingWords = userinput.replaceFirst("event ", "");
                            String[] remainingWords2 = remainingWords.split(" /at ", 2);

                            // Add new task to list
                            Event newEvent = new Event(remainingWords2[0], false, remainingWords2[1]);
                            tasks.add(newEvent);
                        }
                        // default
                        else {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }

                        // Print output of ADD
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                    catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            // Ask for next userinput again
            userinput = scanner.nextLine();
        }

        // At this point userinput equals "bye"
        // Save data to file
        saveDataToFile(tasks);
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static ArrayList<Task> getDataFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File("data/duke.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] words = currentLine.split("\\|");
                boolean isDone;
                if (words[1].equals("1")) {
                    isDone = true;
                }
                else {
                    isDone = false;
                }
                if (words[0].equals("T")) {
                    tasks.add(new Todo(words[2], isDone));
                }
                else if (words[0].equals("D")) {
                    tasks.add(new Deadline(words[2], isDone, words[3]));
                }
                else if (words[0].equals("E")) {
                    tasks.add(new Event(words[2], isDone, words[3]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file to get data from.");
        }
        return tasks;
    }
    private static void saveDataToFile(ArrayList<Task> tasks) {
        try {
            PrintWriter out = new PrintWriter(new File("data/duke.txt"));
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                if(currentTask instanceof Todo) {
                    out.print("T|");
                    if (currentTask.hasDone()) {
                        out.print("1|");
                    }
                    else {
                        out.print("0|");
                    }
                    out.println(currentTask.getName());
                }
                else if (currentTask instanceof Deadline) {
                    out.print("D|");
                    if (currentTask.hasDone()) {
                        out.print("1|");
                    }
                    else {
                        out.print("0|");
                    }
                    out.print(currentTask.getName());
                    out.print("|");
                    out.println(((Deadline) currentTask).getTime());
                }
                else if(currentTask instanceof Event) {
                    out.print("E|");
                    if (currentTask.hasDone()) {
                        out.print("1|");
                    }
                    else {
                        out.print("0|");
                    }
                    out.print(currentTask.getName());
                    out.print("|");
                    out.println(((Deadline) currentTask).getTime());
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file to save data to.");
        }
    }
}
