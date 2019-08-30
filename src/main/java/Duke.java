/**
 * Represents a Duke object from which all process run from.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Creates a Duke object to run and mediate all other components.
     * @param path Is the path of the file to be read.
     */
    public Duke(String path) {
        this.ui = new UI();
        this.storage = new Storage(path);
        this.tasks = new TaskList(storage);
    }

    private void run() {
        ui.greet();
        String[] inputArr = storage.getInput().split("\\n");
        for (String s : inputArr) {
            String input = tasks.addTask(s);
            if (input.equals("bye")) {
                break;
            }
            ui.echo(input);
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\AngKa\\duke\\src\\main\\java\\test.txt").run();
    }
}