import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    DukeReadFile rf;
    DukeWriteFile wf;
    Ui ui;
    TaskList tasks;
    Parser userCommand;

    public Duke (String filePath) {
        ui = new Ui();
        rf = new DukeReadFile(filePath);
        wf = new DukeWriteFile(filePath);
        try {
            rf.readFileContent();
            tasks = new TaskList(rf.myTask());

        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            System.out.println("File not found");
        }
    }

    public void run() {
        System.out.println(ui.INTRO);
        boolean isExit = false;
        userCommand = new Parser(ui, tasks, rf, wf);
        while (!isExit) {
            String input = ui.getInput();
            userCommand.evaluate(input);
            isExit = userCommand.isExit();

        }
    }
    public static void main(String[] args) {

        new Duke("data/duke.txt").run();
    }

    public static String writeFile(ArrayList<Task> currentTask) {
        String writeTask = "";

        for(int i = 0; i < currentTask.size(); i++) {

            if (currentTask.get(i) instanceof Todo) {
                writeTask += "T/" + currentTask.get(i).getStatus() +
                        "/" + currentTask.get(i).getDescription() + "\n";
            } else if (currentTask.get(i) instanceof Event) {
                writeTask += "E/" + currentTask.get(i).getStatus() +
                        "/" + currentTask.get(i).getDescription() + "/" + ((Event) currentTask.get(i)).getVenue() + "\n";
            } else {
                writeTask += "D/" + currentTask.get(i).getStatus() +
                        "/" + currentTask.get(i).getDescription() + "/" + ((Deadline) currentTask.get(i)).getDeadline() + "\n";
            }
        }

        return writeTask;
    }
}
