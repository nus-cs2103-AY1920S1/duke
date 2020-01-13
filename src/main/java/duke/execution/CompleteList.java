package duke.execution;

import duke.models.Planner;

import java.util.ArrayList;

public class CompleteList {

    public static ArrayList<Planner> listOfPlans = new ArrayList<Planner>();

    /**
     * Constructor for CompleteList.
     */
    public CompleteList() {

    }

    /**
     * Overloaded Constructor for Task list in
     * the event that a array list is available
     * from the file.
     *
     * @param list Arraylist that contains all the tasks.
     */
    public CompleteList(ArrayList<Planner> list) {
        listOfPlans = list;
    }

    public void addToCompleteList(Planner assignment) {
        assert assignment != null;
        listOfPlans.add(assignment);
    }
}
