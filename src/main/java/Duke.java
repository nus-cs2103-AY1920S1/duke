import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    //enum TaskType { todo, deadline, event }//Unnecessary and prevents reusability
    enum CommandType { done, delete }

    static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    static final String SAVE_FILE_NAME = "save.txt";
    static final String ABSOLUTE_PATH_TO_SAVE_FILE = WORKING_DIRECTORY + File.separator + SAVE_FILE_NAME;
    static final int NUMBER_OF_SAVE_PARAMS_TASK = 3;

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */


        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = loadFromSaveFile(ABSOLUTE_PATH_TO_SAVE_FILE);
        String s;
        boolean tasksChanged;
        whileLoop: while (true) {
            tasksChanged = false;
            s = sc.nextLine();
            switch (s) {
            case "help":
                System.out.println("Here is the list of instructions: list, bye, done {index}, delete {index}, todo {description}, event {description} /at {date}, deadline {description} /by {date}");
                break;
            case "bye"://exit
                break whileLoop;
            case "list"://list
                if (taskList.size() > 0) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        StringBuilder sb = new StringBuilder();
                        Task t = taskList.get(i);
                        sb.append(i + 1).append(" ").append(t);
                        System.out.println(sb);
                    }
                } else {
                    System.out.println("There are no tasks in your list.");
                }
                break;
            default:
                if (s.indexOf("done") == 0) {//Set task to done
                    indexBasedCommand(taskList, CommandType.done, s);
                    tasksChanged = true;
                } else if (s.indexOf("delete") == 0) {
                    indexBasedCommand(taskList, CommandType.delete, s);
                    tasksChanged = true;
                } else if (s.indexOf("todo") == 0) {
                    insertByCommand(taskList, "todo", s, true);
                    tasksChanged = true;
                } else if (s.indexOf("event") == 0) {
                    insertByCommand(taskList, "event", s, true);
                    tasksChanged = true;
                } else if (s.indexOf("deadline") == 0) {
                    insertByCommand(taskList, "deadline", s, true);
                    tasksChanged = true;
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                if (tasksChanged) {
                    saveToFile(taskList, ABSOLUTE_PATH_TO_SAVE_FILE);
                }
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void indexBasedCommand(List<Task> taskList, CommandType commandType, String command) {
        try {
            int index = -1;//0 Bounded
            if (commandType.equals(CommandType.done)) {
                index += Integer.parseInt(command.replace("done", "").trim());
                Task t = taskList.get(index);
                t.markAsDone(true);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  " + t);
            } else if (commandType.equals(CommandType.delete)) {
                index += Integer.parseInt(command.replace("delete", "").trim());
                Task t = taskList.remove(index);
                System.out.println("Noted. I've removed this task: ");
                System.out.println("  " + t.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The index is invalid.");
        }
    }

    public static void insertByCommand(List<Task> taskList, String taskName, String line, boolean outputResponse) {
        String data = line.replaceFirst("^.*?\\s","");
        //If no change, then it's either empty, or invalid command
        if (line.equals(data)) {
            if (line.equals("todo") || line.equals("event") || line.equals("deadline")) {
                data = "";
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                return;
            }
        }
        String[] splitData;
        switch (taskName) {
        case "todo":
            if (Task.validateData(data, "todo"))
                insertNewTask(taskList, new Todo(data), outputResponse);
            break;
        case "event":
            splitData = TaskWithDate.extractDataFromLine(data, " /at ");
            if (TaskWithDate.validateData(splitData, "event"))
                insertNewTask(taskList, new Event(splitData[0], splitData[1]), outputResponse);
            break;
        case "deadline":
            splitData = TaskWithDate.extractDataFromLine(data, " /by ");
            if (TaskWithDate.validateData(splitData, "deadline"))
            insertNewTask(taskList, new Deadline(splitData[0], splitData[1]), outputResponse);
            break;
        }
    }

    public static void insertNewTask(List<Task> taskList, Task taskToInsert, boolean outputResponse) {
        if(taskToInsert != null) {
            taskList.add(taskToInsert);
            if (outputResponse) {
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + taskToInsert.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
        }
    }

    /**
     * Load from save file (txt file in hard disk) (path currently hardcoded).
     * @param path absolute path to file
     * @return list of tasks
     */
    public static List<Task> loadFromSaveFile(String path) {
        List<Task> results = new ArrayList<>();
        // read the content from file
        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader(path))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                //Process
                processLineFromSaveFile(results, line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found at " + path);
        } catch (IOException e) {
            System.out.println("An exception occurred when loading from file at " + path);
        }
        //Return this by default
        return results;
    }

    /**
     * Process line(s) obtained from loadFromSaveFile
     * @param taskList List of tasks
     * @param line String, a line from the save text file
     * @return Task|null
     */
    public static void processLineFromSaveFile(List<Task> taskList, String line) {
        String[] data = line.split("\\|");
        if (data.length == NUMBER_OF_SAVE_PARAMS_TASK) {
            insertByCommand(taskList, data[0], data[1], false);
            taskList.get(taskList.size() - 1).isDone = data[2].equals("true");
        } else {
            System.out.println("Error in parsing line from save file");
        }
    }

    /**
     * Save task list to txt file in hard disk (path currently hardcoded).
     * @param taskList List of tasks
     * @param path path to save file to
     */
    public static void saveToFile(List<Task> taskList, String path) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path, "UTF-8");
            for (Task task : taskList) {
                StringBuilder sb = new StringBuilder();
                String taskCommand = task.getClass().getName().toLowerCase();
                sb.append(taskCommand)
                        .append("|")
                        .append(taskCommand)
                        .append(" ")
                        .append(task.getDescription());
                if (task instanceof Event) {
                    sb.append(" /at ")
                            .append(((TaskWithDate) task).getDate());
                } else if (task instanceof Deadline) {
                    sb.append(" /by ")
                            .append(((TaskWithDate) task).getDate());
                }
                sb.append("|")
                        .append(task.isDone());

                writer.println(sb);
            }
        } catch (IOException e) {
            System.out.println("An exception occurred when saving to file.");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
