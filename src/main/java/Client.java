import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Client {
    private static Client client = null;
    private TaskList taskList;
    private Storage storage;


    private Client() {
        this.taskList = new TaskList();
        this.storage = new Storage(this.taskList);
        Ui.greet();
    }

    static Client initialise() {
        if (client != null) {
            System.err.println("Client has already been initialised");
        } else {
            client = new Client();
        }
        return client;
    }

    boolean read(String command, String description) {
        boolean shouldContinue = true;
        try {
            switch (command) {
            case "todo":
            case "deadline":
            case "event":
                this.addTask(command, description);
                this.storage.taskListToFile();
                break;
            case "list":
                this.listTasks();
                break;
            case "bye":
                Ui.exit();
                shouldContinue = false;
                break;
            case "done":
                this.completeTask(Integer.parseInt(description));
                this.storage.taskListToFile();
                break;
            case "delete":
                this.deleteTask(Integer.parseInt(description));
                this.storage.taskListToFile();
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException | EmptyDescriptionException e) {
            ExceptionHandler.handleKnownException(e);
        } catch (Exception e) {
            ExceptionHandler.handleUnknownException(e);
        } finally {
            return shouldContinue;
        }
    }

    private String[] getActivityAndDateTime(String description) {
        if (description.isEmpty()) {
            return new String[]{"", ""};
        }
        String[] toEdit = description.split("/", 2);
        String activity = toEdit[0].trim();
        toEdit = toEdit[1].split(" ");
        toEdit[0] = "";
        String dateTime = Arrays.stream(toEdit).reduce("", (x, y) -> x + " " + y).trim();
        return new String[]{activity, dateTime};
    }

    private void addTask(String command, String description) throws EmptyDescriptionException {
        Task task;

        if (command.equals("todo")) {
            task = new ToDo(description);
        } else {
            String[] activityAndDateTime = this.getActivityAndDateTime(description);
            String activity = activityAndDateTime[0];
            String dateTime = activityAndDateTime[1];

            if (command.equals("deadline")) {
                task = new Deadline(activity, dateTime);
            } else {
                task = new Event(activity, dateTime);
            }
        }

        this.taskList.add(task);
        int numOfTasks = this.taskList.getSize();
        Ui.echo("Got it. I've added this task:"
                , "  " + task
                , String.format("Now you have %d task%s in the list.", numOfTasks, numOfTasks == 1 ? "" : "s"));
    }

    private void listTasks() {
        List<String> tasks = this.taskList
                .getList()
                .stream()
                .map(Task::toString)
                .collect(Collectors.toList());
        for (int ordering = 1; ordering <= tasks.size(); ordering++) {
            tasks.set(ordering - 1, ordering + "." + tasks.get(ordering - 1));
        }
        tasks.add(0, "Here are the tasks in your list:");
        Ui.echo(tasks.toArray(new String[0]));
    }

    private void completeTask(int ordering) {
        Task task = this.taskList.getList().get(ordering - 1);
        task.markAsDone();
        Ui.echo("Nice! I've marked this task as done:", "  " + task);
    }

    private void deleteTask(int ordering) {
        List<Task> taskList = this.taskList.getList();
        Task task = taskList.get(ordering - 1);
        taskList.remove(ordering - 1);
        int numOfTasks = this.taskList.getSize();
        Ui.echo("Noted. I've removed this task:"
                , "  " + task
                , String.format("Now you have %d task%s in the list.", numOfTasks, numOfTasks == 1 ? "" : "s"));
    }
}
