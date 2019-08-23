import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Client {
    private static Client client = null;
    private TaskList taskList;
    private Echoer echoer;
    private ExceptionHandler exceptionHandler;


    private Client() {
        this.taskList = new TaskList();
        this.echoer = new Echoer();
        this.exceptionHandler = new ExceptionHandler(this.echoer);
        this.echoer.greet();
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
            case "delete":
                this.deleteTask(Integer.parseInt(description));
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException | EmptyDescriptionException e) {
            this.exceptionHandler.handleKnownException(e);
        } catch (Exception e) {
            this.exceptionHandler.handleUnknownException(e);
        } finally {
            return shouldContinue;
        }
    }

    private String[] getActivityAndDatetime(String description) {
        if (description.isEmpty()) {
            return new String[]{"", ""};
        }
        String[] toEdit = description.split("/");
        String activity = toEdit[0].trim();
        toEdit = toEdit[1].split(" ");
        toEdit[0] = "";
        String datetime = Arrays.stream(toEdit).reduce("", (x, y) -> x + " " + y).trim();
        return new String[]{activity, datetime};
    }

    private void addTask(String command, String description) throws EmptyDescriptionException {
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

        this.taskList.add(task);
        int numOfTasks = this.taskList.getSize();
        this.echoer.echo("Got it. I've added this task:"
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
        this.echoer.echo(tasks.toArray(new String[0]));
    }

    private void completeTask(int ordering) {
        Task task = this.taskList.getList().get(ordering - 1);
        task.markAsDone();
        this.echoer.echo("Nice! I've marked this task as done:", "  " + task);
    }

    private void deleteTask(int ordering) {
        List<Task> taskList = this.taskList.getList();
        Task task = taskList.get(ordering - 1);
        taskList.remove(ordering - 1);
        int numOfTasks = this.taskList.getSize();
        this.echoer.echo("Noted. I've removed this task:"
                , "  " + task
                , String.format("Now you have %d task%s in the list.", numOfTasks, numOfTasks == 1 ? "" : "s"));
    }
}
