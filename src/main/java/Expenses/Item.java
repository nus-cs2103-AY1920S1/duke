package Expenses;

import java.io.Serializable;

public class Item implements Serializable {
    public String title;
    public double amount;
    private boolean isCredit;
    private static final long serialVersionUID = 42L;

    public Item(String title, double amount, boolean isCredit) {
        this.title = title;
        this.amount = amount;
        this.isCredit = isCredit;
    }
}