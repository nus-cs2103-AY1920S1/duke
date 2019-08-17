public class Client {
    private Storage storage;
    private Echoer echoer;
    private static Client client = null;

    private Client() {
        this.storage = new Storage();
        this.echoer = new Echoer();
        this.echoer.greet();
    }

    static Client initialise() {
        if (client != null) {
            System.out.println("Client has already been initialised");
        } else {
            client = new Client();
        }
        return client;
    }

    boolean read(String message) {
        boolean shouldContinue = true;
        switch (message) {
        case "list":
            this.listItems();
            break;
        case "bye":
            this.echoer.exit();
            shouldContinue = false;
            break;
        default:
            this.addItem(message);
        }
        return shouldContinue;
    }

    private void addItem(String item) {
        this.storage.add(item);
        this.echoer.echo("added: " + item);
    }

    private void listItems() {
        String[] items = this.storage.getList().toArray(new String[0]);
        for (int ordering = 1; ordering <= items.length; ordering++) {
            items[ordering - 1] = ordering + ". " + items[ordering - 1];
        }
        this.echoer.echo(items);
    }


}
