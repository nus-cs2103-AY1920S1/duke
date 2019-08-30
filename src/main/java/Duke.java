import java.text.ParseException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    private void done(int num) {
        try {
            tasks.done(num);
            storage.write(tasks);
            ui.done(tasks, num);
        } catch (DukeException ex) {
            ui.showDukeException(ex);
        } catch (ParseException e) {
            ui.showParseError();
        }
    }

    private void delete(int num) {
        try {
            tasks.delete(num);
            storage.write(tasks);
            ui.delete(tasks, num);
        } catch (DukeException ex) {
            ui.showDukeException(ex);
        } catch (ParseException e) {
            ui.showParseError();
        }
    }

    private void add(String type, String description) {
        try{
            tasks.add(type, description);
            storage.write(tasks);
            ui.add(tasks);
        } catch (DukeException ex) {
            ui.showDukeException(ex);
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        ui.greet();
        while (true) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parse(input);
                if (command instanceof Bye) {
                    ui.bye();
                    break;
                } else if (command instanceof PrintList) {
                    ui.printList(tasks);
                } else if (command instanceof Done) {
                    Done done = (Done) command;
                    done(done.number);
                } else if (command instanceof Delete) {
                    Delete delete = (Delete) command;
                    delete(delete.number);
                } else if (command instanceof Add) {
                    Add add = (Add) command;
                    add(add.type, add.description);
                } else if (command instanceof  Find) {
                    Find find = (Find) command;
                    TaskList targets = tasks.find(find.target);
                    ui.printFind(targets);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                ui.showDukeException(ex);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}