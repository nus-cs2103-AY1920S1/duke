import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException,IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader("duke.txt"));
        ArrayList<Task> tasks = readDataFromFile(reader);

        while(true) {
            try {
                String line = scanner.nextLine().toLowerCase();
                ///////////////// BYE //////////////////////////////
                if (line.length() >= 3 && line.substring(0, 3).equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                    ///////////////// LIST /////////////////////////////////
                } else if (line.length() >= 4 && line.substring(0, 4).equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < tasks.size() + 1; i++) {
                        Task currentTask = tasks.get(i - 1);
                        System.out.println(String.format(i + "." + currentTask.toString(), currentTask.getStatusIcon()));
                    }
                    ///////////////// DELETE //////////////////////////////
                } else if (line.length() >= 6 && line.substring(0, 6).equals("delete")) {
                    int taskIndex = Integer.parseInt(line.replaceAll("\\D+","")) - 1;
                    Task currentTask = tasks.get(taskIndex);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(currentTask.toString());
                    tasks.remove(taskIndex);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                    //////////////// DONE ///////////////////////////////////
                } else if (line.length() >= 4 && line.substring(0, 4).equals("done")) {
                    int taskIndex = Integer.parseInt(line.replaceAll("\\D+","")) - 1;
                    Task currentTask = tasks.get(taskIndex);
                    currentTask.setIsDone(); // set current task to done (opposite of current state of isDone)
                    System.out.println(currentTask.getStatusIcon());
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format(currentTask.toString(), currentTask.getStatusIcon()));
                } else {
                    Task newTask = null;
                    if (line.length() >= 4 && line.substring(0, 4).equals("todo")) { // task is a todo
                        String newLine = line.substring(5);

                        // if description is empty, throw exception
                        if (newLine.length() == 0) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            newTask = new ToDo(newLine);
                            informUserOfUpdate(tasks, newTask);
                        }
                    } else if (line.length() >= 8 && line.substring(0, 8).equals("deadline")) { // task is a deadline
                        int index = line.indexOf("/by "); // index of end of '\by ', which is ' '

                        // if no '/by' or no description or no deadline date, throw exception
                        if (index == -1 || line.substring(9, index).length() == 0 || line.substring(index + 3).length() == 0 ) {
                            throw new DukeException("☹ OOPS!!! Please specify a [description of deadline] /by [date of deadline].");
                        } else {
                            String description = line.substring(9, index - 1); // from word after 'deadline' to ' ' before '/by'
                            String by = line.substring(index + 4, line.length()); // from ' ' after '\by' to end of input
                            newTask = new Deadline(description, by);
                            informUserOfUpdate(tasks, newTask);
                        }
                    } else if (line.length() >= 5 && line.substring(0, 5).equals("event")){ // task is an event
                        int index = line.indexOf("/at ");
                        if (index == -1 || line.substring(6, index).length() == 0 || line.substring(index + 3).length() == 0) {
                            throw new DukeException("☹ OOPS!!! Please specify a [description of event] /at [date of event]");
                        } else {
                            String description = line.substring(6, index - 1); // from word after 'event' to ' ' before '/at'
                            String at = line.substring(index + 4, line.length()); // from ' ' after '\at' to end of input
                            newTask = new Event(description, at);
                            informUserOfUpdate(tasks, newTask);
                        }
                    } else {
                        // invalid input
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        writeDataToFile(tasks);
        scanner.close();
    }



    public static void informUserOfUpdate(ArrayList<Task> tasks, Task newTask) {
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    public static void writeDataToFile(ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("duke.txt"));
            for (Task task : tasks) {
                char type = task.getTaskType();
                String status = (task.getStatusIcon().equals("\u2713")) ? "1" : "0";
                String description = task.getDescription();
                String date = (type == 'T') ? "" : " | " + task.getDate();

                writer.write(type + " | " + status + " | " + description + date);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Task> readDataFromFile(BufferedReader reader) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] readData = line.split(" \\| ");
                Task newTask = null;
                if (readData[0].equals("T")) {
                    newTask = new ToDo(readData[1], readData[2]);
                } else if (readData[0].equals("D")) {
                    newTask = new Deadline(readData[1], readData[2], readData[3]);
                } else if (readData[0].equals("E")) {
                    newTask = new Event(readData[1], readData[2], readData[3]);
                }
                if (newTask != null) {
                    tasks.add(newTask);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }
}
