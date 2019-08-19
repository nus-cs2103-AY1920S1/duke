public class TaskList {
    int noOfItems;
    String[] listItems;

    public TaskList() {
        noOfItems = 0;
        listItems = new String[100];
    }

    public void printList() {
        for (int i = 1; i <= noOfItems; i++) {
            String item = listItems[i - 1];
            System.out.println("\t  " + i + ". " + item);
        }
    }

    public void add(String item) {
        listItems[noOfItems] = item;
        noOfItems++;
    }
}
