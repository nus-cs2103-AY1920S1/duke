import java.io.IOException;

class Command {

    /**
     * constructor.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     * @throws DukeException when input is different from required.
     * @throws IOException when input is different from required.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException{

    }

    /**
     * whether is it an exit command
     * @return true when it is an exit command, false otherwise.
     */
    boolean isExit() {
        return (this instanceof ExitCommand);
    }
}

class AddCommand extends Command {
    /**
     * command to add a task.
     */
    private Task t;

    /**
     * constructor.
     * @param t is the task.
     */
    AddCommand(Task t) {
        this.t = t;
    }

    /**
     * add task and print out information.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     * @throws IOException when input is different from required.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(t);
        storage.save();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + t);
        System.out.println("      Now you have " + tasks.getSize() + " tasks in the list.");
    }
}

class DeleteCommand extends Command {
    /**
     * command to delete a task.
     */
    private int index;

    /**
     * constructor
     * @param index to know which position to delete.
     */
    DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * delete task and print out information.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     * @throws DukeException when input is different from required.
     * @throws IOException when input is different from required.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (index >= tasks.getSize()) {
            throw new NoTaskException();
        }
        Task t = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.save();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + t);
        System.out.println("      Now you have "  + tasks.getSize() + " tasks in the list.");
    }

}

class ExitCommand extends Command {

    /**
     * exit the bot and prints good bye message.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(
                "     Bye. Hope to see you again soon!");
    }
}

class ListCommand extends Command {

    /**
     * print out the list of tasks.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Here are the tasks in your list:");
        tasks.printList();
    }
}

class DoneCommand extends Command {
    /**
     * command to mark a task as done.
     */
    private int index;

    DoneCommand(int index) {
        this.index = index;
    }

    /**
     * mark a task as done and print out the information.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     * @throws DukeException when input is different from required.
     * @throws IOException when input is different from required.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (index >= tasks.getSize()) {
            throw new NoTaskException();
        }
        Task t = tasks.getTask(index);
        t.markAsDone();
        storage.save();
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + t);
    }
}

class FindCommand extends Command {
    private String keyword;
    FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * prints out the list of tasks with the keyword
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Here are the matching tasks in your list:");
        tasks.printListWithKeyword(keyword);
    }
}