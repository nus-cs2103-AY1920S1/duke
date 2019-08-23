import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "________________________________________";
        String indent = "    ";
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println(indent + line);
        System.out.println(indent + "Hello! I'm Duke.\n" + indent + "What can I do for you today?");
        System.out.println(indent + line);

        Scanner sc = new Scanner(System.in);
        boolean chat = true;
        while(chat) {
            String input = sc.nextLine();
            input = input.trim();
            String[] inputArr = input.split("\\s+");
            String command = inputArr[0];

            System.out.println(indent + line);
            switch (command) {
                case "bye":
                    System.out.println(indent + "Bye! Hope to see you again soon.");
                    chat = false;
                    break;
                case "list":
                    if (list.size() == 0) {
                        System.out.println(indent + "You have no tasks.");
                    } else {
                        System.out.println(indent + "Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            int k = i + 1;
                            Task task = list.get(i);
                            System.out.println(indent + k + ". " + task);
                        }
                    }
                    break;
                case "done":
                    try {
                        if (input.equals("done")) {
                            throw new DukeException("☹ OOPS!!! Please specify the task number that you want to mark as done.");
                        } else if (inputArr.length > 2 ) {
                            throw new DukeException("☹ OOPS!!! The command format is invalid.");
                        }
                        int num = Integer.parseInt(inputArr[1]);
                        Task task = list.get(num - 1);
                        task.markAsDone();
                        System.out.println(indent + "Nice! I've marked this task as done:");
                        System.out.println(indent + indent + task);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(indent + "☹ OOPS!!! This task number does not exist.");
                    } catch (NumberFormatException e) {
                        System.out.println(indent + "☹ OOPS!!! Please specify the task number that you want to mark as done.");
                    } catch (Exception e) {
                        System.out.println(indent + e);
                    }
                    break;
                case "delete":
                    try {
                        if (input.equals("done")) {
                            throw new DukeException("☹ OOPS!!! Please specify the task number that you want to delete.");
                        } else if (inputArr.length > 2 ) {
                            throw new DukeException("☹ OOPS!!! The command format is invalid.");
                        }
                        int num = Integer.parseInt(inputArr[1]);
                        Task task = list.remove(num - 1);
                        System.out.println(indent + "Noted. I've removed the following task:");
                        System.out.println(indent + indent + task);
                        System.out.println(indent + "Now you have " + list.size() + " task(s) in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(indent + "☹ OOPS!!! This task number does not exist.");
                    } catch (NumberFormatException e) {
                        System.out.println(indent + "☹ OOPS!!! Please specify the task number that you want to mark as done.");
                    } catch (Exception e) {
                        System.out.println(indent + e);
                    }
                    break;
                case "todo":
                    try {
                        if (input.equals("todo")) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            Task task = new ToDo(input);
                            list.add(task);
                            printAddedTask(task, list);
                        }
                    } catch (DukeException de) {
                        System.out.println(indent + de);
                    } catch (Exception e) {
                        System.out.println(indent + e);
                    }
                    break;
                case "event":
                    try {
                        String[] eventCommand = input.split("/at");
                        if (input.equals("event")) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        } else if (eventCommand.length == 1) {
                            throw new DukeException("☹ OOPS!!! Please specify where the event is.");
                        }
                        String eventDescript = eventCommand[0].substring(6);
                        String at = eventCommand[1].substring(1);
                        Task eventTask = new Event(eventDescript, at);
                        list.add(eventTask);
                        printAddedTask(eventTask, list);
                    } catch (DukeException de) {
                        System.out.println(indent + de);
                    } catch (Exception e) {
                        System.out.println(indent + "☹ OOPS!!! That is an invalid command.");
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadlineCommand = input.split("/by");
                        if (input.equals("deadline")) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        } else if (deadlineCommand.length == 1) {
                            throw new DukeException("☹ OOPS!!! Please specify when the deadline is.");
                        }                        String deadlineDescript = deadlineCommand[0].substring(9);
                        String by = deadlineCommand[1].substring(1);
                        Task deadlineTask = new Deadline(deadlineDescript, by);
                        list.add(deadlineTask);
                        printAddedTask(deadlineTask, list);
                    } catch (DukeException de) {
                        System.out.println(indent + de);
                    } catch (Exception e) {
                        System.out.println(indent + "☹ OOPS!!! That is gan invalid command.");
                    }
                    break;
                default:
                    try {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means.");
                    } catch (Exception e) {
                        System.out.println(indent + e);
                    }
            }
            System.out.println(indent + line);
        }

    }

    public static void printAddedTask(Task task, ArrayList list) {
        String indent = "    ";
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println(indent + indent + task);
        System.out.println(indent + "Now you have " + list.size() + " task(s) in the list.");
    }
}
