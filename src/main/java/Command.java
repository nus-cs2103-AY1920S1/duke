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
                break outLoop;

            case DONE :
                try {
                    int taskNo = Integer.parseInt(TaskLine.substring(5));
                    taskList.get(taskNo - 1).markAsDone(taskList.get(taskNo - 1));
                    reply = ui.printDone(taskList.get(taskNo - 1));
                } catch (Exception e) {
                    reply = ui.throwInputError("done");
                }
                break outLoop;

            case TODO :
                try {
                    if (TaskLine.length() == 4) {
                        reply =  ui.throwInputError("todo");
                    } else {
                        Task todoTask = new Todo(TaskLine.substring(5));
                        taskList.add(todoTask);
                        reply =  ui.printAddTask(todoTask,taskList.size());
                    }
                } catch (Exception e){
                    reply = ui.throwInputError("todo");
                }
                break outLoop;

            case DEADLINE :
                try {
                    if (TaskLine.length() == 8) {
                        reply =  ui.throwInputError("deadline");
                    } else {
                        int indexOfSlash = TaskLine.indexOf("/");
                        Task deadlineTask = new Deadline(TaskLine.substring(9, indexOfSlash - 1), TaskLine.substring(indexOfSlash + 4));
                        taskList.add(deadlineTask);
                        reply =  ui.printAddTask(deadlineTask,taskList.size());
                    }
                } catch (Exception e){
                    reply = ui.throwInputError("deadline");
                }
                break outLoop;

            case EVENT :
                try {
                    if (TaskLine.length() == 5) {
                        reply =  ui.throwInputError("event");
                    } else {
                        int indexOfSlash = TaskLine.indexOf("/");
                        Task eventTask = new Event(TaskLine.substring(6, indexOfSlash - 1), TaskLine.substring(indexOfSlash + 4));
                        taskList.add(eventTask);
                        reply =  ui.printAddTask(eventTask,taskList.size());
                    }
                } catch (Exception e) {
                    reply =  ui.throwInputError("event");
                }

                break outLoop;

            case DELETE :
                try {
                    int taskNoDelete = Integer.parseInt(TaskLine.substring(7));
                    Task deletedTask = taskList.get(taskNoDelete - 1);
                    reply =  ui.printDelete(deletedTask, taskList.size());
                    taskList.remove(taskNoDelete - 1);
                } catch (Exception e) {
                    reply = ui.throwInputError("delete");
                }
                break outLoop;

            case FIND :
                try {
                    String keyword = TaskLine.substring(5);
                    TaskList findList = new TaskList();
                    for (int i = 0; i < taskList.size(); i++) {
                        if (taskList.get(i).getDescription().contains(keyword)) {
                            findList.add(taskList.get(i));
                        }
                    }
                    reply =  ui.printFind(findList);
                } catch (Exception e) {
                    reply = ui.throwInputError("find");
                }
                break outLoop;

            default :
                reply =  ui.printIDK();
                break outLoop;
            }
        }
        storage.UpdateFile();
        return reply;
    }
}
