
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
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
            } catch (ParseException ex) {
                System.out.println("☹ OOPS!!! The Date/Time field is invalid");
                userInput = input.nextLine();
                continue;
            }

            if (userInput.startsWith("done")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                Task selectedTask = taskList.get(taskNumber - 1);
                selectedTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + selectedTask);
            } else if (userInput.startsWith("delete")) {
                int taskNumber = Integer.parseInt(userInput.substring(7));
                delTaskInTaskList(taskList, taskNumber);
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);
                addTaskToTaskList(taskList, new ToDo(description));
            } else if (userInput.startsWith("deadline")) {
                String description = userInput.substring(9, userInput.indexOf('/') - 1);
                String by = userInput.substring(13 + description.length()).trim();

                try {
                    handleInput("by", by);
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                    userInput = input.nextLine();
                    continue;
                } catch (ParseException ex) {
                    System.out.println("☹ OOPS!!! The Date/Time field is invalid");
                    userInput = input.nextLine();
                    continue;
                }
                addTaskToTaskList(taskList, new Deadline(description, new DateTime(by)));
            } else if (userInput.startsWith("event")) {
                String description = userInput.substring(6, userInput.indexOf('/') - 1);
                String at = userInput.substring(10 + description.length()).trim();
                try {
                    handleInput("at", at);
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                    userInput = input.nextLine();
                    continue;
                } catch (ParseException ex) {
                    System.out.println("☹ OOPS!!! The Date/Time field is invalid");
                    userInput = input.nextLine();
                    continue;
                }
                addTaskToTaskList(taskList, new Event(description, new DateTime(at)));
            }
            userInput = input.nextLine();
        }
    }

    public static void handleInput(String type, String userInput) throws EmptyFieldException, InvalidInputException, ParseException {
        if (type.equals("done")) {
            if (userInput.substring(4).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The task number cannot be empty.");
            }
            int taskNum = Integer.parseInt(userInput.substring(5));
            if (taskNum > taskList.size()) {
                throw new InvalidInputException("☹ OOPS!!! You do not have that many tasks.");
            } else if (taskNum <= 0) {
                throw new InvalidInputException("☹ OOPS!!! Your task number is invalid.");
            }
        } else if (type.equals("delete")) {
            if (userInput.substring(6).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The task number cannot be empty.");
            }
            int taskNum = Integer.parseInt(userInput.substring(7));
            if (taskNum > taskList.size()) {
                throw new InvalidInputException("☹ OOPS!!! You do not have that many tasks.");
            } else if (taskNum <= 0) {
                throw new InvalidInputException("☹ OOPS!!! Your task number is invalid.");
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
            String formatString = "dd/mm/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(userInput.split(" ")[0]);
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
            String formatString = "dd/mm/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(userInput.split(" ")[0]);
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

    public static void delTaskInTaskList(ArrayList<Task> tasklist, int taskNum) {
        Task taskToRemove = taskList.get(taskNum - 1);
        taskList.remove(taskNum - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskToRemove);
        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
    }
}
