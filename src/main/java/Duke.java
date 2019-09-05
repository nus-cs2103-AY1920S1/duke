import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    // create array of tasks
    public static ArrayList<Task> tasks = new ArrayList<>();
    // keep track of number of tasks
    public static int count = 0;

    public static void main(String[] args) {

        try {
            loadFileContents();
        } catch (FileNotFoundException e) {
            sendLine();
            System.out.println("File not found");
            sendLine();
        }

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        sendLine();
        sendGreet();
        sendLine();

        boolean isTerminated = false;

        while (!isTerminated) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    isTerminated = true;
                } else if (input.equals("list")) {
                    sendLine();
                    sendTasks();
                    sendLine();
                } else if (input.startsWith("done")) {
                    // to identify which task completed, split command at space
                    String[] splitStr = input.split(" ");
                    // if less than 2 words
                    if (splitStr.length < 2) {
                        throw new MissingTaskIndexException();
                    } else {
                        // take 2nd word
                        String itemIndex = splitStr[1];
                        sendLine();
                        doneTask(itemIndex);
                        sendLine();
                    }
                } else if (input.startsWith("todo")) {
                    // split command at first space
                    String[] splitStr = input.split(" ", 2);
                    // if less than 2 words
                    if (splitStr.length < 2) {
                        throw new MissingTodoException();
                    } else {
                        // take 2nd word onwards
                        String item = splitStr[1];
                        sendLine();
                        addTodo(item);
                        sendLine();
                    }
                } else if (input.startsWith("deadline")) {
                    // split command at first space
                    String[] splitStr = input.split(" ", 2);
                    // if less than 2 words
                    if (splitStr.length < 2) {
                        throw new MissingDeadlineException();
                    } else {
                        // take 2nd word onwards
                        String item = splitStr[1];
                        String[] data = item.split("/by", 2);
                        String task = data[0];
                        String deadline = data[1];
                        sendLine();
                        addDeadline(task, deadline);
                        sendLine();
                    }
                } else if (input.startsWith("event")) {
                    // split command at first space
                    String[] splitStr = input.split(" ", 2);
                    // if less than 2 words
                    if (splitStr.length < 2) {
                        throw new MissingEventException();
                    } else {
                        // take 2nd word onwards
                        String item = splitStr[1];
                        String[] data = item.split("/at", 2);
                        String task = data[0];
                        String time = data[1];
                        sendLine();
                        addEvent(task, time);
                        sendLine();
                    }
                } else if (input.startsWith("delete")) {
                    // to identify which task completed, split command at space
                    String[] splitStr = input.split(" ");
                    // if less than 2 words
                    if (splitStr.length < 2) {
                        throw new MissingTaskIndexException();
                    } else {
                        // take 2nd word
                        String itemIndex = splitStr[1];
                        sendLine();
                        deleteTask(itemIndex);
                        sendLine();
                    }
                } else {
                    throw new InvalidCommandException();
                }
            } catch (Exception error) {
                sendLine();
                sendMessage(error.toString());
                sendLine();
            }
        }

        sendLine();
        sendBye();
        sendLine();

        try {
            saveFileContents();
        } catch (IOException e) {
            sendLine();
            System.out.println("Unable to save data: " + e.getMessage());
            sendLine();
        }
    }

    public static void sendLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void sendGreet() {
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
    }

    public static void sendBye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void sendMessage(String input) {
        System.out.println("     " + input);
    }

    public static void addTodo(String item) {
        if (count >= 100) {
            sendMessage("You can add no more than 100 tasks!");
        } else {
            tasks.add(new Todo(item));
            Task thing = tasks.get(count);
            count ++;
            sendMessage("Got it. I've added this task: ");
            sendMessage("  " + thing.toString());
            sendMessage(String.format("Now you have %d tasks in the list.", count));
        }
    }

    public static void addDeadline(String task, String deadline) {
        if (count >= 100) {
            sendMessage("You can add no more than 100 tasks!");
        } else {
            DateTime dateTime = new DateTime(deadline);
            tasks.add(new Deadline(task, dateTime.toString()));
            Task thing = tasks.get(count);
            count ++;
            sendMessage("Got it. I've added this task: ");
            sendMessage("  " + thing.toString());
            sendMessage(String.format("Now you have %d tasks in the list.", count));
        }
    }

    public static void addEvent(String task, String time) {
        if (count >= 100) {
            sendMessage("You can add no more than 100 tasks!");
        } else {
            DateTime dateTime = new DateTime(time);
            tasks.add(new Event(task, dateTime.toString()));
            Task thing = tasks.get(count);
            count ++;
            sendMessage("Got it. I've added this task: ");
            sendMessage("  " + thing.toString());
            sendMessage(String.format("Now you have %d tasks in the list.", count));
        }
    }

    public static void sendTasks() {
        sendMessage("Here are the tasks in your list: ");
        for (int i = 0; i < count; i ++) {
            Task item = tasks.get(i);
            sendMessage((i + 1) + "." + item.toString());
        }
    }

    public static void doneTask(String itemIndex) {
        // convert string to int
        int index = Integer.parseInt(itemIndex) - 1;
        try {
            if (index < 0 || index >= count) {
                throw new InvalidIndexException();
            } else {
                Task item = tasks.get(index);
                // tick completed task
                item.setDone();
                sendMessage("Nice! I've marked this task as done: ");
                sendMessage("  " + item.toString());
            }
        } catch (Exception error) {
            sendMessage(error.toString());
        }
    }

    public static void deleteTask(String itemIndex) {
        // convert string to int
        int index = Integer.parseInt(itemIndex) - 1;
        try {
            if (index < 0 || index >= count) {
                throw new InvalidIndexException();
            } else {
                Task item = tasks.get(index);
                // delete task
                tasks.remove(index);
                count --;
                sendMessage("Noted. I've removed this task: ");
                sendMessage("  " + item.toString());
                sendMessage(String.format("Now you have %d tasks in the list.", count));
            }
        } catch (Exception error) {
            sendMessage(error.toString());
        }
    }

    private static void loadFileContents() throws FileNotFoundException {
        // create a file object
        File f = new File("data/duke.txt");
        Scanner load = new Scanner(f);
        while (load.hasNext()) {
            String item = load.nextLine();
            loadTask(item);
        }
    }

    public static void loadTask(String item) {
        if (item.startsWith("T")) {
            // split command into 3 parts
            String[] splitStr = item.split(" \\| ", 3);
            tasks.add(new Todo(splitStr[2]));
            if (splitStr[1].equals("1")) {
                tasks.get(count).setDone();
            }
            count ++;
        } else if (item.startsWith("D")) {
            // split command into 4 parts
            String[] splitStr = item.split(" \\| ", 4);
            tasks.add(new Deadline(splitStr[2], splitStr[3]));
            if (splitStr[1].equals("1")) {
                tasks.get(count).setDone();
            }
            count ++;
        } else if (item.startsWith("E")) {
            // split command into 4 parts
            String[] splitStr = item.split(" \\| ", 4);
            tasks.add(new Event(splitStr[2], splitStr[3]));
            if (splitStr[1].equals("1")) {
                tasks.get(count).setDone();
            }
            count ++;
        }
    }

    private static void saveFileContents() throws IOException {
        FileWriter save = new FileWriter("data/duke.txt");
        for (int i = 0; i < tasks.size(); i++) {
            save.write(tasks.get(i).saveTask());
        }
        save.close();
    }

}
