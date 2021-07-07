package duke;

import duke.task.Task;
import duke.expense.Person;

import java.util.ArrayList;
import java.util.List;

public class ListStoreModel {
    public List<Task> taskList;
    public List<Person> peopleList;

    public ListStoreModel() {
        this.taskList = new ArrayList<>();
        this.peopleList = new ArrayList<>();
    }

    public ListStoreModel(List<Task> taskList) {
        this.taskList = taskList;
        this.peopleList = new ArrayList<>();
    }

    public ListStoreModel(List<Task> taskList, List<Person> peopleList) {
        this.taskList = taskList;
        this.peopleList = peopleList;
    }
}
