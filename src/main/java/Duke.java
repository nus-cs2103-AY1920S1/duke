import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;


/**
 * Entry point of this project Duke. Duke is a Task manager that aims
 * to serve as an efficient way to manage our day to day tasks. It supports multiple types of task
 * such as todo reminders, tasks with a deadline and even an event.
 * The Duke task manager has many iterations, it is
 * continuously evolving and becoming smarter to cater to it users' needs.
 *
 * @author TuanDingWei
 */
public class Duke {
    public static void main(String[] args) {
        run();
    }

    /**
     * Contains most of the operations of the Task bot.
     */
    private static void run() {
        ArrayList<Task> taskStorage = new ArrayList<>();
        String filePath = "/Users/TuanDingWei/Desktop/NUS_Academia/CS2103/Individual_project/Duke/local/Tasks.txt";
        taskStorage = loadExistingData(filePath, taskStorage);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input;
        String check = "dummy";
        int taskCount;

        while (!check.equals("bye")) {
            try {
                input = sc.nextLine();
                check = input.toLowerCase();
                if (check.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= taskStorage.size(); i++) {
                        Task evaluatingTask = taskStorage.get(i - 1);
                        System.out.println(i + "." + evaluatingTask.toString());
                    }
                    System.out.println();
                    continue;
                }

                String[] dueSplit = input.split("/");
                String due = "dummy";
                if (dueSplit.length > 1) {
                    due = dueSplit[1];
                }
                String[] doneMarkers = dueSplit[0].split(" ", 2);
                String userCommand = doneMarkers[0].toLowerCase();
                String taskDescription = "dummy";
                if (doneMarkers.length > 1) {
                    taskDescription = doneMarkers[1];
                }

                if (userCommand.equals("done")) {
                    int target = Integer.parseInt(taskDescription);
                    Task taskDone;
                    if (taskStorage.size() >= target && target > 0) {
                        taskDone = taskStorage.get(target - 1);
                    } else {
                        throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                    }
                    taskDone.markAsDone();
                    updateLocalFile(filePath, taskStorage);
                    System.out.println("Nice! I've marked this task as done: " + "\n"
                            + "    " + taskDone + "\n");
                } else if (userCommand.equals("delete")) {
                    int target = Integer.parseInt(taskDescription);
                    Task taskDelete;
                    if (taskStorage.size() >= target && target > 0) {
                        taskDelete = taskStorage.get((target - 1));
                        removeTask(taskStorage, (target - 1));
                    } else {
                        throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                    }
                    Task.reduceTaskCount();
                    updateLocalFile(filePath, taskStorage);
                    taskCount = Task.getTaskCount();
                    System.out.println("Noted. I've removed this task:" + "\n"
                            + "    " + taskDelete);
                    System.out.println(taskCounter(taskCount) + "\n");
                } else if (!check.equals("bye")) {
                    Task t;
                    String typeOfTask;
                    if (userCommand.equals("todo")) {
                        if (taskDescription.equals("dummy")) {
                            throw new EmptyToDoDescriptionException("The description of a todo cannot be empty.");
                        }
                        t = new Todo(taskDescription);
                        typeOfTask = "T";
                        taskStorage.add(t);
                    } else if (userCommand.equals("deadline")) {
                        if (taskDescription.equals("dummy")) {
                            throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                        } else if (due.equals("dummy")) {
                            throw new EmptyDueDateException("The due date and time of this deadline is not specified.");
                        }
                        t = new Deadline(taskDescription, due);
                        typeOfTask = "D";
                        taskStorage.add(t);
                    } else if (userCommand.equals("event")) {
                        if (taskDescription.equals("dummy")) {
                            throw new EmptyDescriptionException("The description of a event cannot be empty.");
                        } else if (due.equals("dummy")) {
                            throw new EmptyDueDateException("The due date and time of this task are not specified.");
                        }
                        t = new Event(taskDescription, due);
                        typeOfTask = "E";
                        taskStorage.add(t);
                    } else {
                        throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                    }

                    writeToFile(filePath, typeOfTask, "0", taskDescription, due);
                    taskCount = Task.getTaskCount();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + t);
                    System.out.println(taskCounter(taskCount) + "\n");
                }
            } catch (DukeException ex) {
                System.out.println("OOPS!!! " + ex.getMessage() + "\n");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the task count.
     * @param taskCount The total number of Task that exist in the list.
     * @return An message that tells the user the total number of tasks are in the list.
     */
    private static String taskCounter(int taskCount) {
        if (taskCount > 1) {
            return ("Now you have " + taskCount + " tasks in the list.");
        } else {
            return ("Now you have " + taskCount + " task in the list.");
        }
    }

    /**
     * Removes task from the list.
     * @param taskStorage A list that stores all the existing tasks.
     *                    It is in the format of an ArrayList.
     * @param index The index of task that the user wishes to delete.
     */
    private static void removeTask(ArrayList taskStorage, int index) {
        taskStorage.remove(index);
    }

    /**
     *Performs the loading of existing tasks that are stored in a local text file.
     * @param filePath Contains the path directory to the local text file.
     * @param taskStorage the ArrayList that will be returned.
     * @return an ArrayList of all existing tasks.
     */
    private static ArrayList<Task> loadExistingData(String filePath, ArrayList<Task> taskStorage) {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String storedLine = s.nextLine();
                String[] analyseHolder = storedLine.split(", ", 4);
                String type = analyseHolder[0];
                String isDone = analyseHolder[1];
                String description = analyseHolder[2];
                String due = "dummy";
                if (analyseHolder.length == 4) {
                    due = analyseHolder[3];
                }

                Task task = new Task("dummy");
                Task.reduceTaskCount();
                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D")) {
                    task = new Deadline(description, due);
                } else if (type.equals("E")) {
                    task = new Event(description, due);
                }

                if (isDone.equals("1")) {
                    task.markAsDone();
                }

                taskStorage.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The local file cannot be located. " +
                    "Please ensure that the local text file " +
                    "that stores all existing tasks is in the right folder. ");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is something wrong with the format of the file text. " +
                    "There might be necessary lines between tasks.");
        }
        return taskStorage;
    }

    /**
     * Write to a specified text file that is locally saved.
     * @param filePath The file path to the local text file
     * @param taskType Contains the type of the task that is going to be written.
     * @param isDone Contains the information of whether the task is done.
     * @param description Contains the description of the task that is going to be written.
     * @param due Contains the due date or duration of an event.
     */
    private static void writeToFile(String filePath, String taskType,
                                    String isDone, String description, String due) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String textToAdd;
            if (due.equals("dummy")) {
                textToAdd = taskType + ", " + isDone + ", " + description + System.lineSeparator();
            } else {
                textToAdd = taskType + ", " + isDone + ", "
                        + description + ", " + due + System.lineSeparator();
            }

            fw.write(textToAdd);
            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void updateLocalFile(String filePath, ArrayList<Task> taskStorage) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String textToAdd;
            String taskType;
            String description;
            Boolean isDone;
            String due;
            String isDoneRepresented = "0";
            for (Task task : taskStorage) {
                taskType = task.getTypeOfTask();
                description = task.getDescription();
                isDone = task.isDone();
                if (isDone) {
                    isDoneRepresented = "1";
                }
                if (taskType.equals("T")) {
                    textToAdd = taskType + ", " + isDoneRepresented + ", " + description + System.lineSeparator();
                } else {
                    due = task.getDue();
                    textToAdd = taskType + ", " + isDoneRepresented + ", "
                            + description + ", " + due + System.lineSeparator();
                }
                fw.write(textToAdd);
            }

            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
