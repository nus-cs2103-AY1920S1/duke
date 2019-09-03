import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String INDENT_SPACING = "    ";
    private static final String ROOT_DIRECTORY = "C:/Users/gbrls/OneDrive/Desktop/duke-master/src/main/java";
    private static final String SAVE_DIRECTORY = "/data";
    private static final String SAVE_FILE_NAME = "duke.txt";

    public static void main(String[] args) throws DukeException{
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printGreetingMessage();

        loadTasksList();

        while(sc.hasNextLine()) {
            String newTaskLine = sc.nextLine();
            String[] newTaskSplit = newTaskLine.split(" ");
            String taskType = newTaskSplit[0];

            switch(taskType) {
            case "list":
                printList();
                break;
            case "bye":
                printExitMessage();
                saveTasks();
                return;
            case "done":
                printAndEvaluateTaskDone(newTaskSplit);
                break;
            case "event":
                parseAndEvaluateEvent(newTaskSplit);
                break;
            case "deadline":
                parseAndEvaluateDeadline(newTaskSplit);
                break;
            case "todo":
                parseAndEvaluateToDo(newTaskSplit);
                break;
            case "delete":
                parseAndEvaluateDelete(newTaskSplit);
                break;
            default:
                printException(new DukeIllegalArgumentException());
                break;
            }
        }
    }

    static DateTime convertDateTime(String dateTimeString) {
        String[] dateTimeStringSplit = dateTimeString.split(" ");
        String[] dateStringSplit = dateTimeStringSplit[0].split("/");
        int day = Integer.parseInt(dateStringSplit[0]);
        int month = Integer.parseInt(dateStringSplit[1]);
        int year = Integer.parseInt(dateStringSplit[2]);
        int time = Integer.parseInt(dateTimeStringSplit[1]);
        return new DateTime(day, month, year, time, dateTimeString);
    }

    static void saveTasks() {
        try {
            String saveDirectory = ROOT_DIRECTORY + SAVE_DIRECTORY + "/" + SAVE_FILE_NAME;
            new File(saveDirectory).delete();
            createNewSaveFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveDirectory));
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void createNewSaveFile() {
        try {
            File newSaveFile = new File(ROOT_DIRECTORY + SAVE_DIRECTORY + "/" + SAVE_FILE_NAME);
            newSaveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadTasksList() {
        File directory = new File(ROOT_DIRECTORY + SAVE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileReader tasksFileReader = new FileReader(new File(ROOT_DIRECTORY +
                                                                SAVE_DIRECTORY +
                                                                "/" + SAVE_FILE_NAME));
            BufferedReader bTasksFileReader = new BufferedReader(tasksFileReader);
            readTasksFile(bTasksFileReader);
            try {
                bTasksFileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            createNewSaveFile();
        } catch (DukeSaveFileCorruptedError e) {
            createNewSaveFile();
        }
    }

    static void readTasksFile(BufferedReader bTasksFileReader) throws DukeSaveFileCorruptedError {
        String newTaskString = null;
        while (true) {
            try {
                newTaskString = bTasksFileReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (newTaskString == null) {
                break;
            }
            String[] newTaskSplit = newTaskString.split(" \\| ");
            String taskType = newTaskSplit[0];
            boolean taskIsDone = Integer.parseInt(newTaskSplit[1]) == 1
                    ? true
                    : false;
            String description = newTaskSplit[2];
            Task newTask;
            switch(taskType) {
            case "T":
                newTask = new ToDo(description);
                break;
            case "D":
                DateTime deadlineTime = convertDateTime(newTaskSplit[3]);
                newTask = new Deadline(description, deadlineTime);
                break;
            case "E":
                DateTime eventTime = convertDateTime(newTaskSplit[3]);
                newTask = new Event(description, eventTime);
                break;
            default:
                try {
                    bTasksFileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throw new DukeSaveFileCorruptedError();
            }
            if (newTask != null) {
                if (taskIsDone) {
                    newTask.taskComplete();
                }
                tasks.add(newTask);
            } else  {
                throw new DukeSaveFileCorruptedError();
            }
        }
    }

    static void addBorder(String input) {
        String border = "____________________________________________________________";
        System.out.print(border + "\n" + input + "\n" + border + "\n\n");
    }

    static void printGreetingMessage() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }

    static void printAndEvaluateTaskDone(String[] newTaskSplit) {
        int completedTaskNum = Integer.parseInt(newTaskSplit[1]) - 1;
        Task completedTask = tasks.get(completedTaskNum);
        completedTask.taskComplete();
        addBorder("Nice! I've marked this task as done: \n" + completedTask.toString());
    }

    static void printExitMessage() {
        addBorder("Bye. Hope to see you again soon!");
    }

    static void printException(DukeException exception) {
        addBorder(exception.getMessage());
    }

    static void addTask(Task newTask) {
        tasks.add(newTask);
    }

    static void printAddTaskMessage(Task newTask) {
        String output = "Got it. I've added this task:\n"
                + newTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        addBorder(output);
    }

    static void parseAndEvaluateDelete(String[] newTaskSplit) {
        try {
            int newTaskLen = newTaskSplit.length;
            if (newTaskLen < 2) {
                throw new DukeDeleteIllegalArgumentException("You have not entered a number for deletion");
            } else if (newTaskLen > 2) {
                throw new DukeDeleteIllegalArgumentException("You have entered too many arguments for deletion");
            } else {
                try {
                    int deletionNum = Integer.parseInt(newTaskSplit[1]) - 1;
                    deleteTask(deletionNum);
                } catch (NumberFormatException e) {
                    throw new DukeDeleteIllegalArgumentException("Please enter a valid number for deletion");
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeDeleteIllegalArgumentException("Please enter a valid number within the range");
                }
            }
        } catch (DukeDeleteIllegalArgumentException e) {
            printException(e);
        }
    }

    static void deleteTask(int deletionNum) throws IndexOutOfBoundsException {
        Task deletedTask = tasks.remove(deletionNum);
        String output = "Noted. I've removed this task: \n"
                + deletedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        addBorder(output);
    }

    static void parseAndEvaluateToDo(String[] newTaskSplit) {
        try {
            int newTaskLen = newTaskSplit.length;
            String description = newTaskSplit[1];
            for (int i = 2; i < newTaskLen; i++) {
                description += " " + newTaskSplit[i];
            }
            ToDo newToDo = new ToDo(description);
            addTask(newToDo);
            printAddTaskMessage(newToDo);
        } catch (ArrayIndexOutOfBoundsException e) {
            printException(new DukeToDoIllegalArgumentException());
        }
    }

    static void parseAndEvaluateDeadline(String[] newTaskSplit) {
        try {
            int newTaskLen = newTaskSplit.length;
            boolean foundDeadline = false;
            String description = newTaskSplit[1];
            String deadlineTimeString = "";
            for (int i = 2; i < newTaskLen; i++) {
                if (foundDeadline) {
                    if (i == newTaskLen - 1) {
                        deadlineTimeString += newTaskSplit[i];
                    } else {
                        deadlineTimeString += newTaskSplit[i] + " ";
                    }
                } else {
                    if (newTaskSplit[i].equals("/by")) {
                        foundDeadline = true;
                    } else {
                        description += " " + newTaskSplit[i];
                    }
                }
            }
            if (foundDeadline) {
                DateTime deadlineTime = convertDateTime(deadlineTimeString);
                Deadline newDeadline = new Deadline(description, deadlineTime);
                addTask(newDeadline);
                printAddTaskMessage(newDeadline);
            } else {
                printException(new DukeDeadlineIllegalArgumentException("deadline"));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            printException(new DukeDeadlineIllegalArgumentException("description"));
        }
    }

    static void parseAndEvaluateEvent(String[] newTaskSplit) {
        int newTaskLen = newTaskSplit.length;
        try {
            boolean foundEvent = false;
            String description = newTaskSplit[1];
            String eventTimeString = "";
            for (int i = 2; i < newTaskLen; i++) {
                if (foundEvent) {
                    if (i == newTaskLen - 1) {
                        eventTimeString += newTaskSplit[i];
                    } else {
                        eventTimeString += newTaskSplit[i] + " ";
                    }
                } else {
                    if (newTaskSplit[i].equals("/at")) {
                        foundEvent = true;
                    } else {
                        description += " " + newTaskSplit[i];
                    }
                }
            }
            if (foundEvent) {
                DateTime eventTime = convertDateTime(eventTimeString);
                Event newEvent = new Event(description, eventTime);
                addTask(newEvent);
                printAddTaskMessage(newEvent);
            } else {
                printException(new DukeEventIllegalArgumentException("event timing"));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            printException(new DukeEventIllegalArgumentException("description"));
        }
    }

    static void printList() {
        String str = "";
        int numTasks = tasks.size();
        for (int i = 0; i < numTasks; i++) {
            String newTask = tasks.get(i).toString();
            if (i == numTasks - 1) {
                str += (i + 1) + ". " + newTask;
            } else {
                str += (i + 1) + ". " + newTask + "\n";
            }
        }
        addBorder(str);
    }

}