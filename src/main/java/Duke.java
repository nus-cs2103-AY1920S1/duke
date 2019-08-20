import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        int counter = 0;

        greeting();

        while(sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");
            // String taskName = commandArr[1] + commandArr[2];

            switch(commandArr[0].toLowerCase()) {
                case "event":
                    String name = "";
                    String time = "";
                    boolean flag = false;
                    for (int i = 1; i < commandArr.length; i++ ) {
                        if (commandArr[i].equals("/at")) {
                            flag = true;
                        } else if (flag) {
                            time += commandArr[i] + " ";
                        } else {
                            name += commandArr[i] + " ";
                        }
                    }
                    handleEvent(name.trim(), time.trim(), counter);
                    counter++;
                    break;

                case "list":
                    printList(counter);
                    break;

                case "done":
                    int taskId = Integer.parseInt(commandArr[1]);
                    handleDone(taskId);
                    break;

                case "bye":
                    handleExit();
                    return;
                
                default:
                    break;
            }
        }

        sc.close();
    }

    public static void addBorder(String input) {

        String border = "____________________________________________________________";

        System.out.println(border + "\n\n" + input + "\n" + border + "\n");
    }

    public static void greeting() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /* Takes the task name and index to add to the arr */
    public static void addTask(int index, Task task) {
        tasks[index] = task;
        addBorder(task.toString());
    }

    public static void handleEvent(String name, String time, int index) {
        addTask(index, new Event(name, time));
    }

    public static void handleDone(int taskId) {
        Task doneTask = tasks[taskId - 1];
        doneTask.markAsDone();
        String str = "Nice! I've marked this task as done:\n" 
            + " " + doneTask.toString();

        addBorder(str);
        
    }

    public static void handleExit() {
        addBorder("Bye. Hope to see you again soon!");
    }

    public static void printList(int index) {
        String str = "Here are the tasks in your list:\n";

        for (int i = 1; i < index + 1; i++) {
            if (i == index) {
                str += i + "." + tasks[i-1];
            } else {
                str += i + "." + tasks[i-1] + "\n";
            }
        }

        addBorder(str);
    } 

}
