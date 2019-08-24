import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> store;
    Storage storage;

    public TaskList(ArrayList<Task> listOfTasks, Storage storage) {
        this.store = listOfTasks;
        this.storage = storage;

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

    public void addToDoTask(String[] argumentArray) {
        //form back string
        String toDoTaskString = "";
        for (int i = 1; i < argumentArray.length; i++) {
            toDoTaskString += argumentArray[i];
            toDoTaskString += " ";

        }
        //.trim() to remove trailing space
        Task toDoTask = new ToDo(toDoTaskString.trim());
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


}
