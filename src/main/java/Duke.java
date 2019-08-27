public class Duke {
    private UI ui;
    private TaskList tasks;

    public Duke(String path){
        ui = new UI();
        Storage.setPath(path);
        try {
            tasks = new TaskList(Storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run () {
        String logo = " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ui.run();
    }

    public static void main(String[] args) {
        new Duke("tasks.dmp").run();
    }
}
