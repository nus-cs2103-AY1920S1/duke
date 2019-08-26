import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;

public class Duke {
    public static final String LINE = "    ____________________________________________________________";
    public static final String TABS = "     ";

    public static final String FILE_NAME = "/Users/chowjiaying/Github/2103T-iP/duke/data/duke.txt";
    static ArrayList<Task> taskList = new ArrayList<Task>();
    private Storage storage;




    public static void welcomeMessage() {
        System.out.println(LINE);
        System.out.println(TABS + "Hello! I'm Duke\n"+ TABS +"What can I do for you?");
        System.out.println(LINE);
    }

    private static void displayNumberOfTasks() {
        if (taskList.size() == 1) {
            System.out.printf("%sNow you have %d task in the list.\n", TABS, taskList.size());
        } else {
            System.out.printf("%sNow you have %d tasks in the list.\n", TABS, taskList.size());
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_NAME);
    }

    public Duke(String filePath) {
        welcomeMessage();
        storage = new Storage(filePath);
        storage.load();

        Scanner sc = new Scanner(System.in); //gets commands from user
        String userCommand = sc.nextLine();

        //while loop that takes in user inputs
        while (true) {
            try {
                System.out.println(LINE);
                //program runs this command when userCommand is bye
                if (userCommand.equals("bye")) {
                    System.out.println(TABS + "Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    break;
                } else if (userCommand.equals("list")) {
                    System.out.println(TABS + "Here are the tasks in your list:");
                    //program displays all Tasks in taskList
                    String displayedMessage = "";
                    Integer index = 1;
                    for (Task taskItem : taskList) {
                        displayedMessage += TABS + index.toString() + "." + taskItem + "\n";
                        index++;
                    }
                    System.out.print(displayedMessage);
                } else if (userCommand.startsWith("done")) {
                    //program marks a Task as done
                    System.out.println(TABS + "Nice! I've marked this task as done: ");
                    int taskNumber = Character.getNumericValue(userCommand.charAt(5));
                    Task taskCompleted = taskList.get(taskNumber - 1);
                    taskCompleted.markAsDone();
                    System.out.println(TABS + "  " + taskCompleted.toString());
                    writeTaskToFile(FILE_NAME);
                } else if (userCommand.startsWith("delete")){
                    //program deletes a Task from taskList
                    int taskNumber = Character.getNumericValue(userCommand.charAt(7));
                    Task taskToDelete = taskList.remove(taskNumber - 1);
                    System.out.println(TABS + "Noted. I've removed this task: ");
                    System.out.println(TABS + "  " + taskToDelete.toString());
                    displayNumberOfTasks();
                    writeTaskToFile(FILE_NAME);
                } else {
                    //program checks what type of task it is
                    Task userTask;
                    if (userCommand.startsWith("todo")) {
                        String task = userCommand.replaceFirst("todo", "");
                        if (task.isEmpty()) {
                            throw new EmptyToDoDescriptionException("The description of a todo cannot be empty. ");
                        } else {
                            //removes space in the beginning
                            task = task.replaceFirst(" ", "");
                            userTask = new ToDo(task);
                        }
                    } else if (userCommand.startsWith("deadline")) {
                        String task = userCommand.replaceFirst("deadline ", "");
                        String[] taskInformation = task.split(" /by ");
                        userTask = new Deadline(taskInformation[0], taskInformation[1]);
                    } else if (userCommand.startsWith("event")) {
                        String task = userCommand.replaceFirst("event ", "");
                        String[] taskInformation = task.split(" /at ");
                        userTask = new Event(taskInformation[0], taskInformation[1]);
                    } else {
                        throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                    }
                    taskList.add(userTask);
                    writeTaskToFile(FILE_NAME);
                    System.out.println(TABS + "Got it. I've added this task: ");
                    System.out.println(TABS + "  " + userTask.toString());

                    displayNumberOfTasks();
                }
            } catch (DukeException ex) {
                System.out.println(TABS + "☹ OOPS!!! " + ex.getMessage());
            }

            System.out.println(LINE);
            userCommand = sc.nextLine();
        }


    }
}
