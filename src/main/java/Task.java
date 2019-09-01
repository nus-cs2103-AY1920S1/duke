import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a task
 * Contains a description of the task
 * Contains the list of tasks in an ArrayList
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
    }

    /**
     * Determines the type of the task.
     * Throws exceptions if command input is invalid
     *
     * @param command  Description of task to be added
     * @return type Type of task to be added to task list
     */
    public static String checkCommandType(String command) throws DukeException {
        String type = "";
        if (command.equals("save")){
            type = "save";
        } else if (command.equals("list")) {
            type = "list";
        } else if (command.contains("delete")) {
            type = "delete";
            command = command.substring(6);
            if (command.isEmpty()) {
                throw new DukeException("    OOPS!! Please specify which task to delete");
            }
            command = command.substring(1);
            if (command.charAt(0) < '0' || command.charAt(0) > '9') {
                throw new DukeException("    OOPS!! Please enter a number for task number to be deleted");
            }
        } else if (command.contains("done")) {
            type = "done";
            command = command.substring(4);
            if (command.isEmpty()) {
                throw new DukeException("    OOPS!! Please specify which task is done");
            }
            command = command.substring(1);
            if (command.charAt(0) < '0' || command.charAt(0) > '9') {
                    throw new DukeException("    OOPS!! Please enter a number for task number that is done");
            }
        } else if (command.contains("event")) {
            if (command.contains("/at")) {
                type = "event";
                command = command.substring(5);
            } else {
                throw new DukeException("    OOPS!! The event must include a time after the keyword /at");
            }
        } else if (command.contains("deadline")) {
            if  (command.contains("/by")) {
                type = "deadline";
                command = command.substring(8);
            } else {
                throw new DukeException("    OOPS!! The deadline must include a time after the keyword /by");
            }
        } else if (command.contains("todo")) {
            type = "todo";
            command = command.substring(4);
            if (command.isEmpty()) {
                throw new DukeException("    OOPS!! The description of a todo cannot be empty.");
            }
        } else {
            throw new DukeException("    OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (command.equals(" ")) {
            throw new DukeException("    No command received, please re-enter command.");
        }
        return type;
    }

    /**
     * Adds a new task to the task list
     *
     * @param command  Description of task to be added
     * @param type Type of task to be added to task list
     */
    public static void addTask(String command, String type) {

        Task newTask;
        switch(type) {
            case "event":
                newTask = new Event(command);
                tasks.add(newTask);
                break;

            case "deadline":
                newTask = new Deadline(command);
                tasks.add(newTask);
                break;

            case "todo":
                newTask = new ToDo(command);
                tasks.add(newTask);
                break;

            default:
                newTask = new Task(command);
        }

        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + newTask);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list");
    }

    /**
     * Marks a task as done
     *
     * @param command  Command that includes the index of task to be marked
     */
    public static void markAsDone(String command) {
        String commandStub = command.substring(5);
        int completedTaskNumber = Integer.parseInt(commandStub);
        tasks.get(completedTaskNumber - 1).isDone = true;
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("      " + tasks.get(completedTaskNumber - 1));
    }

    /**
     * Prints the entire task list with index
     *
     */
    public static void printTaskList() {
        System.out.println("    Here are the tasks in your list:\n");
        int index = 1;
        for (Task x : tasks) {
            System.out.println("    " + index + ". " + x);
            index++;
        }
    }

    /**
     * Deletes a task from the task list
     *
     * @param index  Index of the task to be deleted from task list
     */
    public static void deleteTask(String command) {
        command = command.substring(7);
        int index = Integer.parseInt(command);
        if (index <= tasks.size() && index >0) {
            Task removedTask = tasks.remove(index - 1);
            System.out.println("    Noted. I've removed this task:\n      " + removedTask);
            System.out.println("    Now you have " + tasks.size() + " tasks left in the list");
        } else {
            System.out.println("     Index of task to be deleted not found");
        }
        if (tasks.isEmpty()) {
            System.out.println("    Congratulations, your last task has been deleted!");
        }
    }

    /**
     * Determines if task is done
     *
     * @return tick symbol if done, X symbol if not done
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getTime() {
        return "";
    }


    public static void saveTaskList() {
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        try
        {
            String filename= "duke.txt";
            FileWriter fw = new FileWriter(filename,false);
            //appends the string to the file
            int index = 1;
            for (Task x : tasks) {
                String type = "";
                switch (x.type) {
                    case "T":
                        type = "To-Do   ";
                        break;
                    case "E":
                        type = "Event   ";
                        break;
                    case "D":
                        type = "Deadline";
                }
                String isDone = "";
                if (x.isDone) {
                    isDone = "Done    ";
                } else {
                    isDone = "Not done";
                }
                String description = x.description;
                String time = x.getTime();
                strLine = type + " | " + isDone + " | " + description + time + "\n";
                fw.write(strLine);
                index++;
            }
            fw.write("End of file");
            fw.close();
            System.out.println("    Task list has been saved!");
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public static void loadFromFile(String fileInput) {
        String command = fileInput.substring(22, fileInput.length());
        if (fileInput.contains("/by")) {
            command = "deadline " + command;
            tasks.add(new Deadline(command));
        } else if (fileInput.contains ("/at")) {
            command = "event " + command;
            tasks.add(new Event(command));
        } else {
            tasks.add(new ToDo(command));
        }
    }

    public static void displayTaskList() {
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("duke.txt"));
            //read the file content
            System.out.println("Here is your task list:");
            System.out.println("Type     | Status   | Description (with time)\n");
            while (strLine != null) {
                sb.append(strLine);
                sb.append(System.lineSeparator());
                strLine = br.readLine();

                if (strLine.contains("End of file")) {
                    break;
                }
                loadFromFile(strLine);
                System.out.println(strLine);
            }
            br.close();
        } catch(IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
