public class TaskList {
    int noOfItems;
    String[] listItems;

    public TaskList() {
        noOfItems = 0;
        listItems = new String[100];
    }

    /**
     * Prints the list of items in the order as stored by the program.
     */
    public void printList() {
        for (int i = 1; i <= noOfItems; i++) {
            String item = listItems[i - 1];
            System.out.println("\t  " + i + ". " + item);
        }
    }

    /**
     * Adds an item to the list.
     * @param item The item to be stored in the list.
     */
    public void add(String item) {
        listItems[noOfItems] = item;
        noOfItems++;
    }
}
