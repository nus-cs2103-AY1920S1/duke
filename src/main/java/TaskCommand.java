public class TaskCommand extends Command {
    public TaskCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[]splitWords = command.trim().split("\\s",2);

            if (splitWords[0].equals("todo")) {                                                                 //IF TODO
                ToDo.createTodo(command, tasks, storage);
            } else if (splitWords[0].equals("deadline")) {                                                      //IF DEADLINE
                Deadline.createDeadline(command, tasks, storage);
            } else if (splitWords[0].equals("event")) {                                                         //IF EVENT
                Event.createEvent(command, tasks, storage);
            } else {
                throw new DukeException("");
            }

            System.out.println("Got it. I've added this task:" + "\n" + tasks.printLatest()
                    + "\n" + "Now you have " + tasks.size() + " tasks in the list.");

        } catch (IllegalArgumentException e){
            System.out.println("☹ OOPS!!! The date format is wrong.");
        } catch (DukeException e){
            throw new DukeException("");
        }
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
