import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;

public class Duke {
    public static String liner = "    ____________________________________________________________";
    public static File dir;
    public static File file;

    public static void main(String[] args) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        //greet user
        String greeting = "     Hello! I'm Duke\n     What can I do for you?";
        System.out.println(liner + "\n" + greeting + "\n" + liner);

        //initialize txt file in data folder
        dir = new File("data");
        dir.mkdirs();
        file = new File(dir, "duke.txt");
        if (file.exists()) { //clear contents of existing txt
            // reconstruct array
            reloadArray(tasks);
        }

        //user inputs
        String input;
        Scanner sc = new Scanner(System.in);
        while (!(input = sc.nextLine()).equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println(liner);
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        int num = i + 1;
                        Task currTask = tasks.get(i);
                        System.out.println("     " + num + ". " + currTask.toString());
                    }
                    System.out.println(liner);
                } else if (input.startsWith("done")) {
                    String[] arr = input.split(" ");
                    if (arr.length == 2 && arr[1].matches("\\d+")) {
                        int pos = Integer.parseInt(arr[1]);
                        //error handling
                        if (pos > tasks.size() || pos <= 0 ) {
                            throw new DukeException(liner + "\n       OOPS!!! Task id not within range of total number of tasks!\n" + liner);
                        }
                        //
                        Task currTask = tasks.get(pos - 1);
                        currTask.markAsDone();
                        updateChangesTXT(currTask, "done", pos - 1);
                        printDoneTask(currTask);
                    } else {
                        //error handling
                        throw new DukeException(liner + "\n       OOPS!!! Invalid Done Command! Please try again!\n" + liner);
                    }
                } else if (input.startsWith("delete")) {
                    String[] arr = input.split(" ");
                    if (arr.length == 2 && arr[1].matches("\\d+")) {
                        int pos = Integer.parseInt(arr[1]);
                        //error handling
                        if (pos > tasks.size() || pos <= 0 ) {
                            throw new DukeException(liner + "\n       OOPS!!! Task id not within range of total number of tasks!\n" + liner);
                        }
                        //
                        Task currTask = tasks.get(pos - 1);
                        tasks.remove(Integer.parseInt(arr[1]) - 1);
                        updateChangesTXT(currTask, "delete", pos - 1);
                        printDeletedTask(currTask, tasks.size());
                    } else {
                        //error handling
                        throw new DukeException(liner + "\n       OOPS!!! Invalid Delete Command! Please try again!\n" + liner);
                    }
                } else if (input.startsWith("todo")) {
                    String command = input.replaceFirst("todo", "").trim();
                    Task newTask = new Todo(command);
                    //error handling
                    if (command.isEmpty()) {
                        throw new DukeException(liner + "\n       OOPS!!! The description of a todo cannot be empty.\n" + liner);
                    }
                    //
                    tasks.add(newTask);
                    saveChangesTXT(newTask, "T");
                    printAddTask(newTask, tasks.size());
                } else if (input.startsWith("deadline")) {
                    String command = input.replaceFirst("deadline", "").trim();
                    String[] arr = command.split("/by");
                    //error handling
                    if (arr[0].trim().isEmpty()) {
                        throw new DukeException(liner + "\n       OOPS!!! The description of a deadline cannot be empty.\n" + liner);
                    } else if (arr.length < 2 || arr[1].trim().isEmpty()) {
                        throw new DukeException(liner + "\n       OOPS!!! The deadline must have a specified date/time.\n" + liner);
                    }
                    //
                    Task newTask = new Deadline(arr[0].trim(), arr[1].trim());
                    tasks.add(newTask);
                    saveChangesTXT(newTask, "D");
                    printAddTask(newTask, tasks.size());
                } else if (input.startsWith("event")) {
                    String command = input.replace("event", "").trim();
                    String[] arr = command.split("/at");
                    //error handling
                    if (arr[0].trim().isEmpty()) {
                        throw new DukeException(liner + "\n       OOPS!!! The description of a event cannot be empty.\n" + liner);
                    } else if (arr.length < 2 || arr[1].trim().isEmpty()) {
                        throw new DukeException(liner + "\n       OOPS!!! The event must have a specified date/time.\n" + liner);
                    }
                    //
                    Task newTask = new Event(arr[0].trim(), arr[1].trim());
                    tasks.add(newTask);
                    saveChangesTXT(newTask, "E");
                    printAddTask(newTask, tasks.size());
                } else {
                    throw new DukeException(liner + "\n       OOPS!!! I'm sorry, but I don't know what that means :-(\n" + liner);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(liner + "\n     Bye. Hope to see you again soon!\n" + liner);
    }

    // add new tasks information
    public static void saveChangesTXT(Task task, String type) {
        try {
            FileWriter writer = new FileWriter(file, true); //initialize file writer
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            int status;
            if (task.getStatus() == true) {
                status = 1;
            } else {
                status = 0;
            }
            bufferedWriter.write(type + " | " + status + " | " + task.getDescription());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // update existing tasks: done and delete commands
    public static void updateChangesTXT(Task task, String command, int position) {
        try {
            File temp = new File(dir, "temp.txt");
            FileWriter writer = new FileWriter(temp);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            FileReader reader = new FileReader(file); //initialize file reader
            BufferedReader bufferedReader = new BufferedReader(reader);

            int counter = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (counter != position && (!command.equals("delete") || !command.equals("done"))) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                    counter++;
                    continue;
                }

                // DONE COMMAND: counter == position
                if (command == "done") {
                    int status;
                    if (task.getStatus() == true) {
                        status = 1;
                    } else {
                        status = 0;
                    }
                    bufferedWriter.write(task.getType() + " | " + status + " | " + task.getDescription());
                    bufferedWriter.newLine();
                }
                counter++;
                // DELETE COMMAND: ignore current line
            }
            //overwrite master txt copy
            bufferedWriter.close();
            file.delete();
            file = new File(dir, "duke.txt");
            temp.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // clear file contents
    public static void clearFile() {
        try {
            FileWriter w = new FileWriter(file);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load data into array
    public static void reloadArray(ArrayList<Task> tasks) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(" \\| ");
                Task task;
                switch (arr[0]) {
                    case "T": //todo
                        task = new Todo(arr[2]);
                        break;
                    case "D": //deadline
                        task = new Deadline(arr[2], arr[3]);
                        break;
                    case "E": //event
                        task = new Deadline(arr[2], arr[3]);
                        break;
                    default:
                        throw new IOException("Something is Wrong!");
                }

                if (Integer.parseInt(arr[1]) == 1) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // print add task message
    public static void printAddTask(Task newTask, int totalTasks) {
        System.out.println(liner);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + newTask.toString());
        System.out.println("     Now you have " + totalTasks + " tasks in the list.");
        System.out.println(liner);
    }

    // print done task message
    public static void printDoneTask(Task currTask) {
        System.out.println(liner);
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       "  + currTask.toString());
        System.out.println(liner);
    }

    // print deleted task message
    public static void printDeletedTask(Task currTask, int totalTasks) {
        System.out.println(liner);
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       "  + currTask.toString());
        System.out.println("     Now you have " + totalTasks + " tasks in the list.");
        System.out.println(liner);
    }
}
