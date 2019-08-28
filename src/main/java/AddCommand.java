public class AddCommand extends Command {
    private String typeOfTask;

    public AddCommand(String typeOfTask, String commandText) {
        super();
        this.desc = commandText;
        this.typeOfTask = typeOfTask;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
            switch (typeOfTask) {
                case "todo":
                    if (desc.equals("")) {
                        throw new InvalidDescriptionException("Wrong description");
                    }
                    ui.showText(task.addTask(new ToDos(desc)));
                    break;

                case "deadline":
                    String[] input2 = desc.trim().split("/by");
                    if (input2.length != 2) {
                        throw new InvalidDescriptionException("Wrong description");
                    }
                    ui.showText(task.addTask(new Deadlines(input2[0], input2[1])));
                    break;

                case "event":
                    String[] input3 = desc.trim().split("/at");
                    if (input3.length != 2) {
                        throw new InvalidDescriptionException("Wrong description");
                    }
                    ui.showText(task.addTask(new Event(input3[0], input3[1])));
                    break;
            }
    }
}
