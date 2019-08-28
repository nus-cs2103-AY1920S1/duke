public class DoneCommand extends Command {

    public DoneCommand(String inputCommand){
        super(inputCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = this.inputCommand.split(" ", 2);
        if(inputsplit.length <= 1) {
            throw new DukeException("OOPS!!! The description of done must have a value.");
        } else if (Integer.parseInt(inputsplit[1]) > tasks.getSize() || Integer.parseInt(inputsplit[1]) <= 0 ){
            throw new DukeException("OOPS!!! Invalid value for task done!");
        } else if (tasks.getTask(Integer.parseInt(inputsplit[1])).getIsDone() == true) {
            throw new DukeException("OOPS!!! Task is already done!");
        } else {
                int num = Integer.parseInt(inputsplit[1]);
                Task t =  tasks.getTask(num);
                t.markIsDone();
                ui.printDone(t);
                storage.writeToFile(tasks.getTaskList());
        }
    }

}
