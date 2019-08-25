import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> store;
    Storage storage;

    public TaskList(ArrayList<Task> listOfTasks, Storage storage) {
        this.store = listOfTasks;
        this.storage = storage;

    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.store = listOfTasks;
    }

    public void listTask() {
        int num = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task i : store) {
            System.out.println(+num + ". " + i);
            num++;
        }
    }

    public void doneTask(int index) {
        Task taskToModify = store.get(index);
        taskToModify.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskToModify);
        storage.saveTaskToFile(store);

    }

    public void addToDoTask(String toDoTaskString) {
        //.trim() to remove trailing space
        Task toDoTask = new ToDo(toDoTaskString);
        store.add(toDoTask);
        storage.saveTaskToFile(store);
        Ui.printGotIt();
        System.out.println(" " + toDoTask.toString());
        Ui.printNow(store.size());
    }

    public void addDeadlineTask(String deadlineTaskDescriptionString, String deadlineTaskDateAndTimeString) {
        Task deadlineTask = new Deadline(deadlineTaskDescriptionString, deadlineTaskDateAndTimeString);
        store.add(deadlineTask);
        storage.saveTaskToFile(store);
        Ui.printGotIt();
        System.out.println(" " + deadlineTask.toString());
        Ui.printNow(store.size());
    }

    public void addEventTask(String eventTaskDescriptionString, String eventTaskDateAndTimeString) {
        //use of .trim() to avoid trailing whitespace
        Task eventTask = new Event(eventTaskDescriptionString, eventTaskDateAndTimeString);
        store.add(eventTask);
        storage.saveTaskToFile(store);
        Ui.printGotIt();
        System.out.println(" " + eventTask.toString());
        Ui.printNow(store.size());
    }
    public void deleteTask(int index) {
        Task removed = store.remove(index);
        storage.saveTaskToFile(store);
        Ui.printNoted();
        System.out.println(removed);
        Ui.printNow(store.size());
    }

    private void addGenericTask(Task task) {
        this.store.add(task);
    }

    private void listSearchQuery() {
        int num = 1;
        for (Task i : store) {
            System.out.println(+num + ". " + i);
            num++;
        }
    }


    public void findTask(String query) {
        ArrayList<String> listOfAllDesc = new ArrayList<>();
        for (Task i : store) {
            listOfAllDesc.add(i.description);
        }
        TaskList temp = new TaskList(new ArrayList<Task>());

        for (int i = 0; i < store.size(); i++) {
            String eachDescription = listOfAllDesc.get(i);
            if (eachDescription.contains(query)) {
                temp.addGenericTask(store.get(i));
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        temp.listSearchQuery();
    }
}
