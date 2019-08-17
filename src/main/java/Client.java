import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Client {
    private Storage storage;
    private Echoer echoer;
    private static Client client = null;

    private Client() {
        this.storage = new Storage();
        this.echoer = new Echoer();
        this.echoer.greet();
    }

    static Client initialise() {
        if (client != null) {
            System.out.println("Client has already been initialised");
        } else {
            client = new Client();
        }
        return client;
    }

    boolean read(String command, String description) {
        boolean shouldContinue = true;

        switch (command) {
        case "todo":
        case "deadline":
        case "event":
            this.addTask(command, description);
            break;
        case "list":
            this.listTasks();
            break;
        case "bye":
            this.echoer.exit();
            shouldContinue = false;
            break;
        case "done":
            this.completeTask(Integer.parseInt(description));
            break;
        default:
            System.err.println("Command is not valid");
        }
        return shouldContinue;
    }

    private String[] getActivityAndDatetime(String description) {
        String[] toEdit = description.split("/");
        String activity = toEdit[0].trim();
        toEdit = toEdit[1].split(" ");
        toEdit[0] = "";
        String datetime = Arrays.stream(toEdit).reduce("", (x, y) -> x + " " + y).trim();
        String[] activityAndDatetime = {activity, datetime};
        return activityAndDatetime;
    }

    private void addTask(String command, String description) {
        Task task;

        if (command.equals("todo")) {
            task = new ToDo(description);
        } else {
            String[] activityAndDatetime = this.getActivityAndDatetime(description);
            String activity = activityAndDatetime[0];
            String datetime = activityAndDatetime[1];

            if (command.equals("deadline")) {
                task = new Deadline(activity, datetime);
            } else {
                task = new Event(activity, datetime);
            }
        }

        this.storage.add(task);
        int numOfTasks = this.storage.getSize();
        this.echoer.echo("Got it. I've added this task:"
                , "  " + task.toString()
                , String.format("Now you have %d task%s in the list.", numOfTasks, numOfTasks == 1 ? "" : "s"));
    }

    private void listTasks() {
        List<String> tasks = this.storage
                .getList()
                .stream()
                .map(Task::toString)
                .collect(Collectors.toList());
        for (int ordering = 1; ordering <= tasks.size(); ordering++) {
            tasks.set(ordering - 1, ordering + "." + tasks.get(ordering - 1));
        }
        tasks.add(0, "Here are the tasks in your list:");
        this.echoer.echo(tasks.toArray(new String[0]));
    }

    private void completeTask(int ordering) {
        Task task = this.storage.getList().get(ordering-1);
        task.markAsDone();
        this.echoer.echo("Nice! I've marked this task as done:", "  " + task.toString());
    }
}
