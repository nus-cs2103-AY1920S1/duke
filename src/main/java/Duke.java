import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
       new Duke("./saved/taskList_history.txt").run();
    }

    public Duke(String path) {
        storage = new Storage(path);
        ui = new Ui();
        try {
            if (!storage.historyExists()) {
                storage.createFile();
                tasks = new TaskList(new ArrayList<>());
            } else {
                tasks = new TaskList(storage.retrieveHistory());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
            while (true) {
                try {
                    String input = sc.nextLine();
                    String[] inputParts = input.split(" ", 2);
                    String command = inputParts[0];
                    if (command.equals("bye")) {
                        storage.saveHistory(tasks.getTaskList());
                        ui.byeMessage();
                        break;
                    } else if (command.equals("list")) {
                        ui.drawLine();
                        tasks.displayList();
                        ui.drawLineNewLine();
                    } else if (command.equals("done")) {
                        tasks.markItemComplete(Integer.parseInt(inputParts[1]), ui);
                    } else if (command.equals("delete")) {
                        tasks.deleteItem(Integer.parseInt(inputParts[1]), ui);
                    } else {
                        tasks.registerNewTask(inputParts, ui);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DukeException e) {
                    ui.printErrorMessage(e.getMessage());
                }
            }
        sc.close();
    }

}
