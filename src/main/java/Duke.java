import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // create array of tasks
    public static ArrayList<Task> tasks = new ArrayList<>();
    // keep track of number of tasks
    public static int count = 0;

    public static void main(String[] args) {

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
                        sendLine();
                        sendMessage("Please indicate task index!");
                        sendLine();
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
                        sendLine();
                        sendMessage("Please indicate task to do with deadline!");
                        sendLine();
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
                        sendLine();
                        sendMessage("Please indicate task to do with start and end time!");
                        sendLine();
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
                        sendLine();
                        sendMessage("Please indicate task index!");
                        sendLine();
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
            tasks.add(new Deadline(task, deadline));
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
            tasks.add(new Event(task, time));
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
        if (index < 0 || index >= count) {
            sendMessage("Invalid index!");
        } else {
            Task item = tasks.get(index);
            // tick completed task
            item.setDone();
            sendMessage("Nice! I've marked this task as done: ");
            sendMessage("  " + item.toString());
        }
    }

    public static void deleteTask(String itemIndex) {
        // convert string to int
        int index = Integer.parseInt(itemIndex) - 1;
        if (index < 0 || index >= count) {
            sendMessage("Invalid index!");
        } else {
            Task item = tasks.get(index);
            // delete task
            tasks.remove(index);
            count --;
            sendMessage("Noted. I've removed this task: ");
            sendMessage("  " + item.toString());
            sendMessage(String.format("Now you have %d tasks in the list.", count));
        }
    }

}
