import java.io.InputStream;
import java.util.Scanner;

public class Duke {
    private static String divider = "    " + "-".repeat(61);

    private static void listData() {
        String[] taskStrings = new String[Task.totalNumOfTasks + 1];
        taskStrings[0] = "Here are the tasks in your list:";
        for (Task t : Task.taskList) {
            int id = t.getId();
            taskStrings[id] = String.format("%d.%s", id, t.toString());
        }
        dukeRespond(taskStrings);
    }
    private static void addData(String input) throws InvalidKeywordException,
            EmptyDescriptionException, IncorrectTaskFormatException {
        //get task keyword
        Scanner tmp = new Scanner(input);
        String kw = tmp.next();
        String descr = "";

        if (tmp.hasNext()) {
            descr = tmp.nextLine();
        }
        tmp.close();

        Task newTask;
        switch (kw.toLowerCase()) {
            case "deadline":
                newTask = Deadline.create(descr);
                break;
            case "event":
                newTask = Event.create(descr);
                break;
            case "todo":
                newTask = Todo.create(descr);
                break;
            default:
                //dukeRespond("Accidentally pressed enter?",
                //        "nvm you can continue with your next cmd! :D");
                //return;
                throw new InvalidKeywordException("");
        }
        dukeRespond("Got it. I've added this task:",
                "  " + newTask.toString(),
                String.format("Now you have %d task(s) in the list", Task.totalNumOfTasks));
    }
    private static void markDone(String cmd) throws InvalidIDException {
        String[] tmp = cmd.split(" ");
        int id = Integer.parseInt(tmp[1]);
        //ASSUMING VALID ID; THROW EXCEPTION AND HANDLE IF NEEDED LATER
        if (id > Task.totalNumOfTasks || id < 1) {
            //dukeRespond("Oof invalid id",
            //        "Wanna try again?");
            //return;
            throw new InvalidIDException(""+id);
        }

        Task task = Task.taskList.get(id - 1);
        task.setCompleted();
        dukeRespond("Nice! I've marked this task as done:",
                "  " + task.toString());
    }

    public static void dukeRespond(String... inputs) {
        System.out.println(divider);
        for (String str : inputs) {
            System.out.println("     " + str);
        }
        System.out.println(divider);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeRespond("Hello! I'm Duke", "What can I do for you?");

        //start listening for user input
        Scanner sc = new Scanner(System.in);
        String userCmd = sc.nextLine();

        while (!userCmd.equalsIgnoreCase("bye") &&
            !userCmd.equalsIgnoreCase("exit")) {

            try {
                if (userCmd.equalsIgnoreCase("list")) {
                    listData();
                } else if (userCmd.split(" ")[0].equalsIgnoreCase("done")) {
                    markDone(userCmd);
                } else {
                    addData(userCmd);
                }
            } catch (IllegalArgumentException e) {
                dukeRespond(e.toString());
            }

            userCmd = sc.nextLine();
        }
        dukeRespond("Bye. Hope to see you again soon!");

        //clear resources
        sc.close();
    }
}
