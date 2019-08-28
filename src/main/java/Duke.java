import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private TaskList tasks;
    private UI ui;
    private String filePath;

    public Duke(String filePath){
        this.ui = new UI();
        this.filePath = filePath;
        this.tasks = new TaskList();
    }

    public void run(){
        ui.showWelcome();
        String command = ui.readCommand();
        new Parser().parse(command, ui, tasks, filePath);
        ui.showGoodByeMessage();
    }

    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        new Duke("C:\\duke\\data\\tasklist.txt").run();
    }
}