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
        }
        else {
            client = new Client();
        }
        return client;
    }
}
