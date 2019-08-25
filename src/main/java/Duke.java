import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /*
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    */

    private ArrayList<Task> store = new ArrayList<>();
    private static boolean flag = true;
    private static String filePath = "/Users/auxin/duke/data/Tasks.txt";

    private void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private void createFolder() {
        String folderPath = "/Users/auxin/duke/data";
        File newFolder = new File(folderPath);
        if (newFolder.mkdir()) {
            System.out.println("Folder is created.");
        }
    }

    private void createFile() {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File is created to save tasks.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendToFile(String filePath, String textToAppend, boolean flag) {
        try {
            FileWriter fw = new FileWriter(filePath, flag);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(textToAppend + "\n");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String currLine;
            while ((currLine = br.readLine()) != null) {
                String[] arr = currLine.split(" - ");
                String type = arr[0];
                String isDone = arr[1];
                String action = arr[2];
                switch(type) {
                    case "T":
                        Task todo = new Todo(action);
                        if (isDone.equals("\u2713")) {
                            todo.markAsDone();
                        }
                        store.add(todo);
                        break;
                    case "D":
                        String by = arr[3];
                        Task deadline = new Deadline(action, by);
                        if (isDone.equals("\u2713")) {
                            deadline.markAsDone();
                        }
                        store.add(deadline);
                        break;
                    case "E":
                        String at = arr[3];
                        Task event = new Event(action, at);
                        if (isDone.equals("\u2713")) {
                            event.markAsDone();
                        }
                        store.add(event);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void run() throws DukeIllegalDescriptionException, DukeIllegalInputException {

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String cmd = sc.nextLine(); //Read the command
            String[] head = cmd.split(" ", 2); /* Break the command into array with two parts */
            try {
                switch (Command.valueOf(head[0])) { //Read the first key word
                    case bye:
                        System.out.println("Bye. Hope to see you again soon!");
                        flag = false;
                        break;
                    case list:
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < store.size(); ++i) {
                            System.out.println(i + 1 + "." + store.get(i));
                        }
                        break;
                    case done:
                        int num = Integer.parseInt(head[1]);
                        Task newTask = store.get(num - 1);
                        newTask.markAsDone();
                        store.set(num - 1, newTask);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(store.get(num - 1));
                        boolean isAppend = false;
                        for (Task task : store) {
                            appendToFile(filePath, task.toString(), isAppend);
                            if (!isAppend) {
                                isAppend = true;
                            }
                        }
                        break;
                    case todo:
                        try {
                            Task todo = new Todo(head[1]);
                            store.add(todo);
                            printAddTask();
                            System.out.println(todo);
                            printCountTasks();
                            appendToFile(filePath, todo.toString(), true);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeIllegalDescriptionException(head[0]);
                        }
                        break;
                    case deadline:
                        try {
                            String[] dl = head[1].split(" /by ");
                            Task deadline = new Deadline(dl[0], dl[1]);
                            store.add(deadline);
                            printAddTask();
                            System.out.println(deadline);
                            printCountTasks();
                            appendToFile(filePath, deadline.toString(), true);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeIllegalDescriptionException(head[0]);
                        }
                        break;
                    case event:
                        try {
                            String[] ev = head[1].split(" /at ");
                            Task event = new Event(ev[0], ev[1]);
                            store.add(event);
                            printAddTask();
                            System.out.println(event);
                            printCountTasks();
                            appendToFile(filePath, event.toString(), true);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeIllegalDescriptionException(head[0]);
                        }
                        break;
                    case delete:
                        int delNum = Integer.parseInt(head[1]) - 1;
                        Task delTask = store.get(delNum);
                        store.remove(delNum);
                        System.out.println("Noted. I've removed this task:\n" + delTask.toString());
                        printCountTasks();
                        boolean isAppendDel = false;
                        for (Task task : store) {
                            appendToFile(filePath, task.toString(), isAppendDel);
                            if (!isAppendDel) {
                                isAppendDel = true;
                            }
                        }
                        break;
                }
                if (!flag) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                throw new DukeIllegalInputException();
            }
        }
        sc.close();
    }

    private void printAddTask() {
        System.out.println("Got it. I've added this task:");
    }

    private void printCountTasks() {
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    private void startDuke() {
        greet();
        createFolder();
        createFile();
        readFile();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startDuke();
        while (flag) {
            try {
                duke.run();
            } catch (DukeIllegalInputException | DukeIllegalDescriptionException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

enum Command {
    bye, list, done, todo, deadline, event, delete
}
