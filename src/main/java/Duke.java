import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

/*
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // boolean param to append and not overwrite
        fw.append(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File loadupFile = new File("C:\\repos\\duke\\out\\data\\TaskList.txt");
            Scanner scLoad = new Scanner(loadupFile);
            while (scLoad.hasNextLine()) {
                String sentence = scLoad.nextLine();
                String[] taskInfo = sentence.split(" \\u007C ");
                if (taskInfo[0].equals("T")) {
                    Task t = new ToDo(taskInfo[2]);
                    if (taskInfo[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                } else if (taskInfo[0].equals("D")) {
                    Task t = new Deadline(taskInfo[2], taskInfo[3]);
                    if (taskInfo[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                } else if (taskInfo[0].equals("E")) {
                    Task t = new Event(taskInfo[2], taskInfo[3]);
                    if (taskInfo[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                } else {

                }
            }
            scLoad.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        boolean dukeIsRunning = true;

        while (dukeIsRunning) {
            String next = sc.nextLine();
            String firstWord = next.split(" ")[0]; // captures first word of user input
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                dukeIsRunning = false;
            } else if (next.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
            } else if (firstWord.equals("done")) {
                int index = Integer.parseInt(next.split(" ")[1]);
                tasks.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done\n" + tasks.get(index - 1));
            } else if (firstWord.equals("delete")) {
                int target = Integer.parseInt(next.split(" ")[1]);
                System.out.println("Noted. I've removed this task:\n\t" + tasks.get(target - 1));
                tasks.remove(target - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")){
                try {
                    String[] arrCommands = next.split(" ");
                    if (arrCommands.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a " + firstWord + " cannot be empty.");
                    }
                    // remaining is the remaining words after the first command, e.g. todo
                    String remaining = next.split(" ", 2)[1];
                    // desc is the description of the task
                    String desc = remaining.split("/")[0];
                    switch (firstWord) {
                        case "todo":
                            Task todo = new ToDo(desc);
                            tasks.add(todo);
                            System.out.println("Got it. I've added this task: \n\t"
                                    + todo + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
                            break;
                        case "deadline":
                            String dueDateDeadline = remaining.split("/by ")[1];
                            Task deadline = new Deadline(desc, dueDateDeadline);
                            tasks.add(deadline);
                            System.out.println("Got it. I've added this task: \n\t"
                                    + deadline + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
                            break;
                        case "event":
                            String dueDateEvent = remaining.split("/at ")[1];
                            Task event = new Event(desc, dueDateEvent);
                            tasks.add(event);
                            System.out.println("Got it. I've added this task: \n\t"
                                    + event + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException de) {
                    System.out.println(de);
                } catch (Exception e) {
                    System.out.println("General Error");
                }
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                catch (DukeException de) {
                    System.out.println(de);
                }
            }
        }

        // loop terminated, write to file output
        try {
            String taskListFilePath = "C:\\repos\\duke\\out\\data\\TaskList.txt";
            FileWriter fw = new FileWriter (taskListFilePath);
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i) instanceof Deadline) {
                    writeToFile(taskListFilePath, "D | " + tasks.get(i).isDone() + " | "
                            + tasks.get(i).getTask() + " | " + ((Deadline) tasks.get(i)).getDueDate());
                } else if (tasks.get(i) instanceof Event) {
                    writeToFile(taskListFilePath, "E | " + tasks.get(i).isDone() + " | "
                            + tasks.get(i).getTask() + " | " + ((Event) tasks.get(i)).getDueDate());
                } else {
                    writeToFile(taskListFilePath, "T | " + tasks.get(i).isDone() + " | "
                            + tasks.get(i).getTask());
                }
                if (i != tasks.size() - 1) {
                    writeToFile(taskListFilePath, "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred " + e.getMessage());
        }

    }
}
*/