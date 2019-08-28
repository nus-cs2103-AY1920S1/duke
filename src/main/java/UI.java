import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UI {

    Parser parser = new Parser();
    TaskList tasks = new TaskList();
    boolean isExit = false;
    String fileInput;
    private Storage storage;
    List<Command> commands = new ArrayList<>();

    public UI(String fileInput) {
        storage = new Storage(fileInput);
    }

    public void exit() {
        isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }

    public void processInput() {
        List<String> input = storage.processInput();
        for (String line : input) {
            commands.add(parser.process(line));
        }
    }

    public void processCommand() {
        loop: for (Command command : commands) {
            System.out.println(command.command);
            try {
                switch (command.type) {
                    case EXIT:
                        exit();
                        break loop;
                    case PRINTLIST:
                        tasks.printList();
                        break;
                    case ADD:
                        tasks.addTask(parser.createTask(command.command));
                        break;
                    case DELETE:
                        tasks.deleteTask(parser.getTaskNo(command.command));
                        break;
                    case DONE:
                        tasks.setDone(parser.getTaskNo(command.command));
                        break;
                    default:
                        throw new InvalidCommandException();
                }
                for (String task: tasks.listify()) {
                    storage.writeToFile(task);
                }
            } catch (InvalidCommandException | IOException e) {
                System.out.println("Something went wrong");
            }
        }
    }
}
