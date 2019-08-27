import java.util.List;

public class UI {

   // Command command;
    Parser parser = new Parser();
    TaskList tasks = new TaskList();
    boolean isExit = false;
    String input;

    public UI(String s) {
        input = s;
    }

    public void exit() {
        isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }

   public Command processInput() {
       return parser.process(input);
   }

    public void processCommand(Command command) {
        try {
        switch (command.type) {
            case EXIT:
                exit();
                break;
            case PRINTLIST:
                tasks.printList();
                break;
            case ADD:
                tasks.addTask(parser.createTask(command.command));
                break;
            case DELETE:
                tasks.removeTask(parser.getTaskNo(command.command));
                break;
            case DONE:
                tasks.setDone(parser.getTaskNo(command.command));
                break;
            default:
                throw new InvalidCommandException();
        }
    } catch(InvalidCommandException e){
                e.printError();
            }
        }
    }
