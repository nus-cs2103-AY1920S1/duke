import java.util.ArrayList;

class ToDoList {
    private ArrayList<Task> taskList;
    ToDoList() {
        taskList = new ArrayList<>(100);
    }
    void addTask(String task, TaskType type) throws EmptyEventDscDukeException, EmptyDeadlineDscDukeException, NoDateDukeException {
        Task addedTask;
        String date;
        switch (type) {
            case TODO:
                addedTask = new TodoTask(task);
                taskList.add(addedTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(addedTask);
                break;
            case DEADLINE:
                if (task.indexOf('/') == -1) {
                    throw new NoDateDukeException("/ wasn't found");
                }
                if (task.indexOf('/') == 0) {
                    throw new EmptyDeadlineDscDukeException("Description for deadline is empty.");
                }
                date = task.substring(task.indexOf('/') + 1);
                if (date.equals("")) {
                    throw new NoDateDukeException("No date provided");
                }
                addedTask = new Deadline(
                    task.substring(0, task.indexOf('/') - 1),
                    date
                );
                taskList.add(addedTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(addedTask);
                break;
            case EVENT:
                if (task.indexOf('/') == -1) {
                    throw new NoDateDukeException("No date found");
                }
                if (task.indexOf('/') == 0) {
                    throw new EmptyEventDscDukeException("Description for event is empty.");
                }
                date = task.substring(task.indexOf('/') + 1);
                if (date.equals("")) {
                    throw new NoDateDukeException("No date provided");
                }
                addedTask = new Event(
                        task.substring(0, task.indexOf('/') - 1),
                        date
                );
                taskList.add(addedTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(addedTask);
                break;
            default:
                // throw some exception here?
                break;
        }
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    void listAllTasks() {
        int total = taskList.size();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < total; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }
    void checkTask(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.taskDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }
}
