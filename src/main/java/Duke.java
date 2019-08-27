import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.PrintWriter;


public class Duke {

    private ArrayList<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<Task>();
        initDuke();
    }

    /**
     * Creates the Duke Logo.
     * @params args String[]
     */
    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.loadData();
        String cmd = "";
        Scanner sc = new Scanner(System.in);

        cmd = sc.nextLine();

        while (!cmd.equals("bye")) {
            String[] cmdList = cmd.split(" ");
            String keyword = cmdList[0];
            printLine();

            try {

                if (keyword.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= duke.tasks.size(); i++) {
                        System.out.println(i + "." + duke.tasks.get(i - 1));
                    }

                } else if (keyword.equalsIgnoreCase("done")) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.print("  ");
                    Task taskToMarkAsDone = duke.tasks.get(Integer.parseInt(cmdList[1]) - 1);
                    taskToMarkAsDone.markAsDone();
                    System.out.println(taskToMarkAsDone);

                } else if (keyword.equalsIgnoreCase("delete")) {
                    Task taskToBeRemoved = duke.tasks.get(Integer.parseInt(cmdList[1]) - 1);
                    duke.tasks.remove(taskToBeRemoved);
                    System.out.println("Noted. I've removed this task:");
                    System.out.print("  ");
                    System.out.println(taskToBeRemoved);
                    System.out.println("Now you have " + duke.tasks.size() + " tasks in the list.");

                } else { // it is a new Task
                    Task newTaskToBeAdded = duke.handleNewTask(keyword, cmd);
                    duke.tasks.add(newTaskToBeAdded);
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  ");
                    System.out.println(newTaskToBeAdded);
                    System.out.println("Now you have " + duke.tasks.size() + " tasks in the list.");
                }

            } catch (InputMismatchException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
            printLine();
            cmd = sc.nextLine();
        }
        duke.saveData();
        closeDuke();
    }

    private void saveData() {
        File file = new File("./data/duke.txt");
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            PrintWriter printer = new PrintWriter(writer);
            for (Task t : this.tasks) {
                printer.append(t.toDataFormat() + "\n");
            }
            printer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void loadData() {
        try {
            File dataFile = createOrRetrieve("./data/duke.txt");
            ArrayList<String> stringOfTasks = readFile(dataFile);
            String keyword = "";
            String bool = "";
            String description = "";
            String date = "";
            Task t;
            for (String s : stringOfTasks) {
                String[] cmdList = s.split("\\|");
                keyword = cmdList[0].trim();
                bool = cmdList[1].trim();
                description = cmdList[2].trim();
                if (cmdList.length > 3) {
                    date = cmdList[3];
                }
                if (keyword.equalsIgnoreCase("D")) {
                    t = new Deadline(description, date);
                    t.setIsDone(bool);
                } else if (keyword.equalsIgnoreCase("E")) {
                    t = new Event(description, date);
                    t.setIsDone(bool);
                } else { //(keyword.equalsIgnoreCase("T")) {
                    t = new Todo(description);
                    t.setIsDone(bool);
                }
                this.tasks.add(t);
            }
        } catch (IOException e) {
            return;
        }
    }

    private static File createOrRetrieve(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
        }
        return file;
    }


    private static ArrayList<String> readFile(File file) {
        ArrayList<String> stringOfTasks = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                stringOfTasks.add(line);
            }
            reader.close();
            return stringOfTasks;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", file);
            e.printStackTrace();
            return null;
        }
    }

    private static void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    private static void initDuke() {
        printLine();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void closeDuke() {

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private Task handleNewTask(String keyword, String cmd) throws InputMismatchException {
        if (keyword.equalsIgnoreCase("deadline")) {
            String descriptionAndTime = cmd.substring(8);
            String[] details = descriptionAndTime.trim().split(" /by");
            if (descriptionAndTime.isEmpty() || details.length <= 1) {
                throw new InputMismatchException("The description of a deadline cannot be empty.");
            }
            return new Deadline(details[0], details[1]);

        } else if (keyword.equalsIgnoreCase("event")) {
            String descriptionAndTime = cmd.substring(5);
            String[] details = descriptionAndTime.trim().split(" /at");
            if (descriptionAndTime.isEmpty() || details.length <= 1) {
                throw new InputMismatchException("The description of an event cannot be empty.");
            }
            return new Event(details[0], details[1]);

        } else if (keyword.equalsIgnoreCase("todo")) {
            String description = cmd.substring(4).trim(); //words after todo
            if (description.isEmpty()) {
                throw new InputMismatchException("The description of a todo cannot be empty.");
            }
            return new Todo(description);
        } else {
            throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
