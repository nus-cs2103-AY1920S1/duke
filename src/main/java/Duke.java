import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {

    private static void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static ArrayList<Task> createTasks(File f) throws FileNotFoundException {
        ArrayList<Task> result = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String command = s.next();

                if (command.equals("todo")) {
                    String str = s.nextLine();
                    result.add(new ToDo(str));
                } else if (command.equals("deadline")) {
                    String str = s.nextLine();
                    String[] wordArr = str.split("/by", 2);
                    result.add(new DeadLine(wordArr[0], wordArr[1]));
                } else if (command.equals("event")) {
                    String str = s.nextLine();
                    String[] wordArr = str.split("/at", 2);
                    result.add(new DeadLine(wordArr[0], wordArr[1]));
                } else if (command.equals("delete")) {
                    int taskNum = s.nextInt();
                    result.remove(taskNum);
                } else {
                    int taskNum = s.nextInt();
                    result.get(taskNum).setAsDone();
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! The file has not been found!");
        }

        return result;
    }

    private static void printTasks(ArrayList<Task> t) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\n\tHere are the tasks in your list: ");
        for (int i = 0; i < t.size(); i++) {
            System.out.println("\n\t" + (i + 1) + ". " + t.get(i).toString());
        }
        System.out.println("\t____________________________________________________________\n");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        // Create a .txt file to store data
        File f = new File("data/taskList.txt");

        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Welcome message
        System.out.println("\t____________________________________________________________");
        System.out.println("\n\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");

        // Create taskList from data file
        try {
            taskList = createTasks(f);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        printTasks(taskList);

        // Echo commands until bye is entered

        while (true) {

            String command = sc.next();

            try {
                if (command.equals("list")) {

                    printTasks(taskList);

                } else if (command.equals("done")) {
                    int taskNum = Integer.valueOf(sc.next());
                    if (taskNum < 0 || taskNum >= taskList.size()) {
                        throw new DukeException("OOPS! Integer is out of range of list.");
                    }
                    taskList.get(taskNum).setAsDone();
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tNice! I have marked this task as done: ");
                    System.out.println("\n\t" + taskList.get(taskNum));
                    System.out.println("\t____________________________________________________________\n");

                    // Save the command in taskList.txt
                    String text = "done " + taskNum + "\n";
                    appendToFile(f.getAbsolutePath(), text);

                } else if (command.equals("bye")) {

                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tBye. Hope to see you again soon!");
                    System.out.println("\t____________________________________________________________\n");
                    break;

                } else if (command.equals("todo")) {

                    String str = sc.nextLine();

                    if (str.equals("")) {
                        throw new DukeException("OOPS! The description for todo should not be empty.");
                    }

                    // Create ToDo object
                    ToDo toDoTask = new ToDo(str);
                    taskList.add(toDoTask);

                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tGot it! I've added this task: ");
                    System.out.println("\n\t" + toDoTask.toString());
                    System.out.println("\n\tNow you have " + taskList.size() + " tasks in the list.");
                    System.out.println("\t____________________________________________________________\n");

                    // Save the command in taskList.txt
                    String text = "todo " + str + "\n";
                    appendToFile(f.getAbsolutePath(), text);

                } else if (command.equals("deadline")) {

                    String str = sc.nextLine();
                    String[] wordArr = str.split("/by", 2);

                    if (wordArr.length == 1) {
                        throw new DukeException("OOPS! Deadlines should be followed by a /by.");
                    }

                    // Create DeadLine object
                    DeadLine d = new DeadLine(wordArr[0], wordArr[1]);
                    taskList.add(d);
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tGot it! I've added this task: ");
                    System.out.println("\n\t" + d.toString());
                    System.out.println("\n\tNow you have " + taskList.size() + " tasks in the list.");
                    System.out.println("\t____________________________________________________________\n");

                    // Save the command in taskList.txt
                    String text = "deadline " + wordArr[0] + " /by " + wordArr[1] + "\n";
                    appendToFile(f.getAbsolutePath(), text);

                } else if (command.equals("event")) {

                    String str = sc.nextLine();
                    String[] wordArr = str.split("/at", 2);

                    if (wordArr.length == 1) {
                        throw new DukeException("OOPS! Events should be followed by a /at.");
                    }

                    // Create Event Object
                    Event e = new Event(wordArr[0], wordArr[1]);
                    taskList.add(e);
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tGot it! I've added this task: ");
                    System.out.println("\n\t" + e.toString());
                    System.out.println("\n\tNow you have " + taskList.size() + " tasks in the list.");
                    System.out.println("\t____________________________________________________________\n");

                    // Save the command in taskList.txt
                    String text = "event " + wordArr[0] + " /at " + wordArr[1] + "\n";
                    appendToFile(f.getAbsolutePath(), text);

                } else if (command.equals("delete")) {

                    int taskNum = Integer.valueOf(sc.next());
                    if (taskNum < 0 || taskNum >= taskList.size()) {
                        throw new DukeException("OOPS! Integer is out of range of list.");
                    }
                    Task removed = taskList.get(taskNum);
                    taskList.remove(taskNum);
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tNoted. I have removed this task: ");
                    System.out.println("\n\t" + removed);
                    System.out.println("\n\tNow you have " + taskList.size() + " tasks in the list.");
                    System.out.println("\t____________________________________________________________\n");

                    // Save the command in taskList.txt
                    String text = "delete " + taskNum + "\n";
                    appendToFile(f.getAbsolutePath(), text);

                } else {
                    throw new DukeException("OOPS! I'm sorry, I don't know what that means! :(");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\tOOPS! An integer is expected after done / delete.");
                System.out.println("\t____________________________________________________________\n");
            } catch (DukeException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\t" + e.getMessage());
                System.out.println("\t____________________________________________________________\n");
            }
        }

        sc.close();

    }
}