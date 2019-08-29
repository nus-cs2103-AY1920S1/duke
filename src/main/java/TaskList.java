import java.io.IOException;
import java.util.ArrayList;

class TaskList {
    ArrayList<Task> tasks;
    Storage storage;

    TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
    }

    TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    boolean hasTask() {
        return tasks.size() != 0 ;
    }

    int size() {
        return tasks.size();
    }

    void markTaskAsDone(int taskNumber) throws DukeException, IOException {
        if (tasks.get(taskNumber).isDone) {
            String errorMessage = Ui.spaces(5) + "Task has already been marked done!";
            throw new DukeException(errorMessage);
        }
        
        storage.overwriteLocalSave(tasks);

        tasks.get(taskNumber).isDone = true;
        System.out.println(Ui.spaces(5) + "Nice! I've marked this task as done:\n"
                + Ui.spaces(5) + tasks.get(taskNumber));
    }

    void listAlltasks() throws DukeException {
        if (tasks.size() == 0) {
            String errorMessage = Ui.spaces(5) + "There are no task(s) to list!";
            throw new DukeException(errorMessage);
        }

        System.out.println(Ui.spaces(5) + "Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%s%d. %s\n", Ui.spaces(7), i + 1, tasks.get(i));
        }
    }

    void makeNewTodo(String description) throws IOException {
        ToDo currentTodo = new ToDo(description);
        tasks.add(currentTodo);
        
        storage.addToLocalSave(currentTodo);

        System.out.printf("%sGot it! I've added this task for you \uD83D\uDE09\n", Ui.spaces(5));
        System.out.printf("%s%s\n\n", Ui.spaces(7), currentTodo);
        System.out.printf("%sNow you have %d task(s) in your list.\n", Ui.spaces(5), tasks.size());
    }

    void makeNewDeadline(String desc, String dateTime) throws IOException {
        Deadline currentDeadline = new Deadline(desc, dateTime);
        tasks.add(currentDeadline);
        
        storage.addToLocalSave(currentDeadline);
        
        System.out.printf("%sGot it! I've added this task for you \uD83D\uDE09\n", Ui.spaces(5));
        System.out.printf("%s%s\n\n", Ui.spaces(7), currentDeadline);
        System.out.printf("%sNow you have %d task(s) in your list.\n", Ui.spaces(5), tasks.size());
    }

    
    void makeNewEvent(String desc, String dateTime) throws IOException {
        Event currentEvent = new Event(desc, dateTime);
        tasks.add(currentEvent);

        storage.addToLocalSave(currentEvent);

        System.out.printf("%sGot it! I've added this task for you \uD83D\uDE09\n", Ui.spaces(5));
        System.out.printf("%s%s\n\n", Ui.spaces(7), currentEvent);
        System.out.printf("%sNow you have %d task(s) in your list.\n", Ui.spaces(5), tasks.size());
    }

    void delete(int taskNumber) throws IOException {
        Task removedTask = tasks.remove(taskNumber);
        
        storage.overwriteLocalSave(tasks);

        System.out.println(Ui.spaces(5) + "Noted. I've removed this task:\n"
                + Ui.spaces(7) + removedTask + "\n\n"
                + Ui.spaces(5) + String.format("Now you have %d task(s) in your list.", tasks.size()));
    }
}