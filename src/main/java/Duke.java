import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);

        TaskList taskList = new TaskList();
        String filepath = "../../../data/duke.txt";
        Storage storage = new Storage(filepath);

        System.out.println("Here is the list of tasks from where you've left off: ");
        try {
            taskList = new TaskList(storage.loadPreviousTasks());
            taskList.printAllTasks();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Scanner input = new Scanner(System.in);
        String command = input.nextLine().trim(); //trim leading/trailing whitespace


        // Trying out LocalDateTime, DateTimeFormatter
        //LocalDateTime current = LocalDateTime.of(2019, 12, 2, 18, 0);
        //System.out.println(current);

        while (!command.equals("bye")) {
            // When command is 'list'
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                taskList.printAllTasks();
                command = input.nextLine().trim();

            } else if (command.contains("done")) {
                String[] sentence = command.split(" ");

                // When command is 'done'
                try {
                    if (sentence[0].equals("done")) { // Check if the first word is done
                        int completedTaskIndex = Integer.parseInt(sentence[1]);
                        taskList.markAsDone(completedTaskIndex); // If it wasn't marked before, this would print out a notification saying it is now marked.

                        // Save new list to storage
                        try {
                            storage.saveToFile(taskList.toString());
                        } catch(IOException e) {
                            System.out.println("file error");
                        }

                    } else {
                        throw new UnknownTaskTypeException();
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Which task on the list have you completed? (Eg 'done 2')");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! That task is not on the list, please check the list again by calling 'list'.");
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! Wrong format. Please key in a valid number (Eg 'done 2')");
                } catch (UnknownTaskTypeException e) {
                    System.out.println(e.getMessage());
                }

                command = input.nextLine().trim();

            } else if (command.contains("delete")) {
                String[] sentence = command.split(" ");

                // When command is 'delete'
                try {
                    if (sentence[0].equals("delete")) {
                        int taskIndex = Integer.parseInt(sentence[1]);
                        Task deletedTask = taskList.deleteTask((taskIndex - 1));
                        System.out.println("Noted. I've removed this task:\n"
                                + "  "
                                + deletedTask
                                + String.format("\nNow you have %d tasks in the list.", taskList.numTasks));

                        // Save new list to storage
                        try {
                            storage.saveToFile(taskList.toString());
                        } catch(IOException e) {
                            System.out.println("file error");
                        }

                    } else {
                        throw new UnknownTaskTypeException();
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Which task on the list would you like to delete? (Eg. 'delete 2')");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! That task is not on the list, please check the list again by calling 'list'.");
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! Wrong format. Please key in a valid number (Eg 'delete 2')");
                } catch (UnknownTaskTypeException e) {
                    System.out.println(e.getMessage());
                }

                command = input.nextLine().trim();

            } else {
                // Generate new task
                try {
                    if (!command.isEmpty()) {
                        Task newTask = generateNewTask(command);
                        taskList.addTask(newTask);

                        // Save the new list to storage
                        try {
                            storage.saveToFile(taskList.toString());
                        } catch(IOException e) {
                            System.out.println("file error");
                        }

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask.toString());
                        System.out.println("Now you have " + taskList.numTasks + " tasks in the list.");
                    }
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                command = input.nextLine().trim();
            }
        }

        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    private static String formatDateTime(String deadline) {
        // Split to individual components
        deadline = deadline.trim();
        System.out.println(deadline);
        String[] dd = deadline.split(" ");
        String[] date = dd[0].split("/");
        String time = dd[1];
        System.out.println(time);
        int hours = Integer.valueOf(time.substring(0,2));
        int minutes = Integer.valueOf(time.substring(2));

        LocalDateTime actualDateTime = LocalDateTime.of(Integer.valueOf(date[2]), Integer.valueOf(date[1]),
                Integer.valueOf(date[0]), hours, minutes);
        // maybe error here
        DateTimeFormatter dtf=  DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm a");
        String formatted = " " + dtf.format(actualDateTime);
        return formatted;
    }

    private static Task generateNewTask(String task) throws DukeException {
        try {
            String type = task.substring(0, task.indexOf(' '));
            String taskDescription = task.substring(task.indexOf(' ') + 1);
            Task newTask = new Task("dummy");

            // Create the appropriate Task type
            if (type.equals("todo")) {
                newTask = new ToDo(taskDescription);
            }

            try {
                if (type.equals("deadline")) {
                    String[] sentence = taskDescription.split("/by");
                    String description = sentence[0];
                    String deadline = formatDateTime(sentence[1]);

                    newTask = new Deadline(description, deadline);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingDateTimeException("DateTime missing."
                        + "Please set a deadline. (Eg. deadline read book /by Sunday)");
            }

            try {
                if (type.equals("event")) {
                    String[] sentence = taskDescription.split("/at");
                    String description = sentence[0];
                    String time = sentence[1];
                    System.out.println(time);
                    newTask = new Event(description, time);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingDateTimeException("Event time period missing."
                        + "Please set a start and end time. (Eg. event dance /at Mon 2-4pm)");
            }

            return newTask;

        } catch (StringIndexOutOfBoundsException rootError) {
            // If task type is correct, the error is due to empty description
            // else the task type is unknown
            if (task.equals("todo") | task.equals("deadline") | task.equals("event")) {
                throw new EmptyDescriptionException(task, rootError);
            } else {
                throw new UnknownTaskTypeException();
            }
        }
    }
}
