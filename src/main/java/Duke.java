import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private String LINES = "\n" + "____________________________________________________________" + "\n";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        new Duke("data/task.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
            finally {
                ui.showLine();
            }
        }
    }

    private void done(String s, String[] strArr) {
        try {
            if (s.length() < 6) {
                throw new DukeException("Please write in this format: done X\nWhere X is a number in the list");
            }
            int pos = Integer.parseInt(strArr[1]) - 1;
            Task doneTask = ((Task) tasks.list.get(pos)).markAsDone();
            System.out.println(display("Nice! I've marked this task as done:\n"
                            + doneTask));

        } catch (DukeException e) {
            System.out.print(display(e.getMessage()));
        } catch (IndexOutOfBoundsException e) {
            System.out.print(display("Please input a number that is within the list"));
        }
    }

    private String display(String text) {
        return LINES + text + LINES;
    }
}
