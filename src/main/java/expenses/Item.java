package expenses;

import java.io.Serializable;

public class Item implements Serializable {
    public String title;
    public double amount;
    private boolean isCredit;
    private static final long serialVersionUID = 42L;

    /**
     * Constructs a credit or debit item.
     * @param title of the item
     * @param amount credited or debited
     * @param isCredit whether this item is a credit or debit item
     */
    public Item(String title, double amount, boolean isCredit) {
        this.title = title;
        this.amount = amount;
        this.isCredit = isCredit;
    }
}