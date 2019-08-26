import java.util.List;

public class Parser {
    List<String> availableCommands;

    public Parser(List availableCommands)  {
        this.availableCommands = availableCommands;
    }

    public void parseCommand (UI ui, Storage storage, String command, TaskList taskList) throws DukeException {
        if (!availableCommands.contains(command.split(" ")[0])) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } else if (command.equals("bye")) {
            ui.exit();
        } else if (command.equals("list")) {
            ui.printList(taskList);
        } else if (command.startsWith("done")) {
            String[] words = command.split("\\s");
            int index = Integer.parseInt(words[1]);
            taskList.doneTask(index);
            storage.saveTask();
        } else if (command.startsWith("delete")){
            String[] words = command.split("\\s");
            int index = Integer.parseInt(words[1]);
            taskList.deleteTask(index);
            storage.saveTask();
        } else {
            taskList.addTask(command);
            storage.saveTask();
        }
    }


}
