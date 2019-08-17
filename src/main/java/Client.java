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

    boolean read(String message) {
        boolean shouldContinue = true;
        switch (message) {
        case "list":
            this.listTasks();
            break;
        case "bye":
            this.echoer.exit();
            shouldContinue = false;
            break;
        default:
            this.addTask(message);
        }
        return shouldContinue;
    }

    private void addTask(String description) {
        Task task = new Task()
        this.storage.add(new Task(description));
        this.echoer.echo("added: " + task.getDescription());
    }

    private void listTasks() {
        String[] tasks = this.storage
                .getList()
                .stream()
                .map(Task::toString)
                .toArray(String[]::new);
        for (int ordering = 1; ordering <= tasks.length; ordering++) {
            tasks[ordering - 1] = ordering + ". " + tasks[ordering - 1];
        }
        this.echoer.echo(tasks);
    }


}
