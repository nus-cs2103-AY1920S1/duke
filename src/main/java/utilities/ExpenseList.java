package utilities;

import expense.Expense;

import java.util.ArrayList;

public class ExpenseList {

    private ArrayList<Expense>list;
    private ArrayList<ArrayList<Expense>>subLists = new ArrayList<>();

    public ExpenseList(ArrayList<Expense>list) {
        this.list = list;
        this.setIDs();
        for(Expense x: list){
            editSubList(x);
        }
    }

    public ExpenseList() {
        list = new ArrayList<Expense>();
        subLists = new ArrayList<>();
    }

    private void editSubList(Expense item) {
        boolean isPresent = false;
        for(ArrayList<Expense> e: subLists) {
            if(e.get(0).getTagName().equals(item.getTagName())) {
                e.add(item);
                isPresent = true;
                break;
            }
        }
        if(!isPresent) {
            ArrayList<Expense>newList = new ArrayList<>();
            newList.add(item);
            subLists.add(newList);
        }
    }

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

    public String toSubString() {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < subLists.size(); i++) {
            s.append("#").append(subLists.get(i).get(0).getTagName()).append("\n");
            for(int j = 0; j < subLists.get(i).size(); j++) {
                if (i == (subLists.size() - 1) && (j == (subLists.get(i).size() - 1))) {
                    s.append(j + 1).append(". ").append(subLists.get(i).get(j).toSubString());
                } else if (j == (subLists.get(i).size() - 1)){
                    s.append(j + 1).append(". ").append(subLists.get(i).get(j).toSubString()).append("\n").append("\n");
                } else{
                    s.append(j + 1).append(". ").append(subLists.get(i).get(j).toSubString()).append("\n");
                }
            }
        }
        return s.toString();
    }

    public void add (Expense item) {
        list.add(item);
        this.setIDs();
        editSubList(item);
    }

    public int size() {
        return list.size();
    }

    public double totalValue() {
        double total = 0;

        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getCost();
        }

        return total;
    }

    public Expense get(int i) {
        return list.get(i);
    }

    public void remove(int i) {
        list.remove(i);
        /*for(int j = 0; j < subLists.size(); j++) {
            for(int k = 0; k < subLists.get(j).size(); k++) {
                if(subLists.get(j).get(k).getID() == i) {
                    subLists.get(j).remove(k);
                }
            }
        }*/
        subLists.clear();
        this.setIDs();
        for(Expense x: list){
            editSubList(x);
        }
    }

    public void setIDs(){
        for(int i = 0; i < list.size(); i++) {
            list.get(i).setID(i + 1);
        }
    }

}
