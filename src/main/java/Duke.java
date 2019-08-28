import java.util.Scanner;

public class Duke {
    private Storage storage; //handles storage: file read file write
    private TaskList tasks; //aka has list of tasks + task handling
    private Ui ui; //handles i/o (ui) printing inputs etc for duke

    public Duke(String dataFilepath) {
        this.ui = new Ui();
        this.storage = new Storage(dataFilepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        //start listening for user input
        Scanner sc = new Scanner(System.in);
        String userCmd = sc.nextLine();

        boolean resaveData = false;

        while (!userCmd.equalsIgnoreCase("bye") &&
                !userCmd.equalsIgnoreCase("exit")) {
            try {
                switch (userCmd.split("\\s+")[0].toLowerCase()) {
                    case "list":
                        this.tasks.listTasks(this.ui);
                        break;
                    case "done":
                        this.tasks.markDone(this.ui, userCmd);
                        resaveData = true;
                        break;
                    case "delete":
                        this.tasks.deleteTask(this.ui, userCmd);
                        resaveData = true;
                        break;
                    default:
                        this.tasks.addData(this.ui, userCmd);
                        resaveData = true;
                        break;
                }

                if (resaveData) {
                    this.storage.updateFile(this.tasks);
                }
                //}
            } catch (IllegalArgumentException e) {
                this.ui.dukeRespond(e.toString());
            }

            userCmd = sc.nextLine();
        }
        this.ui.dukeRespond("Bye. Hope to see you again soon!");

        //clear resources
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
