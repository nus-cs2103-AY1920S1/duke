import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while(true) {

            try {
                String line = scanner.nextLine().toLowerCase();
                if (line.length() >= 3 && line.substring(0, 3).equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (line.length() >= 4 && line.substring(0, 4).equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < tasks.size() + 1; i++) {
                        Task currentTask = tasks.get(i - 1);
                        System.out.println(String.format(i + "." + currentTask.toString(), currentTask.getStatusIcon()));
                    }
                } else if (line.length() >= 6 && line.substring(0, 6).equals("delete")) {
                    int taskIndex = Integer.parseInt(line.replaceAll("\\D+","")) - 1;
                    Task currentTask = tasks.get(taskIndex);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(currentTask.toString());
                    tasks.remove(taskIndex);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));

                } else if (line.length() >= 4 && line.substring(0, 4).equals("done")) {
                    int taskIndex = Integer.parseInt(line.replaceAll("\\D+","")) - 1;
                    Task currentTask = tasks.get(taskIndex);
                    currentTask.setIsDone(); // set current task to done (opposite of current state of isDone)
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format(currentTask.toString(), currentTask.getStatusIcon()));
                } else {
                    Task newTask = null;
                    if (line.length() >= 4 && line.substring(0, 4).equals("todo")) { // task is a todo
                        String newLine = line.substring(4);

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
                        if (index == -1 || line.substring(8, index).length() == 0 || line.substring(index + 3).length() == 0 ) {
                            throw new DukeException("☹ OOPS!!! Please specify a [description of deadline] /by [date of deadline].");
                        } else {
                            String description = line.substring(8, index - 1); // from ' ' after 'deadline' to ' ' before '/by'
                            String by = line.substring(index + 4, line.length()); // from ' ' after '\by' to end of input
                            newTask = new Deadline(description, by);
                            informUserOfUpdate(tasks, newTask);
                        }
                    } else if (line.length() >= 5 && line.substring(0, 5).equals("event")){ // task is an event
                        int index = line.indexOf("/at ");
                        if (index == -1 || line.substring(5, index).length() == 0 || line.substring(index + 3).length() == 0) {
                            throw new DukeException("☹ OOPS!!! Please specify a [description of event] /at [date of event]");
                        } else {
                            String description = line.substring(5, index - 1); // from ' ' after 'event' to ' ' before '/at'
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
        scanner.close();
    }
    
    public static void informUserOfUpdate(List<Task> tasks, Task newTask) {
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

//    public static void saveDataToFile(Task task, )
}
