import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);

        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        TaskList taskList = new TaskList();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                taskList.printAllTasks();
                command = input.nextLine();
            } else if (command.contains("done")) {
                // Check if the first word is done
                String[] sentence = command.split(" ");

                try {
                    if (sentence[0].equals("done")) {
                        int completedTaskIndex = Integer.parseInt(sentence[1]);
                        taskList.markAsDone(completedTaskIndex); // If it wasn't marked before, this would print out a notification saying it is now marked.
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Which task on the list have you completed? (Eg 'done 2')");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! That task is not on the list, please check the list again by calling 'list'.");
                }

                command = input.nextLine();

            } else {
                try {
                    if (!command.isEmpty()) {
                        Task newTask = generateNewTask(command);
                        taskList.addTask(newTask);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask.toString());
                        System.out.println("Now you have " + taskList.numTasks + " tasks in the list");
                    }
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                command = input.nextLine();
            }
        }

        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    private static Task generateNewTask(String task) throws DukeException {
        try {
            String type = task.substring(0, task.indexOf(' '));
            String taskDescription = task.substring(task.indexOf(' ') + 1);
            Task newTask = new Task("dummy");

            if (type.equals("todo")) {
                newTask = new ToDo(taskDescription);
            }

            if (type.equals("deadline")) {
                String[] sentence = taskDescription.split("/by");
                String description = sentence[0];
                String deadline = sentence[1];
                newTask = new Deadline(description, deadline);
            }

            if (type.equals("event")) {
                String[] sentence = taskDescription.split("/at");
                String description = sentence[0];
                String time = sentence[1];
                newTask = new Event(description, time);
            }

            return newTask;

        } catch (StringIndexOutOfBoundsException rootError) {
            // if task type is correct, then error is due to empty description.
            if (task.equals("todo") | task.equals("deadline") | task.equals("event")) {
                throw new EmptyDescriptionException(task, rootError);
            } else {
                throw new UnknownTaskTypeException();
            }
        }
    }
}
