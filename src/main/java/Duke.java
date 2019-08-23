import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String indentation = "     ";
    private static final String separator = "    ____________________________________________________________\n";
    private static final String storagePath = "/Users/xiaoyu/duke/data/duke.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");


    private static Scanner scanner;
    private static final ArrayList<Task> tasks = new ArrayList<>();


    public static void main(String[] args) {

        final String welcomeSentence = "Hello! I'm Duke\nWhat can I do for you?";
        final String endingSentence = "Bye. Hope to see you again soon!";


        System.out.println(showFormattedStr(welcomeSentence));

        try {
            readData();
        } catch (FileNotFoundException e) {
            new File(storagePath);
        }

        if (tasks.size() > 0) {
            try {
                conductCommand(Command.LIST);
            } catch (DukeException de) {
                System.out.println(showFormattedStr(de.getMessage()));
            }

        }

        scanner = new Scanner(System.in);
        String text = scanner.next();
        Command command;

        while (!text.substring(0, 3).equals("bye")) {
            try {
                command = readCommand(text);
                conductCommand(command);
            } catch (DukeException de) {
                System.out.println(showFormattedStr(de.getMessage()));
            }
            text = scanner.next();
        }

        try {
            writeData();
        } catch (IOException e) {
            System.out.println(showFormattedStr("☹ OOPS!!! We cannot store your data!"));
        }

        System.out.println(showFormattedStr(endingSentence));
    }

    private static String showFormattedStr(String str) {
        return separator + indentation + str.replace("\n", "\n" + indentation) + "\n" + separator;
    }

    private static String showFormattedList(List<Task> list) {
        String formattedList = separator;
        formattedList = formattedList + indentation + "Here are the tasks in your list:\n";
        if (list.size() == 0) {
            return showFormattedStr("Currently there is no task~");
        }
        for (int i = 1; i <= list.size(); i++) {
            formattedList = formattedList + indentation + i + ". " + list.get(i - 1) + "\n";
        }
        formattedList = formattedList + separator;
        return formattedList;
    }


    private static Command readCommand(String text) throws DukeException {
        Command command;

        try {
            command = Command.valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return command;
    }

    private static void conductCommand(Command command) throws DukeException {
        if (command == Command.LIST) {
            System.out.println(showFormattedList(tasks));
        } else if (command == Command.DONE) {
            Task doneTask = tasks.get(scanner.nextInt() - 1);
            doneTask.markDone();
            System.out.println(showFormattedStr("Nice! I've marked this task as done:\n" + "  " + doneTask));
        } else if (command == Command.DELETE) {
            Task removedTask = tasks.remove(scanner.nextInt() - 1);
            System.out.println(showFormattedStr("Noted. I've removed this task: \n" + "  " + removedTask
                    + "\nNow you have " + tasks.size() + " tasks in the list."));
        } else {
            Task newTask;
            String description = scanner.nextLine().trim();
            if (command == Command.TODO) {
                if (description.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                newTask = new Todo(description);
            } else {
                String[] details;
                if (command == Command.DEADLINE) {
                    details = description.split(" /by ");
                    if (details.length != 2) {
                        throw new DukeException ("☹ OOPS!!! The description of a deadline is not enough.");
                    }
                    newTask = new Deadline(details[0], LocalDateTime.parse(details[1],formatter));
                } else {
                    details = description.split(" /at ");
                    if (details.length != 2) {
                        throw new DukeException ("☹ OOPS!!! The description of a event is not enough.");
                    }
                    //format: /at 2/2/1999 12:00-14:00
                    String[] times = details[1].split("-");
                    newTask = new Event(details[0], LocalDateTime.parse(times[0],formatter), LocalTime.parse(times[1]));
                }
            }
            tasks.add(newTask);
            System.out.println(showFormattedStr(
                    "Got it. I've added this task:\n" + "  " + newTask
                            + "\nNow you have " + tasks.size() + " tasks in the list."));
        }

    }

    private static void readData() throws FileNotFoundException{
        scanner = new Scanner(new File(storagePath));
        while (scanner.hasNext()) {
            tasks.add(Task.from(scanner.nextLine()));
        }
    }

    private static void writeData() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath));
        String content = "";
        for (Task task : tasks) {
            if (task instanceof Todo) {
                content = "T|" + task.getStatus() +  "|" + task.getDescription();
            } else if (task instanceof Deadline) {
                Deadline dl = (Deadline) task;
                content = "D|" + task.getStatus() +  "|" + dl.getDescription() + "|" + dl.getDueDateTime();
            } else if (task instanceof Event) {
                Event e = (Event) task;
                content = "E|" + e.getStatus() + "|" + e.getDescription() + "|" + e.getDuration();
            }
            writer.append(content + "\n");
        }
        writer.close();
    }
}
