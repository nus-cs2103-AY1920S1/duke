package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Storage storage = new Storage("data/duke.txt");
            ArrayList<Task> tasks = storage.load();

            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?");

            boolean readingCommands = true;
            while (readingCommands) {
                try {
                    String input = sc.nextLine();
                    String[] inputWords = input.split(" ", 2);
                    String command = inputWords[0];

                    switch (command) {
                        case "todo": {
                            try {
                                String description = inputWords[1];
                                storage.addTask(new ToDo(description));
                            } catch (IndexOutOfBoundsException e) {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                            break;
                        }

                        case "deadline": {
                            try {
                                String[] params = inputWords[1].split(" /by ");
                                String description = params[0];
                                Date by = DateUtil.parse(params[1]);
                                storage.addTask(new Deadline(description, by));
                            } catch (IndexOutOfBoundsException e) {
                                throw new DukeException("Creating a Deadline failed: Insufficient params provided");
                            }
                            break;
                        }

                        case "event": {
                            try {
                                String[] params = inputWords[1].split(" /at ");
                                String description = params[0];
                                Date at = DateUtil.parse(params[1]);
                                storage.addTask(new Event(description, at));
                            } catch (IndexOutOfBoundsException e) {
                                throw new DukeException("Creating an Event failed: Insufficient params provided");
                            }
                            break;
                        }

                        case "list": {
                            for (int i = 1; i <= tasks.size(); i++) {
                                Task task = tasks.get(i - 1);
                                System.out.println(i + ". " + task);
                            }
                            break;
                        }

                        case "done": {
                            int id = Integer.parseInt(inputWords[1]) - 1;
                            try {
                                Task task = tasks.get(id);
                                task.markAsDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println(task);
                            } catch (IndexOutOfBoundsException e) {
                                throw new DukeException("Marking task with ID " + id + " failed: Invalid ID");
                            }
                            break;
                        }

                        case "delete": {
                            int id = Integer.parseInt(inputWords[1]) - 1;
                            storage.deleteTask(id);
                            break;
                        }

                        case "bye": {
                            readingCommands = false;
                            System.out.println("Bye. Hope to see you again soon!");
                            break;
                        }

                        default: {
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }
}

