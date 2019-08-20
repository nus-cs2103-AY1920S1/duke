import java.io.InputStream;
import java.util.Scanner;

public class Duke {
    private static String divider = "    " + "-".repeat(61);

    private static void listData() {
        String[] taskStrings = new String[Task.totalNumOfTasks + 1];
        taskStrings[0] = "Here are the tasks in your list:";
        int listIndex = 0;
        for (Task t : Task.taskList) {
            listIndex++;
            //int id = t.getId();
            //ASSUMPTION: ok fine need to at least print new id, if i dont wanna change the init id
            taskStrings[listIndex] = String.format("%d.%s", listIndex, t.toString());
        }
        dukeRespond(taskStrings);
    }
    private static void addData(String input) throws InvalidKeywordException,
            EmptyDescriptionException, IncorrectTaskFormatException {
        if (input.equals("")) {
            dukeRespond("Did you want to say something? Cus you didn't type anything haha");
            return;
        }
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
                String.format("Now you have %d tasks in the list", Task.totalNumOfTasks));
    }
    private static void markDone(String cmd) throws InvalidIDException, NoIDGivenException {
        String[] tmp = cmd.split(" ");

        if (tmp.length < 2)
            throw new NoIDGivenException("done");

        int id;
        try {
            id = Integer.parseInt(tmp[1]);
        } catch (NumberFormatException e) {
            throw new InvalidIDException(tmp[1]);
        }

        if (id > Task.totalNumOfTasks || id < 1) {
            throw new InvalidIDException(""+id);
        }

        Task task = Task.taskList.get(id - 1);
        task.setCompleted();
        dukeRespond("Nice! I've marked this task as done:",
                "  " + task.toString());
    }
    private static void deleteTask(String cmd) {
        String[] tmp = cmd.split(" ");

        if (tmp.length < 2)
            throw new NoIDGivenException("delete");

        int id;
        try {
            id = Integer.parseInt(tmp[1]);
        } catch (NumberFormatException e) {
            throw new InvalidIDException(tmp[1]);
        }

        if (id > Task.totalNumOfTasks || id < 1) {
            throw new InvalidIDException(""+id);
        }

        Task task = Task.taskList.remove(id - 1);
        Task.totalNumOfTasks--;
        dukeRespond("Noted. I've removed this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", Task.totalNumOfTasks));
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
                //if (userCmd.equalsIgnoreCase("list")) {
                //    listData();
                //} else{
                    switch (userCmd.split(" ")[0].toLowerCase()) {
                        case "list":
                            listData();
                            break;
                        case "done":
                            markDone(userCmd);
                            break;
                        case "delete":
                            deleteTask(userCmd);
                            break;
                        default:
                            addData(userCmd);
                            break;
                    }

                //}
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
