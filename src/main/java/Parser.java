import java.util.ArrayList;

public class Parser {
    protected ArrayList<Task> commands;
    protected Storage storage;


    public Parser(ArrayList<Task> commands, Storage storage) {
        this.commands = commands;
        this.storage = storage;
    }

    /**
     * Parses and handles the output for all the commands
     * @param command
     */
    public void parse(String command) {
            String[] inputArr = command.split(" ");
            String userCommand = inputArr[0];
            try {
                if (userCommand.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                } else if (userCommand.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    int count = 1;
                    for (Task s : commands) {
                        System.out.println(count + ". " + s);
                        count++;
                    }
                } else if (userCommand.equals("done")) {
                    try {
                        int index = Integer.parseInt(inputArr[1]) - 1;
                        try {
                            Task doneTask = commands.get(index);
                            doneTask.markAsDone();
                            System.out.println("Nice! I've marked this task as done:\n  " + doneTask);
                            storage.done(doneTask, index + 1);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Index for done does not exist in the list.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! Index for done cannot be empty.");
                    }
                } else if (userCommand.equals("deadline")) {
                    try {
                        String dL = command.split(" ", 2)[1];
                        try {
                            String[] taskDeadLine = dL.split(" /by ");
                            String taskD = taskDeadLine[0];
                            String by = taskDeadLine[1];
                            Task tt = new Deadline(taskD, new DateTime(by));
                            commands.add(tt);
                            System.out.println("Got it. I've added this task:\n  " + tt
                                    + "\nNow you have " + commands.size() + " tasks in the list.");
                            storage.save(tt);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The format for deadline is wrong. Please follow: <description> /by <time>");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of deadline cannot be empty.");
                    }
                } else if (userCommand.equals("event")) {
                    try {
                        String eEvent = command.split(" ", 2)[1];
                        try {
                            String[] taskEvent = eEvent.split(" /at ");
                            String taskE = taskEvent[0];
                            String at = taskEvent[1];
                            Task ee = new Event(taskE, new DateTime(at));
                            commands.add(ee);
                            System.out.println("Got it. I've added this task:\n  " + ee
                                    + "\nNow you have " + commands.size() + " tasks in the list.");
                            storage.save(ee);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The format for event is wrong. Please follow: <description> /at <time>");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of event cannot be empty.");
                    }
                } else if (userCommand.equals("todo")) {
                    try {
                        String todoT = command.split(" ", 2)[1];
                        Task t = new Todo(todoT);
                        commands.add(t);
                        System.out.println("Got it. I've added this task:\n  " + t
                                + "\nNow you have " + commands.size() + " tasks in the list.");
                        storage.save(t);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (userCommand.equals("delete")) {
                    try {
                        int i = Integer.parseInt(inputArr[1]) - 1;
                        try {
                            Task tt = commands.remove(i);
                            System.out.println("Noted. I've removed this task:\n  " + tt +
                                    "\nNow you have " + commands.size() + " tasks in the list.");
                            storage.delete(i);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Index for delete does not exist in the list.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! Index for delete cannot be empty.");
                    }
                } else if (userCommand.equals("find")) {
                    String word = inputArr[1];
                    System.out.println("Here are the matching tasks in your list:");
                    int count = 1;
                    for (Task ttt : commands) {
                        if (ttt.toString().contains(word)) {
                            System.out.println(count + ". " + ttt);
                            count++;
                        }
                    }
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

    }
}
