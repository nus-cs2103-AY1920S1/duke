import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        readInput();
        goodbye();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void readInput() {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printTaskList(taskList);
                userInput = input.nextLine();
                continue;
            }
            String[] userInputSplit = userInput.split(" ");

            try {
                handleInput(userInputSplit[0], userInput);
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                userInput = input.nextLine();
                continue;
            }

            if (userInput.startsWith("done")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                Task selectedTask = taskList.get(taskNumber - 1);
                selectedTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + selectedTask);
                userInput = input.nextLine();
            } else {
                if (userInput.startsWith("todo")) {
                    String description = userInput.substring(5);
                    addTaskToTaskList(taskList, new ToDo(description));
                } else if (userInput.startsWith("deadline")) {
                    String description = userInput.substring(9, userInput.indexOf('/') - 1);
                    String by = userInput.substring(14 + description.length());
                    try {
                        handleInput("by", by);
                    } catch (DukeException ex) {
                        System.out.println(ex.getMessage());
                    }
                    addTaskToTaskList(taskList, new Deadline(description, by));
                } else if (userInput.startsWith("event")) {
                    String description = userInput.substring(6, userInput.indexOf('/') - 1);
                    String at = userInput.substring(11 + description.length());
                    try {
                        handleInput("at", at);
                    } catch (DukeException ex) {
                        System.out.println(ex.getMessage());
                    }
                    addTaskToTaskList(taskList, new Event(description, at));
                }
                userInput = input.nextLine();
            }
        }
    }

    public static void handleInput(String type, String userInput) throws EmptyFieldException, InvalidInputException{
        if (type.equals("done")) {
            if (userInput.substring(4).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The task number cannot be empty.");
            } else if (Integer.parseInt(userInput.substring(5)) > taskList.size()) {
                throw new InvalidInputException("☹ OOPS!!! You do not have that many tasks yet.");
            }
        } else if (type.equals("todo")) {
            if (userInput.substring(4).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (type.equals("deadline")) {
            if (userInput.substring(8).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The new deadline cannot be empty.");
            } else if (!userInput.contains("/by")) {
                throw new EmptyFieldException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
            }
            String description = userInput.substring(9, userInput.indexOf('/'));
            if (description.isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (type.equals("by")) {
            if (userInput.isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
            }
        } else if (type.equals("event")) {
            if (userInput.substring(5).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The new event cannot be empty.");
            } else if (!userInput.contains("/at")) {
                throw new EmptyFieldException("☹ OOPS!!! The 'at' field of an event cannot be empty.");
            }
            String description = userInput.substring(6, userInput.indexOf('/'));
            if (description.isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        } else if (type.equals("at")) {
            if (userInput.isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The 'at' field of an event cannot be empty.");
            }
        } else if (type.isBlank() && userInput.isBlank()) {
            throw new EmptyFieldException("☹ OOPS!!! I'm sorry, but you have to input something");
        } else {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("task list is empty");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i));
            }
        }
    }

    public static void addTaskToTaskList(ArrayList<Task> tasklist, Task task) {
        tasklist.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
    }
}
