public class AddTodoCommand extends Command {

    public AddTodoCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        String[] inputMessage = input.split(" ");
        if (inputMessage.length == 0) {
            throw new DukeException("     OOPS!! The description of a todo cannot be empty");
        }
        String item = "";
        for (int i = 0; i < inputMessage.length; i++) {
            if (i == inputMessage.length - 1) {
                item += inputMessage[i];
            } else {
                item += inputMessage[i];
                item += " ";
            }
        }
        Todo newTodo = new Todo(item);
        listOfTasks.addTodo(newTodo);
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
        ui.printTaskAdd(newTodo);
    }
}
