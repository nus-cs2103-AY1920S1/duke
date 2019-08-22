import java.lang.reflect.Array;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> commands = new ArrayList<Task>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ");
            String command = inputArr[0];
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.close();
                    break;
                } else if(command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    int count = 1;
                    for (Task s : commands) {
                        System.out.println(count + ". " + s);
                        count++;
                    }
                } else if (command.equals("done")) {
                    try {
                        int index = Integer.parseInt(inputArr[1]) - 1;
                        try {
                            Task doneTask = commands.get(index);
                            doneTask.markAsDone();
                            System.out.println("Nice! I've marked this task as done:\n  " + doneTask);
                        } catch(IndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Index for done does not exist in the list.");
                        }
                    } catch(ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! Index for done cannot be empty.");
                    }
                } else if (command.equals("deadline")) {
                    try {
                        String dL = input.split(" ", 2)[1];
                        try {
                            String[] taskDeadLine = dL.split(" /by ");
                            String taskD = taskDeadLine[0];
                            String by = taskDeadLine[1];
                            Task tt = new Deadline(taskD, by);
                            commands.add(tt);
                            System.out.println("Got it. I've added this task:\n  " + tt
                                    + "\nNow you have " + commands.size() + " tasks in the list.");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The format for deadline is wrong. Please follow: <description> /by <time>");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of deadline cannot be empty.");
                    }
                } else if(command.equals("event")) {
                    try {
                        String eEvent = input.split(" ", 2)[1];
                        try {
                            String[] taskEvent = eEvent.split(" /at ");
                            String taskE = taskEvent[0];
                            String at = taskEvent[1];
                            Task ee = new Event(taskE, at);
                            commands.add(ee);
                            System.out.println("Got it. I've added this task:\n  " + ee
                                    + "\nNow you have " + commands.size() + " tasks in the list.");
                        } catch(ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The format for event is wrong. Please follow: <description> /at <time>");
                        }
                    } catch(ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of event cannot be empty.");
                    }
                } else if(command.equals("todo")){
                    try {
                        String todoT = input.split(" ", 2)[1];
                        Task t = new Todo(todoT);
                        commands.add(t);
                        System.out.println("Got it. I've added this task:\n  " + t
                                + "\nNow you have " + commands.size() + " tasks in the list.");
                    } catch(ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if(command.equals("delete")) {
                    try {
                        int i = Integer.parseInt(inputArr[1]) - 1;
                        try {
                            Task tt = commands.remove(i);
                            System.out.println("Noted. I've removed this task:\n  " + tt +
                                               "\nNow you have " + commands.size() + " tasks in the list.");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Index for delete does not exist in the list.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! Index for delete cannot be empty.");
                    }
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
