package utilities;

import expense.Expense;

import java.util.ArrayList;

/**
 * to store the expenses.
 */
public class ExpenseList {

    private ArrayList<Expense> list;
    private ArrayList<ArrayList<Expense>> subLists = new ArrayList<>();

    /**
     * Constructor if there is a existing list.
     *
     * @param list is the list input
     */
    public ExpenseList(ArrayList<Expense> list) {
        this.list = list;
        this.setIDs();
        for (Expense x: list) {
            editSubList(x);
        }
    }

    /**
     * Constructor if there is no existing list.
     */
    public ExpenseList() {
        list = new ArrayList<Expense>();
        subLists = new ArrayList<>();
    }

    /**
     * to edit the list of tagged lists when new item is added.
     *
     * @param item is the new expense added
     */
    private void editSubList(Expense item) {
        boolean isPresent = false;
        for (ArrayList<Expense> e: subLists) {
            if (e.get(0).getTagName().equals(item.getTagName())) {
                e.add(item);
                isPresent = true;
                break;
            }
        }
        if (!isPresent) {
            ArrayList<Expense> newList = new ArrayList<>();
            newList.add(item);
            subLists.add(newList);
        }
    }

    /**
     * To print for DukeOutput.txt
     *
     * @return a string to be printed in output
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= list.size(); i++) {
            if (i == (list.size())) {
                s.append(i).append(". ").append(list.get(i - 1).toString());
            } else {
                s.append(i).append(". ").append(list.get(i - 1).toString()).append("\n");
            }
        }
        return s.toString();
    }

    /**
     * To print the tagged subLists in CLI.
     *
     * @return the string to be printed
     */
    public String toSubString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < subLists.size(); i++) {
            s.append("#").append(subLists.get(i).get(0).getTagName()).append("\n");
            for (int j = 0; j < subLists.get(i).size(); j++) {
                if (i == (subLists.size() - 1) && (j == (subLists.get(i).size() - 1))) {
                    s.append(j + 1).append(". ").append(subLists.get(i).get(j).toSubString());
                } else if (j == (subLists.get(i).size() - 1)) {
                    s.append(j + 1).append(". ").append(subLists.get(i).get(j).toSubString()).append("\n").append("\n");
                } else {
                    s.append(j + 1).append(". ").append(subLists.get(i).get(j).toSubString()).append("\n");
                }
            }
        }
        return s.toString();
    }

    /**
     * add new expense to the lists.
     *
     * @param item is the new expense
     */
    public void add(Expense item) {
        list.add(item);
        this.setIDs();
        editSubList(item);
    }

    /**
     * to calculate size of list.
     *
     * @return int value of size
     */
    public int size() {
        return list.size();
    }

    /**
     * calculates the total expenses spent.
     *
     * @return the total expenses as a double
     */
    public double totalValue() {
        double total = 0;

        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getCost();
        }

        return total;
    }

    /**
     * to retrieve expense object from list.
     *
     * @param i is the digit of object in the list.
     *
     * @return the expense object
     */
    public Expense get(int i) {
        return list.get(i);
    }

    /**
     * remove Expense object from list.
     *
     * @param i is the digit of object in the list
     */
    public void remove(int i) {
        list.remove(i);
        subLists.clear();
        this.setIDs();
        for (Expense x: list) {
            editSubList(x);
        }
    }

    /**
     * change iD of object when changes occur to the list.
     */
    public void setIDs() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i + 1);
        }
    }

}
