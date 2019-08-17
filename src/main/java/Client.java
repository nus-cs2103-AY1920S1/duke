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

    boolean read(String command, String message) {
        boolean shouldContinue = true;

        switch (command) {
        case "list":
            this.listTasks();
            break;
        case "bye":
            this.echoer.exit();
            shouldContinue = false;
            break;
        case "done":
            this.completeTask(Integer.parseInt(message.trim()));
            break;
        default:
            this.addTask(command + message);
        }
        return shouldContinue;
    }

    private void addTask(String description) {
        Task task = new Task(description);
        this.storage.add(task);
        this.echoer.echo("added: " + task.getDescription());
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
