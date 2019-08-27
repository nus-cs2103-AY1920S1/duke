public class AddTodoCommand extends Command {

    String inputTodo = "";

    public void setInputTodo(String inputTodo) {
        this.inputTodo = inputTodo;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputTodo.trim().length() == 4) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            String todoDescription = inputTodo.substring(5);
            Todo t = new Todo(todoDescription);
            tasks.addTask(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            storage.updateChanges(tasks.getDukeTaskList());
        }
    }

}
