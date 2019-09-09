public class HelpCommand extends Command {
    HelpCommand(){
        super(6);
    }
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        return "Welcome to Duke help menu\n " +
                "   Here are the following commands:\n" +
                "       Todo {taskName}\n" +
                "           Add Todo task where {taskName} is the name of the task\n" +
                "       Deadline {taskName} \\by {date}\n" +
                "           Add Deadline task where {taskName} is the name of the task\n"+
                "       Event {taskName} \\at {date}\n" +
                "           Add Event task where {taskName} is the name of the task\n"+
                "       list" +
                "           Get the full list of task"+
                "       done {index}" +
                "           Make the task at {index} done"+
                "       delete {index}" +
                "           Delete the task at {index}" +
                "       bye" +
                "           quit the programme";
    }
}
