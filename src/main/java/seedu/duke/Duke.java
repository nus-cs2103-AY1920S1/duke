package seedu.duke;

import java.util.Scanner;

import seedu.duke.command.Command;
import seedu.duke.task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui(new Scanner(System.in));
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
                continue;
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

// import java.util.Scanner;
// import java.time.format.DateTimeParseException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Arrays;
// import java.util.NoSuchElementException;

// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.BufferedWriter;
// import java.io.FileNotFoundException;

// public class Duke {
// public static void main(String[] args) {
// // System.out.println("Hello from\n" + logo);
// Duke d = new Duke();
// d.loadTasks();
// d.greeting();
// d.listen();
// }

// private static final String SAVE_LOCATION = new String("data.txt");

// private Duke() {}

// private void greeting() {
// String logo =
// " ____ _ \n"
// + "| _ \\ _ _| | _____ \n"
// + "| | | | | | | |/ / _ \\\n"
// + "| |_| | |_| | < __/\n"
// + "|____/ \\__,_|_|\\_\\___|\n";
// System.out.println(logo);
// System.out.println("Hello! I'm Duke");
// }

// private ArrayList<Task> cache = new ArrayList<>();

// private void listen() {
// Scanner listener = new Scanner(System.in);
// while(listener.hasNext()) {
// String line = listener.nextLine();
// if (line.equals("bye")) {
// System.out.println("Bye. Hope to see you again!");
// saveTasks();
// break;
// } else if (line.equals("list")) {
// showList();
// } else {
// handleTask(line);
// }
// saveTasks();
// }
// listener.close();
// }

// private void loadTasks() {
// // load tasks from SAVE_LOCATION into the arraylist of tasks, cache.
// try {
// Scanner sc = new Scanner(new FileReader(SAVE_LOCATION));
// while (sc.hasNext()) {
// System.out.println("reading...");
// try {
// Task t = parseTask(sc.nextLine());
// cache.add(t);
// } catch (InvalidFormatException e) {
// System.out.println(e.message);
// }
// }
// sc.close();
// } catch (FileNotFoundException e) {
// System.out.println("Wow. First time to Duke?");
// return;
// }
// System.out.println(cache);
// }

// private Task parseTask(String line) throws InvalidFormatException {
// // format: " [T|D|E] | [0|1] | desc | [by|at] "
// String[] parts = line.split("\\|");
// for(int i=0; i< parts.length; i++) {
// parts[i] = parts[i].trim();
// }
// System.out.printf("length of splitted is %d \n", parts.length);
// if (parts.length < 3 || parts.length > 4) throw new
// InvalidFormatException(line);
// String type = parts[0];
// boolean done = parts[1].equals("1");
// String desc = parts[2];
// Task t = new Task(done, desc);
// switch (type) {
// case "T":
// t = new ToDo(done, desc);
// break;
// case "D":
// t = new Deadline(done, desc, parts[3]);
// break;
// case "E":
// t = new Event(done, desc, parts[3]);
// }
// return t;
// }

// private void saveTasks() {
// // horrible design: rewrite the file at SAVE_LOCATION with the cache
// try {
// FileWriter fw = new FileWriter(SAVE_LOCATION);
// BufferedWriter bw = new BufferedWriter(fw);

// for (Task t: cache) {
// bw.write(t.saveFormat());
// }

// bw.close();
// } catch (IOException e) {
// System.out.println("Oh no!! Couldn't save for some reason.");
// e.printStackTrace();
// }

// }

// private void handleTask(String line) {
// // something else: help message
// // done <number>
// // todo <desc>
// // deadline <desc> /by <time>
// // event desc /at <time>

// Scanner wordReader = new Scanner(line);
// String command = wordReader.next();
// try{
// switch (command) {
// case "done":
// handleDone(wordReader.nextLine());
// break;
// case "todo":
// handleToDo(wordReader.nextLine());
// break;
// case "deadline":
// handleDeadline(wordReader.nextLine());
// break;
// case "event":
// handleEvent(wordReader.nextLine());
// break;
// case "delete":
// handleDelete(wordReader.nextLine());
// break;
// default:
// printHelpMessage();
// break;
// }
// } catch (NoSuchElementException e) {
// System.out.println("A command must be followed by one or more arguments! Type
// 'help' for more help");
// banner();
// }
// }

// private void printHelpMessage() {
// System.out.println("Available commands:\n" +
// "event <desc> /at <time>\n" +
// "todo <desc>\n" +
// "deadline <desc> /by <time>\n" +
// "list\n" +
// "delete <number>\n" +
// "done <number>\n");
// }

// private void handleDone(String number) {
// // parse the number, mark as done
// try {
// int n = Integer.parseInt(number.trim());
// Task t = cache.get(n-1).markAsDone();
// System.out.println("Nice! I've marked this task as done:");
// System.out.println(t);
// banner();
// } catch (NullPointerException e) {
// System.out.println("Hey bro why no number?");
// banner();
// } catch (Exception e) {
// System.out.println(e);
// System.out.println("The format is: 'done <number>'. Please try again.");
// banner();
// }
// }

// private void handleDelete(String number) {
// // parse the number, mark as done
// try {
// int n = Integer.parseInt(number.trim());
// Task t = cache.remove(n-1);
// System.out.println("Noted. I've removed this task:");
// System.out.println(t);
// System.out.printf("Now you have %d tasks in the list.\n", cache.size());
// banner();
// } catch (NullPointerException e) {
// System.out.println("You probably gave a number outside of the list.");
// banner();
// } catch (Exception e) {
// System.out.println(e);
// System.out.println("The format is: 'delete <number>'. Please try again.");
// banner();
// }
// }

// private void handleEvent(String descAt) {
// try {
// // separate by /at
// String[] splitted = descAt.split("/at");
// if (splitted.length < 2) {
// System.out.println("Please enter a non-empty description and time, in the
// following format:");
// System.out.println("event <desc> /at <time>");
// banner();
// return;
// }

// String desc = splitted[0];
// List<String> restAsList = Arrays.asList(
// Arrays.copyOfRange(splitted, 1, splitted.length));
// String at = String.join("/at", restAsList);

// Event e = new Event(desc, at);
// cache.add(e);
// printAddConfirmation();
// } catch (DateTimeParseException e) {
// System.out.println(e);
// System.out.println("The datetime format is wrong! Try 'dd/mm/yyyy HHMM' in
// 24-hour format.");
// }
// }

// private void handleDeadline(String descBy) {
// try {
// // separate by /by
// String[] splitted = descBy.split("/by");
// if (splitted.length < 2) {
// System.out.println("Please enter a non-empty description and time, in the
// following format:");
// System.out.println("deadline <desc> /by <time>");
// banner();
// return;
// }

// String desc = splitted[0];
// List<String> restAsList = Arrays.asList(
// Arrays.copyOfRange(splitted, 1, splitted.length));
// String by = String.join("/by", restAsList);

// Deadline e = new Deadline(desc, by);
// cache.add(e);
// printAddConfirmation();
// } catch (DateTimeParseException e) {
// System.out.println("The datetime format is wrong! Try 'dd/mm/yyyy HHMM' in
// 24-hour format.");
// }
// }

// private void handleToDo(String desc) {
// cache.add(new ToDo(desc));
// printAddConfirmation();
// }

// private void printAddConfirmation() {
// System.out.println("Got It. I've added this task:");
// System.out.println(cache.get(cache.size()-1)); // last object
// System.out.printf("Now you have %d tasks in the list.\n", cache.size());
// banner();
// }

// private void showList() {
// for (int i=0; i<cache.size(); i++) {
// Task t = cache.get(i);
// System.out.printf("%d.%s\n", i+1, t);
// }
// banner();
// }

// private void banner() {
// System.out.println();
// }
// }
