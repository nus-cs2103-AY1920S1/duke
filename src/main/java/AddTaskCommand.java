public class AddTaskCommand extends Command {

    private String instruction;
    private String description;

    public AddTaskCommand(String instruction, String description) {
        this.instruction = instruction;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        Task task;
        String[] words = description.split(" ");
        StringBuilder descriptions = new StringBuilder();

        switch(instruction) {
            case "todo":
                task = new ToDo(description);
                tasks.addTask(task);
                ui.addTask(task, tasks.getNumber());
                break;

            case "event":

                StringBuilder at = new StringBuilder();
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    if (!word.equals("/at")) {
                        descriptions.append(words[i]);
                        descriptions.append(" ");
                    } else {
                        for (int j = i + 1; j < words.length; j++) {
                            at.append(words[j]);
                            at.append(" ");
                        }
                        break;
                    }
                }
                task = new Event(descriptions.toString().trim(), at.toString().trim());
                tasks.addTask(task);
                ui.addTask(task, tasks.getNumber());
                break;

            case "deadline":
                StringBuilder by = new StringBuilder();
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    if (!word.equals("/by")) {
                        descriptions.append(words[i]);
                        descriptions.append(" ");
                    } else {
                        for (int j = i + 1; j < words.length; j++) {
                            by.append(words[j]);
                            by.append(" ");
                        }
                        break;
                    }
                }
                task = new Deadline(descriptions.toString().trim(), by.toString().trim());
                tasks.addTask(task);
                ui.addTask(task, tasks.getNumber());
                break;

            default:
        }

    }

}
