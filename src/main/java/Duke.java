import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;
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

        while (check.equals("bye") == false) {
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

                 String[] dueSplit = input.split("/", 2);
                 String due = "dummy";
                 if (dueSplit.length > 1) {
                     due = dueSplit[1].split(" ", 2)[1];
                 }
                 String[] doneMarkers = dueSplit[0].split(" ", 2);
                 String userCommand = doneMarkers[0].toLowerCase();
                 String taskDescription = "dummy";
                 if (doneMarkers.length > 1) {
                     taskDescription = doneMarkers[1];
                 }

                 if (userCommand.equals("done")) {
                     int target = Integer.valueOf(taskDescription);
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
                     int target = Integer.valueOf(taskDescription);
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
                     String typeOfTask = "";
                     if (userCommand.equals("todo")) {
                         if (taskDescription.equals("dummy")) {
                             throw new EmptyToDoDescriptionException("The description of a todo cannot be empty.");
                         }
                         t = new Todo(taskDescription);
                         typeOfTask = "T";
                         taskStorage.add(t);
                         writeToFile(filePath, typeOfTask, "0", taskDescription, t);
                     } else if (userCommand.equals("deadline")) {
                         Date dateDue = convertStringToDate(due);
                         if (taskDescription.equals("dummy")) {
                             throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                         }
                         t = new Deadline(taskDescription, dateDue);
                         taskStorage.add(t);
                         typeOfTask = "D";
                         writeToFile(filePath, typeOfTask, "0", taskDescription, t);
                     } else if (userCommand.equals("event")) {
                         String[] eventStartEnd = due.split("-", 2);
                         Date start = convertStringToDate(eventStartEnd[0]);
                         Date end = convertStringToDate(eventStartEnd[1]);

                         if (taskDescription.equals("dummy")) {
                             throw new EmptyDescriptionException("The description of a event cannot be empty.");
                         }
                         t = new Event(taskDescription, start, end);
                         typeOfTask = "E";
                         taskStorage.add(t);
                         writeToFile(filePath, typeOfTask, "0", taskDescription, t);
                     } else {
                         throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                     }

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
                Date due = new Date();
                Date start = new Date();
                Date end = new Date();
                if (analyseHolder.length == 4) {
                    String[] timeSplit = analyseHolder[3].split("-");
                    if (timeSplit.length == 2) {
                        SimpleDateFormat startFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        SimpleDateFormat endFormat = new SimpleDateFormat("HHmm");
                        start = startFormat.parse(timeSplit[0].trim());
                        end = endFormat.parse(timeSplit[1].trim());
                    } else {
                        due = convertStringToDate(analyseHolder[3]);
                    }
                }

                Task task = new Task("dummy");
                Task.reduceTaskCount();
                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D")) {
                    task = new Deadline(description, due);
                } else if (type.equals("E")) {
                    task = new Event(description, start, end);
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
        } catch (ParseException e) {
            e.printStackTrace();
//            System.out.println("There seems to be an error in the format of the date.");
        }
        return taskStorage;
    }

    /**
     * Write to a specified text file that is locally saved.
     * @param filePath The file path to the local text file
     * @param taskType Contains the type of the task that is going to be written.
     * @param isDone Contains the information of whether the task is done.
     * @param description Contains the description of the task that is going to be written.
     */
    private static void writeToFile(String filePath, String taskType,
                                    String isDone, String description, Task task) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String textToAdd = new String();
            if (task instanceof Todo) {
                textToAdd = taskType + ", " + isDone + ", " + description + System.lineSeparator();
            } else if (task instanceof Deadline){
                textToAdd = taskType + ", " + isDone + ", "
                        + description + ", " + ((Deadline) task).getDueInString() + System.lineSeparator();
            } else if (task instanceof Event) {
                textToAdd = taskType + ", " + isDone + ", "
                        + description + ", " + ((Event) task).getDueInString() + System.lineSeparator();
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
            String textToAdd = new String();
            String taskType;
            String description;
            Boolean isDone;
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
                } else if (taskType.equals("D")) {
                    textToAdd = taskType + ", " + isDoneRepresented + ", "
                            + description + ", " + ((Deadline) task).getDueInString() + System.lineSeparator();
                } else if (taskType.equals("E")) {
                    textToAdd = taskType + ", " + isDoneRepresented + ", "
                            + description + ", " + ((Event) task).getDueInString() + System.lineSeparator();
                }
                fw.write(textToAdd);
            }

            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private static Date convertStringToDate(String input) {
        Date date = new Date();
            try {
                if (input.length() <= 5) {
                    SimpleDateFormat formatTimeOnly = new SimpleDateFormat("HHmm");
                    date = formatTimeOnly.parse(input.trim());
                    return date;
                } else {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm", Locale.ENGLISH);
                    date = format.parse(input);
                    return date;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
    }
}
