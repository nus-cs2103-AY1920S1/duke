import java.io.IOException;

public class Command {

    public Command() {
    }

    public String execute(TaskList taskList, UI ui,Storage storage, String input) throws DukeException, IOException {
        storage.LoadFile();
        ui.printGreeting();
        String TaskLine = input;
        Parser parser = new Parser();
        String reply = "";
        outLoop:
        while (TaskLine != null) {
            String[] words = TaskLine.split(" ");
            switch (parser.parse(TaskLine)) {
            case BYE:
                reply = ui.printBye();
                break outLoop;

            case LIST :
                reply = ui.printList(taskList);
                //TaskLine = ui.readCommand();
                break outLoop;

            case DONE :
                int taskNo = Integer.parseInt(TaskLine.substring(5));
                taskList.get(taskNo - 1).markAsDone(taskList.get(taskNo - 1));
                reply = ui.printDone(taskList.get(taskNo - 1));
                //TaskLine = ui.readCommand();
                break outLoop;

            case TODO :
                if (TaskLine.length() == 4) {
                    reply =  ui.throwInputError("todo");
                } else {
                    Task todoTask = new Todo(TaskLine.substring(5));
                    taskList.add(todoTask);
                    reply =  ui.printAddTask(todoTask,taskList.size());
                }
                //TaskLine = ui.readCommand();

                break outLoop;

            case DEADLINE :
                if (TaskLine.length() == 8) {
                    reply =  ui.throwInputError("deadline");
                } else {
                    int indexOfSlash = TaskLine.indexOf("/");
                    Task deadlineTask = new Deadline(TaskLine.substring(9, indexOfSlash - 1), TaskLine.substring(indexOfSlash + 4));
                    taskList.add(deadlineTask);
                    reply =  ui.printAddTask(deadlineTask,taskList.size());
                }
                //TaskLine = ui.readCommand();
                break outLoop;

            case EVENT :
                if (TaskLine.length() == 5) {
                    reply =  ui.throwInputError("event");
                } else {
                    int indexOfSlash = TaskLine.indexOf("/");
                    Task eventTask = new Event(TaskLine.substring(6, indexOfSlash - 1), TaskLine.substring(indexOfSlash + 4));
                    taskList.add(eventTask);
                    reply =  ui.printAddTask(eventTask,taskList.size());
                }
                //TaskLine = ui.readCommand();
                break outLoop;

            case DELETE :
                int taskNoDelete = Integer.parseInt(TaskLine.substring(7));
                Task deletedTask = taskList.get(taskNoDelete - 1);
                reply =  ui.printDelete(deletedTask, taskList.size());
                taskList.remove(taskNoDelete - 1);
                //TaskLine = ui.readCommand();
                break outLoop;

            case FIND :
                String keyword = TaskLine.substring(5);
                TaskList findList = new TaskList();
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getDescription().contains(keyword)) {
                        findList.add(taskList.get(i));
                    }
                }
                reply =  ui.printFind(findList);
                //TaskLine = ui.readCommand();
                break outLoop;

            default :
                reply =  ui.printIDK();
                //TaskLine = ui.readCommand();
                break outLoop;
            }
        }
        storage.UpdateFile();
        return reply;
    }
}
